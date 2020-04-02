package id.rumahawan.fightcovid19.features.main.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.features.main.repository.RepositoryReference
import id.rumahawan.fightcovid19.features.main.viewmodel.ViewModelReference

class ViewModelFactoryReference(
    private val repository: RepositoryReference
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelReference(
            repository
        ) as T
    }

}