package com.example.trucklist


import android.content.Context
import com.example.trucklist.api.NetworkModule
import com.example.trucklist.view.TruckDetailsActivity
import com.example.trucklist.view.VehicleActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class,
    SchedulerModule::class
])

class AppModule

@Module
abstract class AndroidInjectorsModule {
    @ContributesAndroidInjector
    abstract fun vehicleActivity(): VehicleActivity
    abstract fun truckDetailsActivity(): TruckDetailsActivity
}

@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    AppModule::class,
    AndroidInjectorsModule::class
))

interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>() {
        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}