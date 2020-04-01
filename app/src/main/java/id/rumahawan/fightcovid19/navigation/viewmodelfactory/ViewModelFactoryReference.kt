package id.rumahawan.fightcovid19.navigation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.navigation.repository.RepositoryReference
import id.rumahawan.fightcovid19.navigation.viewmodel.ViewModelReference

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