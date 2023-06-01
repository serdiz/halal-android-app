package ro.serdiz.se.core

object Constants {
    //App
    const val TAG = "AppTag"

    //Firestore
    const val PRODUCT = "product"
    const val PRODUCT_NAME = "product_name"
    const val CATEGORY_ID = "id"
    const val CATEGORY = "category"

    //Screens
    const val PRODUCT_LIST_SCREEN = "Product List"
    const val PRODUCT_SEARCH_SCREEN = "Product Search"
    const val PRODUCT_DETAILS_SCREEN = "Product Details"
    const val CATEGORY_PRODUCTS_SCREEN = "Category Products"
//    const val PRODUCT_DETAILS_SCREEN1 = "Product Details1"


    //Placeholders
    const val SEARCH = "Search..."

    //Arguments
    const val PRODUCT_NAME1 = "product_"
    const val PRODUCT_DESCRIPTION = "product_description"


    //Values
    const val NO_SEARCH = ""
    const val NO_PRODUCT_NAME = ""
//    const val NO_PRODUCT_DESCRIPTION = "product_description"

    const val NO_TITLE = ""

    //Messages
    const val NO_PRODUCTS_FOUND = "No products found."





        //Collection References
        const val USERS = "users"

        //User fields
        const val DISPLAY_NAME = "displayName"
        const val EMAIL = "email"
        const val PHOTO_URL = "photoUrl"
        const val CREATED_AT = "createdAt"

        //Names
        const val SIGN_IN_REQUEST = "signInRequest"
        const val SIGN_UP_REQUEST = "signUpRequest"

        //Buttons
        const val SIGN_IN_WITH_GOOGLE = "Sign in with"
        const val SIGN_OUT = "Sign-out"
    const val REVOKE_ACCESS = "Revoke Access"



    //Screens
        const val AUTH_SCREEN = "Authentication"
//        const val PROFILE_SCREEN = "Profile"

        //Messages
//        const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
    const val SIGN_IN = "Sign in"
    const val RESET_PASSWORD = "Reset"
    const val SIGN_UP = "Sign up"



    //Screens
    const val SIGN_IN_SCREEN = "Sign in"
    const val FORGOT_PASSWORD_SCREEN = "Forgot password"
    const val SIGN_UP_SCREEN = "Sign up"
    const val VERIFY_EMAIL_SCREEN = "Verify email"
    const val PROFILE_SCREEN = "Profile"
    const val HOME_SCREEN = "Home"


    //Labels
    const val EMAIL_LABEL = "Email"
    const val PASSWORD_LABEL = "Password"

    //Useful
    const val NO_VALUE = ""
    const val VERTICAL_DIVIDER = "|"

    //Texts
    const val FORGOT_PASSWORD = "Forgot password?"
    const val NO_ACCOUNT = "Don't Have An Account? Sign up."
    const val ALREADY_USER = "Already a user? Sign in."
    const val WELCOME_MESSAGE = "Welcome to our app."
    const val ALREADY_VERIFIED = "Already verified?"
    const val SPAM_EMAIL = "If not, please also check the spam folder."

    //Messages
    const val VERIFY_EMAIL_MESSAGE = "We've sent you an email with a link to verify the email."
    const val EMAIL_NOT_VERIFIED_MESSAGE = "Your email is not verified."
    const val RESET_PASSWORD_MESSAGE = "We've sent you an email with a link to reset the password."
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
    const val ACCESS_REVOKED_MESSAGE = "Your access has been revoked."

    //Error Messages
    const val SENSITIVE_OPERATION_MESSAGE = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."

    // API
    const val BASE_URL = "http://192.168.1.71:8000/"
    const val PRODUCTS_ENDPOINT = "products"
    const val CATEGORIES_ENDPOINT = "categories"


}

