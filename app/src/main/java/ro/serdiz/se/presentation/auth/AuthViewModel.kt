//package ro.serdiz.se.presentation.auth
//
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.android.gms.auth.api.identity.SignInClient
//import com.google.firebase.auth.AuthCredential
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import ro.serdiz.se.domain.model.Response
//import ro.serdiz.se.domain.model.Response.Loadings
//import ro.serdiz.se.domain.model.Response.Succes
//import ro.serdiz.se.domain.repository.AuthRepository
//import ro.serdiz.se.domain.repository.OneTapSignInResponse
//import ro.serdiz.se.domain.repository.SignInWithGoogleResponse
//import javax.inject.Inject
//
//@HiltViewModel
//class AuthViewModel @Inject constructor(
//    private val repo: AuthRepository,
//    val oneTapClient: SignInClient
//): ViewModel() {
//    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase
//
//    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Succes(null))
//        private set
//    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Succes(false))
//        private set
//
//    fun oneTapSignIn() = viewModelScope.launch {
//        oneTapSignInResponse = Loadings
//        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
//    }
//
//    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
//        oneTapSignInResponse = Loadings
//        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential)
//    }
//}