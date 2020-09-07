package com.devsparkle.posts.util

import com.devsparkle.posts.core.di.components.AppComponent
import com.devsparkle.posts.core.di.modules.AdapterModule
import com.devsparkle.posts.core.di.modules.DataSourceModule
import com.devsparkle.posts.core.di.modules.NetworkModule
import com.devsparkle.posts.core.di.modules.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        NetworkModule::class,
        DataSourceModule::class,
        AdapterModule::class,
        TestPropertyModule::class
    ]
)
interface TestAppComponent : AppComponent