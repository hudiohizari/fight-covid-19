package id.rumahawan.ifightco.features.main.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.databinding.FragmentInboxBinding
import id.rumahawan.ifightco.features.main.adapter.AdapterInbox
import id.rumahawan.ifightco.features.main.bridge.InterfaceInbox
import id.rumahawan.ifightco.features.main.viewmodel.ViewModelInbox
import id.rumahawan.ifightco.features.main.viewmodelfactory.ViewModelFactoryInbox
import id.rumahawan.ifightco.utils.BaseFragment
import id.rumahawan.ifightco.utils.snackbar
import id.rumahawan.ifightco.utils.snackbarLong
import org.kodein.di.generic.instance

class FragmentInbox:
    BaseFragment(),
    InterfaceInbox
{

    private lateinit var binding: FragmentInboxBinding
    private lateinit var viewModel: ViewModelInbox
    private val factory: ViewModelFactoryInbox by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_inbox,
            container,
            false
        )
        viewModel = ViewModelProvider(this, factory).get(ViewModelInbox::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelInbox::class.java)
    }

    override fun getInboxAdapter(): AdapterInbox {
        val adapter: AdapterInbox
        if (binding.rvMain.adapter != null) {
            adapter = binding.rvMain.adapter as AdapterInbox
        } else {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
            binding.rvMain.apply{
                this.layoutManager = layoutManager
                itemAnimator = DefaultItemAnimator()
                adapter = AdapterInbox(viewModel.inboxList.value ?: mutableListOf())

                this.adapter = adapter
            }
        }
        return adapter
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

}
