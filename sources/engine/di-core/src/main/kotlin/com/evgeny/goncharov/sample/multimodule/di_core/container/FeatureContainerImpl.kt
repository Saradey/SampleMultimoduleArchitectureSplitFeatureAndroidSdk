package com.evgeny.goncharov.sample.multimodule.di_core.container

import com.evgeny.goncharov.sample.multimodule.di_core.contracts.ReleasableApi
import com.evgeny.goncharov.sample.multimodule.di_core.holder.BaseHolder
import com.evgeny.goncharov.sample.multimodule.di_core.holder.FeatureHolder
import com.evgeny.goncharov.sample.multimodule.di_core.initializer.FeatureHolderInitializer
import com.evgeny.goncharov.sample.multimodule.di_core.initializer.GlobalHolderInitializer
import com.evgeny.goncharov.sample.multimodule.di_core.navigation.BaseLauncher
import com.evgeny.goncharov.sample.multimodule.di_core.navigation.LaunchersApi

internal class FeatureContainerImpl(
    private val globalHolder: MutableMap<Class<*>, BaseHolder<*>> = HashMap(),
    private val featureHolder: MutableMap<Class<ReleasableApi>, FeatureHolder<ReleasableApi>> = HashMap(),
    private val featureLaunchers: MutableMap<Class<*>, BaseLauncher> = HashMap()
) : FeatureContainerManager {

    private var featureInitializer: FeatureHolderInitializer? = null

    override fun getFeatureComponent(key: Class<out ReleasableApi>): ReleasableApi {
        return getFeatureHolder(key).getComponent()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <GC> getGlobalComponent(key: Class<GC>): GC {
        return globalHolder[key]?.getComponent() as GC
            ?: throw IllegalStateException(String.format(FAILED_GET_GLOBAL_HOLDER, key))
    }

    override fun releaseFeatureComponent(key: Class<out ReleasableApi>) {
        featureHolder[key]?.releaseFeature() ?: throw IllegalStateException(
            String.format(
                FAILED_RELEASE_HOLDER,
                key
            )
        )
    }

    override fun init(
        globalInitializer: GlobalHolderInitializer,
        featureInitializer: FeatureHolderInitializer
    ): FeatureContainerManager {
        globalHolder.putAll(globalInitializer.init(this))
        this.featureInitializer = featureInitializer
        return this
    }

    private fun getFeatureHolder(key: Class<out ReleasableApi>): FeatureHolder<out ReleasableApi> {
        if (featureHolder.isEmpty()) {
            initAllFeatureHolder()
        }
        return featureHolder[key]
            ?: throw IllegalStateException(String.format(FAILED_GET_HOLDER, key))
    }

    @Suppress("UNCHECKED_CAST")
    private fun initAllFeatureHolder() {
        featureInitializer?.let { initializer ->
            featureHolder.putAll(
                initializer.init(this) as Map<Class<ReleasableApi>, FeatureHolder<ReleasableApi>>
            )
        }
    }

    override fun <L : BaseLauncher> getFeatureLauncher(key: Class<L>): BaseLauncher {
        if (featureLaunchers.isEmpty()) {
            featureLaunchers.putAll(
                (globalHolder[LaunchersApi::class.java]?.getComponent() as LaunchersApi).provideLaunchers()
            )
        }
        return featureLaunchers[key] ?: error(FAILED_GET_LAUNCHER)
    }

    private companion object {
        private const val FAILED_GET_HOLDER = "failed get feature holder %s"

        private const val FAILED_RELEASE_HOLDER = "failed release feature holder %s"

        private const val FAILED_GET_GLOBAL_HOLDER = "failed get global holder %s"

        private const val FAILED_GET_LAUNCHER = "failed get feature launcher %s"
    }
}