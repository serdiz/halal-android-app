package ro.serdiz.se.presentation.sign_in

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import ro.serdiz.se.core.Utils
import ro.serdiz.se.core.Utilsl.Companion.showMessage
import ro.serdiz.se.navigation.Screenl
import ro.serdiz.se.presentation.auth.components.OneTapSignIn
import ro.serdiz.se.presentation.auth.components.SignInWithGoogle
import ro.serdiz.se.presentation.sign_in.components.SignIn
import ro.serdiz.se.presentation.sign_in.components.SignInContent
import ro.serdiz.se.presentation.sign_in.components.SignInTopBar

@Composable
@ExperimentalComposeUiApi
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToProductSearchScreen: () -> Unit

) {
    val context = LocalContext.current
    class PaddingValuesPreviewProvider : PreviewParameterProvider<PaddingValues> {
        override val values: Sequence<PaddingValues> = sequenceOf(
            PaddingValues(16.dp),
            PaddingValues(8.dp),
            PaddingValues(24.dp)
        )
    }
    Scaffold(
//        topBar = {
//            SignInTopBar()
//        },
        content = { padding ->
            SignInContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen,
                navigateToProfileScreen = navigateToProfileScreen,
                navigateToProductSearchScreen = navigateToProductSearchScreen
                )
        }
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                Utils.print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle { signedIn ->
        if (signedIn) {
            navigateToProfileScreen()
        }
    }

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}
//
//@ExperimentalComposeUiApi
//@Preview
//@Composable
//fun SignInScreenPreview(
//    @PreviewParameter(PaddingValuesPreviewProvider::class) padding: PaddingValues
//) {
//    SignInScreen(
//        navigateToForgotPasswordScreen = {},
//        navigateToSignUpScreen = {},
//        navigateToProfileScreen = {}
//    )
//}













//package ro.serdiz.se.presentation.sign_in
//
//import android.app.Activity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.IntentSenderRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.material.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.platform.LocalContext
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.google.android.gms.auth.api.identity.BeginSignInResult
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.GoogleAuthProvider
//import ro.serdiz.se.core.Utils
//import ro.serdiz.se.core.Utilsl.Companion.showMessage
//import ro.serdiz.se.presentation.auth.components.OneTapSignIn
//import ro.serdiz.se.presentation.auth.components.SignInWithGoogle
//import ro.serdiz.se.presentation.sign_in.components.SignIn
//import ro.serdiz.se.presentation.sign_in.components.SignInContent
//import ro.serdiz.se.presentation.sign_in.components.SignInTopBar
//@Composable
//@ExperimentalComposeUiApi
//fun SignInScreen(
//    viewModel: SignInViewModel = hiltViewModel(),
//    navigateToForgotPasswordScreen: () -> Unit,
//    navigateToSignUpScreen: () -> Unit,
//    navigateToProfileScreen: () -> Unit
//) {
//    val context = LocalContext.current
//
//    Scaffold(
//        topBar = {
//            SignInTopBar()
//        },
//        content = { padding ->
//            SignInContent(
//                padding = padding,
//                signIn = { email, password ->
//                    viewModel.signInWithEmailAndPassword(email, password)
//                                   },
//                oneTapSignIn = {
//                    viewModel.oneTapSignIn()
//                },
//
//
//                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
//                navigateToSignUpScreen = navigateToSignUpScreen,
//                navigateToProfileScreen = navigateToProfileScreen
//            )
//                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            try {
//                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
//                val googleIdToken = credentials.googleIdToken
//                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
//                viewModel.signInWithGoogle(googleCredentials)
//            } catch (it: ApiException) {
//                Utils.print(it)
//            }
//        }
//    }
//
//    fun launch(signInResult: BeginSignInResult) {
//        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
//        launcher.launch(intent)
//    }
//            OneTapSignIn(
//                launch = {
//                    launch(it)
//                }
//            )
//
//            SignInWithGoogle { signedIn ->
//                if (signedIn) {
//                    navigateToProfileScreen()
//                }
//            }
//
//            SignIn(
//                showErrorMessage = { errorMessage ->
//                    showMessage(context, errorMessage)
//                }
//            )
//        }
//    )
//}
//
//
//
//
