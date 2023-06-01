package ro.serdiz.se.presentation.profile.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Constants.ACCESS_REVOKED_MESSAGE
import ro.serdiz.se.core.Constants.REVOKE_ACCESS_MESSAGE
import ro.serdiz.se.core.Constants.SENSITIVE_OPERATION_MESSAGE
import ro.serdiz.se.core.Constants.SIGN_OUT
import ro.serdiz.se.core.Utilsl.Companion.print
import ro.serdiz.se.core.Utilsl.Companion.showMessage
import ro.serdiz.se.domain.model.Respons.*
import ro.serdiz.se.presentation.profile.ProfileViewModell

@Composable
fun RevokeAccessl(
    viewModel: ProfileViewModell = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse1) {
        is Loadingl -> ProgressBar()
        is Successl -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failurel -> revokeAccessResponse.apply {
            LaunchedEffect(el) {
                print(el)
                if (el.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }

    }
}