package ro.serdiz.se.presentation.verify_email

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.presentation.verify_email.components.ReloadUser
import ro.serdiz.se.presentation.verify_email.components.VerifyEmailContent
import ro.serdiz.se.components.TopBar
import ro.serdiz.se.core.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import ro.serdiz.se.core.Constants.VERIFY_EMAIL_SCREEN
import ro.serdiz.se.core.Utilsl.Companion.showMessage
import ro.serdiz.se.presentation.profile.ProfileViewModell
import ro.serdiz.se.presentation.profile.components.RevokeAccessl
import ro.serdiz.se.presentation.verify_email.components.ReloadUser
import ro.serdiz.se.presentation.verify_email.components.VerifyEmailContent

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModell = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
//            TopBar(
//                title = VERIFY_EMAIL_SCREEN,
//                signOut = {
//                    viewModel.signOut()
//                },
//                revokeAccess = {
//                    viewModel.revokeAccessl()
//
//                },
//                photo = "https://example.com/profile-photo.jpg"
//            )
        },
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        scaffoldState = scaffoldState
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToProfileScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )
    RevokeAccessl(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}
