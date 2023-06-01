package ro.serdiz.se.presentation.sign_in.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utilsl.Companion.print
import ro.serdiz.se.domain.model.Respons
import ro.serdiz.se.presentation.sign_in.SignInViewModel

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Respons.Loadingl -> ProgressBar()
        is Respons.Successl -> Unit
        is Respons.Failurel -> signInResponse.apply {
            LaunchedEffect(el) {
                print(el)
                showErrorMessage(el.message)
            }
        }
    }
}