package ukic.ante.dineeasy

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ukic.ante.dineeasy.di.repositoryModule
import ukic.ante.dineeasy.di.viewModelModule

class Dineeasy:Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Dineeasy)
            modules(
                viewModelModule,
                repositoryModule
            )
        }
    }

    companion object{
        lateinit var application: Application
    }
}