package com.devsparkle.posts.core

import android.app.Application
import com.devsparkle.posts.core.di.components.AppComponent
import com.devsparkle.posts.core.di.components.DaggerAppComponent

class PostsApplication : Application() {
    companion object {
        lateinit var instance: PostsApplication
            private set
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().build()
    }
}