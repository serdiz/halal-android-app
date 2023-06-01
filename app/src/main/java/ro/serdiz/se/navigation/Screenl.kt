package ro.serdiz.se.navigation

import ro.serdiz.se.core.Constants
import ro.serdiz.se.core.Constants.FORGOT_PASSWORD_SCREEN
import ro.serdiz.se.core.Constants.HOME_SCREEN
import ro.serdiz.se.core.Constants.PROFILE_SCREEN
import ro.serdiz.se.core.Constants.SIGN_IN_SCREEN
import ro.serdiz.se.core.Constants.VERIFY_EMAIL_SCREEN
import ro.serdiz.se.core.Constants.SIGN_UP_SCREEN

sealed class Screenl(val route: String) {
    object SignInScreen: Screenl(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screenl(FORGOT_PASSWORD_SCREEN)
    object HomeScreen: Screenl(HOME_SCREEN)
    object SignUpScreen: Screenl(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screenl(VERIFY_EMAIL_SCREEN)
    object ProfileScreenl: Screenl(PROFILE_SCREEN)
    object ProductListScreen: Screenl(Constants.PRODUCT_LIST_SCREEN)
    object ProductSearchScreen: Screenl(Constants.PRODUCT_SEARCH_SCREEN)
    object ProductDetailsScreen: Screenl(Constants.PRODUCT_DETAILS_SCREEN)
    object CategoryProductScreen: Screenl(Constants.CATEGORY_PRODUCTS_SCREEN)
}