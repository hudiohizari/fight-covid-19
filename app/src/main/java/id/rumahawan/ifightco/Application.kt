package id.rumahawan.ifightco

import android.app.Application
import id.rumahawan.ifightco.features.main.repository.RepositoryHome
import id.rumahawan.ifightco.features.main.repository.RepositoryInbox
import id.rumahawan.ifightco.features.main.repository.RepositoryReference
import id.rumahawan.ifightco.features.main.viewmodelfactory.ViewModelFactoryHome
import id.rumahawan.ifightco.features.main.viewmodelfactory.ViewModelFactoryInbox
import id.rumahawan.ifightco.features.main.viewmodelfactory.ViewModelFactoryReference
import id.rumahawan.ifightco.repomanager.local.LocalRequestManager
import id.rumahawan.ifightco.repomanager.remote.NetworkConnectionInterceptor
import id.rumahawan.ifightco.repomanager.remote.RemoteRequestManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        /*
        * PROJECT WIDE
        */
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { RemoteRequestManager(instance()) }
        bind() from singleton { LocalRequestManager(instance()) }

        /*
        * Home
        */
        bind() from singleton { RepositoryHome(instance()) }
        bind() from provider { ViewModelFactoryHome(instance()) }

        /*
        * Reference
        */
        bind() from singleton { RepositoryReference(instance()) }
        bind() from provider { ViewModelFactoryReference(instance()) }

        /*
        * Inbox
        */
        bind() from singleton { RepositoryInbox(instance()) }
        bind() from provider { ViewModelFactoryInbox(instance()) }

    }
}