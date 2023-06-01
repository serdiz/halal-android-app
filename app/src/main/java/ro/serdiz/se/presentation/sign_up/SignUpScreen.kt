package ro.serdiz.se.presentation.sign_up

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.core.Constants.VERIFY_EMAIL_MESSAGE
import ro.serdiz.se.core.Utilsl.Companion.showMessage
import ro.serdiz.se.presentation.sign_up.components.SendEmailVerification
import ro.serdiz.se.presentation.sign_up.components.SignUp
import ro.serdiz.se.presentation.sign_up.components.SignUpContent

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = { email, password ->
                    viewModel.signUpWithEmailAndPassword(email, password)
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        }
    )

//    SendEmailVerification()
}