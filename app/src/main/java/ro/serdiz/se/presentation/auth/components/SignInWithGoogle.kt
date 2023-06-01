package ro.serdiz.se.presentation.auth.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utils.Companion.print
import ro.serdiz.se.domain.model.Response.*
import ro.serdiz.se.navigation.Screenl
import ro.serdiz.se.presentation.sign_in.SignInViewModel

@Composable
fun SignInWithGoogle(
    viewModel: SignInViewModel = hiltViewModel(),
//    navigateToHomeScreen: (signedIn: Boolean) -> Unit,
    navigateToProfileScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loadings -> ProgressBar()
        is Succes -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToProfileScreen(signedIn)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
        is Error -> TODO()
        is Success -> TODO()
        Loading -> TODO()
    }
}