package com.evgeny.goncharov.sample.multimodule.splash_screen_impl.navigation

import com.evgeny.goncharov.sample.multimodule.navigation.GlobalRouter
import com.evgeny.goncharov.sample.multimodule.splash_screen_api.SplashLauncher
import com.evgeny.goncharov.sample.multimodule.splash_screen_impl.navigation.screens.SplashScreens

public class SplashLauncherImpl(
    private val globalRouter: GlobalRouter
) : SplashLauncher {

    override fun launch() {
        globalRouter.navigateTo(SplashScreens.startSplashFeature())
    }
}