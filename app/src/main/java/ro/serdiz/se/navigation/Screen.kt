package ro.serdiz.se.navigation

import ro.serdiz.se.core.Constants.PRODUCT_LIST_SCREEN
import ro.serdiz.se.core.Constants.PRODUCT_DETAILS_SCREEN
//import ro.serdiz.se.core.Constants.PRODUCT_DETAILS_SCREEN1
import ro.serdiz.se.core.Constants.PRODUCT_SEARCH_SCREEN
import ro.serdiz.se.core.Constants.AUTH_SCREEN
//import ro.serdiz.se.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    object ProductListScreen: Screen(PRODUCT_LIST_SCREEN)
    object ProductSearchScreen: Screen(PRODUCT_SEARCH_SCREEN)
    object ProductDetailsScreen: Screen(PRODUCT_DETAILS_SCREEN)
    object AuthScreen: Screen(AUTH_SCREEN)
//    object ProfileScreen: Screen(PROFILE_SCREEN)


}