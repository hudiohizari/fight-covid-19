package id.rumahawan.fightcovid19

import android.app.Application
import id.rumahawan.fightcovid19.repomanager.local.LocalRequestManager
import id.rumahawan.fightcovid19.repomanager.remote.NetworkConnectionInterceptor
import id.rumahawan.fightcovid19.repomanager.remote.RemoteRequestManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
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

    }
}