package ro.serdiz.se.presentation.forgot_password.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utilsl.Companion.print
import ro.serdiz.se.domain.model.Respons.*
import ro.serdiz.se.presentation.forgot_password.ForgotPasswordViewModel

@Composable
fun ForgotPassword(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showResetPasswordMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val sendPasswordResetEmailResponse = viewModel.sendPasswordResetEmailResponse) {
        is Loadingl -> ProgressBar()
        is Successl -> {
            val isPasswordResetEmailSent = sendPasswordResetEmailResponse.data
            LaunchedEffect(isPasswordResetEmailSent) {
                if (isPasswordResetEmailSent) {
                    navigateBack()
                    showResetPasswordMessage()
                }
            }
        }
        is Failurel -> sendPasswordResetEmailResponse.apply {
            LaunchedEffect(el) {
                print(el)
                showErrorMessage(el.message)
            }
        }
    }
}