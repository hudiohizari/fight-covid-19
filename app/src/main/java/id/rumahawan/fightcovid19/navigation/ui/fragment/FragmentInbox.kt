package id.rumahawan.fightcovid19.navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.navigation.viewmodel.ViewModelFragmentInbox

class FragmentInbox : Fragment() {

    companion object {
        fun newInstance() =
            FragmentInbox()
    }

    private lateinit var viewModel: ViewModelFragmentInbox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelFragmentInbox::class.java)
        // TODO: Use the ViewModel
    }

}
