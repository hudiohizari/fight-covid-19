package id.rumahawan.fightcovid19.navigation.ui.bottomsheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.rumahawan.fightcovid19.R

class BottomSheetHospitalDetail: BottomSheetDialogFragment() {

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(context, R.layout.bottom_sheet_hospital_detail, null)
        dialog.setContentView(view)
    }
}