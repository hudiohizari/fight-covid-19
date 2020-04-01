package id.rumahawan.fightcovid19.navigation.ui.activity

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
import com.google.android.gms.maps.model.MarkerOptions
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.ActivityReferenceBinding
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceRujukan
import id.rumahawan.fightcovid19.navigation.model.data.Hospital
import id.rumahawan.fightcovid19.navigation.model.data.Province
import id.rumahawan.fightcovid19.navigation.model.response.ResponseHospital
import id.rumahawan.fightcovid19.navigation.ui.bottomsheet.BottomSheetHospitalDetail
import id.rumahawan.fightcovid19.navigation.viewmodel.ViewModelReference
import id.rumahawan.fightcovid19.navigation.viewmodelfactory.ViewModelFactoryReference
import id.rumahawan.fightcovid19.utils.BaseActivity
import id.rumahawan.fightcovid19.utils.getBitmap
import id.rumahawan.fightcovid19.utils.snackbar
import id.rumahawan.fightcovid19.utils.snackbarLong
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
    private var selectedProvinceLocation: LatLng? = null

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
        val visibility = viewModel.isSeeAllVisible.value
        if (visibility != View.VISIBLE) viewModel.isSeeAllVisible.value = View.VISIBLE
        selectedProvinceLocation = LatLng(
            selected.lat?.toDouble() ?: 0.0, selected.lng?.toDouble() ?: 0.0
        )
        viewModel.getHospitalList(
            selected.provinceCode ?: "",
            selected.lat?.toDouble() ?: 0.0,
            selected.lng?.toDouble() ?: 0.0
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
        for (hospital in response.hospitals ?: mutableListOf()) {
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
        }
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedProvinceLocation, 6f))

        map?.setOnMarkerClickListener {
            if (!it.title.equals("Lokasi anda", true)){
                val dialog = BottomSheetHospitalDetail(searchHospital(it.title))
                dialog.show(supportFragmentManager, dialog.tag)
                true
            } else { false }
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
                    if (isMoveToMarker) map?.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesia, 10f))
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