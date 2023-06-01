package ro.serdiz.se.presentation.sign_up.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utilsl.Companion.print
import ro.serdiz.se.domain.model.Respons.*
import ro.serdiz.se.presentation.sign_up.SignUpViewModel

@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    sendEmailVerification: () -> Unit,
    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResponse) {
        is Loadingl -> ProgressBar()
        is Successl -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Failurel -> signUpResponse.apply {
            LaunchedEffect(el) {
                print(el)
            }
        }
    }
}
