//package ro.serdiz.se.presentation.profile
//
//import androidx.compose.material.Scaffold
//import androidx.compose.material.SnackbarResult.ActionPerformed
//import androidx.compose.material.rememberScaffoldState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.hilt.navigation.compose.hiltViewModel
//import kotlinx.coroutines.launch
//import ro.serdiz.se.core.Constants.REVOKE_ACCESS_MESSAGE
//import ro.serdiz.se.core.Constants.SIGN_OUT
//import ro.serdiz.se.presentation.profile.ProfileViewModel
//import ro.serdiz.se.presentation.profile.components.ProfileContent
//import ro.serdiz.se.presentation.profile.components.ProfileTopBar
//import ro.serdiz.se.presentation.profile.components.RevokeAccess
//import ro.serdiz.se.presentation.profile.components.SignOut
//
//@Composable
//fun ProfileScreen(
//    viewModel: ProfileViewModel = hiltViewModel(),
//    navigateToAuthScreen: () -> Unit
//) {
//    val scaffoldState = rememberScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//
//    Scaffold(
//        topBar = {
//            ProfileTopBar(
//                signOut = {
//                    viewModel.signOut()
//                },
//                revokeAccess = {
//                    viewModel.revokeAccess()
//                }
//            )
//        },
//        content = { padding ->
//            ProfileContent(
//                padding = padding,
//                photoUrl = viewModel.photoUrl,
//                displayName = viewModel.displayName
//            )
//        },
//        scaffoldState = scaffoldState
//    )
//
//    SignOut(
//        navigateToAuthScreen = { signedOut ->
//            if (signedOut) {
//                navigateToAuthScreen()
//            }
//        }
//    )
//
//    fun showSnackBar() = coroutineScope.launch {
//        val result = scaffoldState.snackbarHostState.showSnackbar(
//            message = REVOKE_ACCESS_MESSAGE,
//            actionLabel = SIGN_OUT
//        )
//        if (result == ActionPerformed) {
//            viewModel.signOut()
//        }
//    }
//
//    RevokeAccess(
//        navigateToAuthScreen = { accessRevoked ->
//            if (accessRevoked) {
//                navigateToAuthScreen()
//            }
//        },
//        showSnackBar = {
//            showSnackBar()
//        }
//    )
//}