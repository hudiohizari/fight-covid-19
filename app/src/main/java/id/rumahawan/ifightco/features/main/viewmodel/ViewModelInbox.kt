package id.rumahawan.ifightco.features.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceInbox
import id.rumahawan.ifightco.features.main.model.data.Inbox
import id.rumahawan.ifightco.features.main.repository.RepositoryInbox
import id.rumahawan.ifightco.utils.ApiException
import id.rumahawan.ifightco.utils.Coroutines
import id.rumahawan.ifightco.utils.NoInternetException

class ViewModelInbox(
    private val repository: RepositoryInbox
) : ViewModel() {

    var bridge: InterfaceInbox? = null

    val inboxList = MutableLiveData<MutableList<Inbox>>()
        .apply { value = mutableListOf() }
    val emptyVisibility = MutableLiveData<Int>().apply { value = View.VISIBLE }
    val listVisibility = MutableLiveData<Int>().apply { value = View.GONE }

    init {
        getInboxData()
        inboxList.observeForever {
            bridge?.getInboxAdapter()?.notifyDataSetChanged()
            if (it.size > 0) {
                if(emptyVisibility.value != View.GONE) emptyVisibility.value = View.GONE
                if(listVisibility.value != View.VISIBLE) listVisibility.value = View.VISIBLE
            } else {
                if(emptyVisibility.value != View.VISIBLE) emptyVisibility.value = View.VISIBLE
                if(listVisibility.value != View.GONE) listVisibility.value = View.GONE
            }
        }
    }

    private fun getInboxData(){
        bridge?.showLoading()
        Coroutines.main{
            try {
                inboxList.value = repository.getInbox().inbox
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }
}
