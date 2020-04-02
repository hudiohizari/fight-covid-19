package id.rumahawan.fightcovid19.features.main.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.features.main.repository.RepositoryHome
import id.rumahawan.fightcovid19.features.main.viewmodel.ViewModelHome

class ViewModelFactoryHome(
    private val repository: RepositoryHome
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelHome(
            repository
        ) as T
    }

}