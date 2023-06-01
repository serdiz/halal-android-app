package ro.serdiz.se.presentation

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import ro.serdiz.se.R
//import ro.serdiz.se.presentation.MainViewModel
import ro.serdiz.se.navigation.Screenl
import ro.serdiz.se.presentation.sign_in.SignInViewModel

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<SignInViewModel>()
//    private val viewModel1 by viewModels<MainViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ad = listOf(
            R.drawable.img_10,
            R.drawable.img_9,
            R.drawable.img_8,
            R.drawable.img_7,


            )
        setContent {
            val splashFinished = remember { mutableStateOf(false) }

            if (!splashFinished.value) {
                SplashContent {
                    splashFinished.value = true
                }
            } else {
                navController = rememberAnimatedNavController()

                NavGraph( navController)
                AuthState()
            }
        }
    }

    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        if (isUserSignedOut) {
           NavigateToSignInScreen()
        } else {
            if (viewModel.isEmailVerified) {
                NavigateToProfileScreen()
            } else {
                NavigateToVerifyEmailScreen()
            }
        }
    }

    @Composable
    private fun NavigateToSignInScreen() = navController.navigate(Screenl.SignInScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToProfileScreen() = navController.navigate(Screenl.ProfileScreenl.route) {
        popUpTo(navController.graph.id) {
            inclusive = true

        }
    }

    @Composable
    private fun NavigateToVerifyEmailScreen() = navController.navigate(Screenl.VerifyEmailScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun SplashContent(onFinished: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.dr_halal),
                contentDescription = "Splash Screen Logo",
                modifier = Modifier.size(200.dp)
            )
        }

        LaunchedEffect(Unit) {
            delay(2000L)
            onFinished()
        }
    }
}






























//package ro.serdiz.se.presentation
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.delay
//import ro.serdiz.se.R
//import ro.serdiz.se.navigation.NavGraph
//import ro.serdiz.se.presentation.MainViewModel
////import ro.serdiz.se.navigation.Screen.ProfileScreenl
//import ro.serdiz.se.navigation.Screenl
//import ro.serdiz.se.presentation.auth.AuthViewModel
//
//@AndroidEntryPoint
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//class MainActivity : ComponentActivity() {
//    private lateinit var navController: NavHostController
//    private val viewModel by viewModels<AuthViewModel>()
//    private val viewModel1 by viewModels<MainViewModel>()
//
//    @OptIn(ExperimentalAnimationApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
////
//            val splashFinished = remember { mutableStateOf(false) }
//
//            if (!splashFinished.value) {
//                SplashContent {
//                    splashFinished.value = true
//                }
//            } else {
//                navController = rememberAnimatedNavController()
//                NavGraph(navController = navController)
//                checkAuthState()
//                AuthState()
//
//            }
//        }
//    }
//
//    private fun checkAuthState() {
//        if (viewModel.isUserAuthenticated) {
//            navigateToProfileScreen()
//        }
//    }
//
//    private fun navigateToProfileScreen() = navController.navigate(Screenl.ProfileScreenl.route)
//}
//
//@Composable
//fun SplashContent(onFinished: () -> Unit) {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Image(
//            painter = painterResource(R.drawable.dr_halal),
//            contentDescription = "Splash Screen Logo",
//            modifier = Modifier.size(200.dp)
//        )
//    }
//
//
//    LaunchedEffect(Unit) {
//        delay(2000L)
//        onFinished()
//    }
//}
////@Composable
////private fun AuthState() {
////    val isUserSignedOut = viewModel1.getAuthState().collectAsState().value
////    if (isUserSignedOut) {
////        NavigateToSignInScreen()
////    } else {
////        if (viewModel1.isEmailVerified) {
////            NavigateToProfileScreen()
////        } else {
////            NavigateToVerifyEmailScreen()
////        }
////    }
////}
////@Composable
////private fun NavigateToSignInScreen() = navController.navigate(Screenl.SignInScreen.route) {
////    popUpTo(navController.graph.id) {
////        inclusive = true
////    }
////}
////
////@Composable
////private fun NavigateToProfileScreen() = navController.navigate(Screenl.ProfileScreenl.route) {
////    popUpTo(navController.graph.id) {
////        inclusive = true
////    }
////}
////
////@Composable
////private fun NavigateToVerifyEmailScreen() = navController.navigate(Screenl.VerifyEmailScreen.route) {
////    popUpTo(navController.graph.id) {
////        inclusive = true
////    }
////}
