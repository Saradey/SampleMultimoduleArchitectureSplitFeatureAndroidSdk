package com.evgeny.goncharov.sample.multimodule.di_core.container

import com.evgeny.goncharov.sample.multimodule.di_core.contracts.ReleasableApi
import com.evgeny.goncharov.sample.multimodule.di_core.navigation.BaseLauncher

internal object NotInitializedFeatureContainer : FeatureContainer {
    override fun getFeatureComponent(key: Class<out ReleasableApi>) =
        throw IllegalStateException(ERROR_MASSAGE)

    override fun releaseFeatureComponent(key: Class<out ReleasableApi>) =
        throw IllegalStateException(ERROR_MASSAGE)

    override fun <D> getGlobalComponent(key: Class<D>) = throw IllegalStateException(ERROR_MASSAGE)

    override fun <L : BaseLauncher> getFeatureLauncher(key: Class<L>) =
        throw IllegalStateException(ERROR_MASSAGE)

    private const val ERROR_MASSAGE = "Need initialize FeatureContainer"
}