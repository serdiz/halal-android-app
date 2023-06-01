package ro.serdiz.se.presentation.auth.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utils.Companion.print
import ro.serdiz.se.domain.model.Response.*
import ro.serdiz.se.presentation.sign_in.SignInViewModel

@Composable
fun OneTapSignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    println("Onetap sign in activated ${viewModel.oneTapSignInResponse}")
    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Loadings -> ProgressBar()
        is Succes -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
        is Error -> TODO()
        is Success -> TODO()
        Loading -> TODO()
    }
}