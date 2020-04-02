package id.rumahawan.fightcovid19.features.main.ui.bottomsheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.BottomSheetHospitalDetailBinding
import id.rumahawan.fightcovid19.features.main.bridge.InterfaceHospitalDetail
import id.rumahawan.fightcovid19.features.main.model.data.Hospital
import id.rumahawan.fightcovid19.features.main.viewmodel.ViewModelHospitalDetail

class BottomSheetHospitalDetail(
    private val hospital: Hospital
):
    BottomSheetDialogFragment(),
    InterfaceHospitalDetail
{

    private lateinit var binding: BottomSheetHospitalDetailBinding
    private lateinit var viewModel: ViewModelHospitalDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_hospital_detail,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ViewModelHospitalDetail::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()

        return binding.root
    }

    private fun initView(){
        viewModel.name.value = hospital.name
        viewModel.address.value = hospital.address
        viewModel.distance.value = "Jarak dari tempatmu: ${hospital.distance} Km"
        viewModel.phone.value = hospital.telephone
    }

    override fun onClosePressed() {
        dismiss()
    }

    override fun launchNavigation() {
        val gmmIntentUri = Uri.parse(
            "google.navigation:q=${hospital.lat},${hospital.lng}&mode=d")
        Intent(Intent.ACTION_VIEW, gmmIntentUri).also {
            it.setPackage("com.google.android.apps.maps")
            startActivity(it)
        }
    }

    override fun launchPhone() {
        Intent(
            Intent.ACTION_DIAL,
            Uri.fromParts("tel", "${hospital.telephone}", null)
        ).also { startActivity(it) }
    }
}