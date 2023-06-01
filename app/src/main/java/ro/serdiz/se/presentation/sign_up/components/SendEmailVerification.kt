package ro.serdiz.se.presentation.sign_up.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utilsl.Companion.print
import ro.serdiz.se.domain.model.Respons
import ro.serdiz.se.presentation.sign_up.SignUpViewModel

@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Respons.Loadingl -> ProgressBar()
        is Respons.Successl -> Unit
        is Respons.Failurel -> sendEmailVerificationResponse.apply {
            LaunchedEffect(el) {
                print(el)
            }
        }

    }
}