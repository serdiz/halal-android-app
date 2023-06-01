//package ro.serdiz.se.presentation.profile.components
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.hilt.navigation.compose.hiltViewModel
//import ro.serdiz.se.components.ProgressBar
//import ro.serdiz.se.core.Utils.Companion.print
//import ro.serdiz.se.domain.model.Response.*
//import ro.serdiz.se.presentation.profile.ProfileViewModel
//
//@Composable
//fun RevokeAccess(
//    viewModel: ProfileViewModel = hiltViewModel(),
//    navigateToAuthScreen: (accessRevoked: Boolean) -> Unit,
//    showSnackBar: () -> Unit
//) {
//    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
//        is Loadings -> ProgressBar()
//        is Succes -> revokeAccessResponse.data?.let { accessRevoked ->
//            LaunchedEffect(accessRevoked) {
//                navigateToAuthScreen(accessRevoked)
//                navigateToAuthScreen(false)
//
//            }
//        }
//        is Failure -> LaunchedEffect(Unit) {
//            print(revokeAccessResponse.e)
//            showSnackBar()
//        }
////        is Error -> TODO()
////        is Success -> TODO()
////        is Loading -> TODO()
//        else -> {}
//    }
//}