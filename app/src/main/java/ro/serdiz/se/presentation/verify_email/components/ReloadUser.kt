package ro.serdiz.se.presentation.verify_email.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Utils.Companion.print
import ro.serdiz.se.domain.model.Respons.*
import ro.serdiz.se.presentation.profile.ProfileViewModell

@Composable
fun ReloadUser(
    viewModel: ProfileViewModell = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is Loadingl -> ProgressBar()
        is Successl -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navigateToProfileScreen()
                }
            }
        }
        is Failurel -> reloadUserResponse.apply {
            LaunchedEffect(el) {
                print(el)
            }
        }
    }
}