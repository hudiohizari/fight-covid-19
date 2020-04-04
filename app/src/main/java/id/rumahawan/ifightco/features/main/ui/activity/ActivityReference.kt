package id.rumahawan.ifightco.features.main.ui.activity

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.databinding.ActivityReferenceBinding
import id.rumahawan.ifightco.features.main.bridge.InterfaceRujukan
import id.rumahawan.ifightco.features.main.model.data.Hospital
import id.rumahawan.ifightco.features.main.model.data.Province
import id.rumahawan.ifightco.features.main.model.response.ResponseHospital
import id.rumahawan.ifightco.features.main.ui.bottomsheet.BottomSheetHospitalDetail
import id.rumahawan.ifightco.features.main.viewmodel.ViewModelReference
import id.rumahawan.ifightco.features.main.viewmodelfactory.ViewModelFactoryReference
import id.rumahawan.ifightco.utils.*
import mumayank.com.airlocationlibrary.AirLocation
import org.kodein.di.generic.instance
import java.util.*

class ActivityReference:
    BaseActivity(),
    InterfaceRujukan,
    OnMapReadyCallback

{
    private lateinit var binding: ActivityReferenceBinding
    private lateinit var viewModel: ViewModelReference
    private val factory: ViewModelFactoryReference by instance()

    private var map: GoogleMap? = null
    private var selectedProvince: Province? = null
    private var userLocation: Location? = null

    override fun onResume() {
        super.onResume()
        if(::binding.isInitialized) binding.mvMain.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::binding.isInitialized) binding.mvMain.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reference)
        viewModel = ViewModelProvider(this, factory).get(ViewModelReference::class.java)
        viewModel.bridge = this
        viewModel.title.value = resources.getString(R.string.label_rs_rujukan)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView(savedInstanceState)
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private fun initView(savedInstanceState: Bundle?){
        binding.mvMain.onCreate(savedInstanceState)
        binding.mvMain.getMapAsync(this)

        binding.atvProvince.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                getHospitalList(searchProvince(parent.getItemAtPosition(position).toString()))
        }
    }

    private fun searchProvince(title: String): Province{
        for(province in viewModel.getProvinceList()){
            if (province.province.equals(title, true)){
                return province
            }
        }
        return Province.empty()
    }

    private fun getHospitalList(selected: Province){
        val visibility = viewModel.seeListVisibility.value
        if (visibility != View.VISIBLE) viewModel.seeListVisibility.value = View.VISIBLE
        selectedProvince = selected
        viewModel.getHospitalList(
            selected.provinceCode ?: "",
            userLocation?.latitude ?: 0.0,
            userLocation?.longitude ?: 0.0
        )
    }

    override fun setListProvinceInput(list: List<String>) {
        val adapter: ArrayAdapter<String?> = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            list
        )
        binding.atvProvince.setAdapter(adapter)
    }

    override fun onHospitalsSucceed(response: ResponseHospital) {
        map?.clear()

        val builder = LatLngBounds.Builder()
        val hospitals = response.hospitals ?: mutableListOf()
        for (hospital in hospitals) {
            val bitmap = getBitmap(R.drawable.ic_hospital)
            val lat = hospital.lat?.toDouble() ?: 0.0
            val lng = hospital.lng?.toDouble() ?: 0.0
            val position = LatLng(lat, lng)

            map?.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .position(position)
                    .title(hospital.name)
            )

            builder.include(position)
        }

        val bounds = builder.build()
        val padding = getPx(16) // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        if (hospitals.size > 1) map?.moveCamera(cu)
        else map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(
            selectedProvince?.lat?.toDouble() ?: 0.0,
            selectedProvince?.lng?.toDouble() ?: 0.0), 10f))

        map?.setOnMarkerClickListener {
            if (!it.title.equals("Lokasi anda", true)){
                val dialog = BottomSheetHospitalDetail(searchHospital(it.title))
                dialog.show(supportFragmentManager, dialog.tag)
                true
            } else { false }
        }
    }

    override fun launchHospitalList() {
        launchNewActivityReturn(ActivityHospitalList::class.java).apply {
            putExtra(Constant.KEY_TITLE, selectedProvince?.province)
            putParcelableArrayListExtra(Constant.KEY_HOSPITAL_LIST,
                ArrayList(
                    viewModel.getHospitalList().apply {
                        sortBy { it.distance }
                    }.toMutableList()
                ))
            startActivity(this)
        }
    }

    private fun searchHospital(title: String): Hospital{
        for(hospitals in viewModel.getHospitalList()){
            if (hospitals.name.equals(title, true)){
                return hospitals
            }
        }
        return Hospital.empty()
    }

    override fun getLocation(isMoveToMarker: Boolean) {
        map?.clear()
        airLocation = AirLocation(this,
            shouldWeRequestPermissions = true,
            shouldWeRequestOptimization = true,
            callbacks = object: AirLocation.Callbacks {
                override fun onSuccess(location: Location) {
                    userLocation = location
                    val indonesia = LatLng(location.latitude, location.longitude)

                    val addresses: List<Address>
                    val geocoder = Geocoder(this@ActivityReference, Locale.getDefault())

                    addresses = geocoder.getFromLocation(
                        location.latitude, location.longitude, 1)
                    viewModel.address.value = addresses[0].getAddressLine(0)

                    map?.addMarker(
                        MarkerOptions()
                            .position(indonesia)
                            .title("Lokasi anda")
                    )
                    if (isMoveToMarker) {
                        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesia, 10f))
                        viewModel.seeListVisibility.apply {
                            if (value != View.GONE) value = View.GONE
                        }
                    }
                }

                override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {

                }
            })
    }

    override fun onBackButton() {
        finish()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String?) {
        binding.container.snackbar(message)
    }

    override fun showMessageLong(message: String?) {
        binding.container.snackbarLong(message)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        getLocation(true)
    }
}