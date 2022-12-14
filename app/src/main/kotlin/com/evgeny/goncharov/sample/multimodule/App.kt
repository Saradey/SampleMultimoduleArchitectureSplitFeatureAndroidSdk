package com.evgeny.goncharov.sample.multimodule

import android.app.Application
import com.evgeny.goncharov.sample.multimodule.di_core.engine.DI
import com.evgeny.goncharov.sample.multimodule.initializer.FeatureHolderInitializerImpl
import com.evgeny.goncharov.sample.multimodule.initializer.GlobalHolderInitializerImpl
import java.lang.Exception

internal class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        try {
            DI.initialize(GlobalHolderInitializerImpl(this), FeatureHolderInitializerImpl())
        } catch (exp: Exception) {
            throw RuntimeException("$FAILED_INIT_DI_MESSAGE $exp")
        }
    }

    companion object {
        private const val FAILED_INIT_DI_MESSAGE = "Fail to init DI"
    }
}