package com.evgeny.goncharov.sample.multimodule.splash_screen_impl.di

import com.evgeny.goncharov.sample.multimodule.bottom_menu_api.BottomMenuLauncher
import com.evgeny.goncharov.sample.multimodule.di_core.scopes.FeatureScope
import com.evgeny.goncharov.sample.multimodule.navigation.api.CoreNavigationApi
import com.evgeny.goncharov.sample.multimodule.splash_screen_impl.di.contracts.SplashInternal
import com.evgeny.goncharov.sample.multimodule.navigation.base.FeatureNavigationModule
import com.evgeny.goncharov.sample.multimodule.splash_screen_impl.di.modules.ViewModelsSplashModule
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [
        FeatureNavigationModule::class,
        ViewModelsSplashModule::class
    ],
    dependencies = [
        CoreNavigationApi::class
    ]
)
internal interface SplashComponent : SplashInternal {

    @Component.Factory
    interface Factory {

        fun create(
            coreNavigationApi: CoreNavigationApi,
            @BindsInstance bottomMenuLauncher: BottomMenuLauncher
        ): SplashComponent
    }
}