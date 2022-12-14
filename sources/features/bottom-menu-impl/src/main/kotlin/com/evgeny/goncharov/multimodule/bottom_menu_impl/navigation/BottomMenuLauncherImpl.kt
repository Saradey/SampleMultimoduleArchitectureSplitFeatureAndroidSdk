package com.evgeny.goncharov.multimodule.bottom_menu_impl.navigation

import com.evgeny.goncharov.sample.multimodule.bottom_menu_api.BottomMenuLauncher
import com.evgeny.goncharov.sample.multimodule.navigation.GlobalRouter

public class BottomMenuLauncherImpl(
    private val globalRouter: GlobalRouter
) : BottomMenuLauncher {

    override fun launch() {
        globalRouter.newRootScreen(BottomMenuScreens.startFeature())
    }
}