package ro.serdiz.se.presentation.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.model.Respons.Loadingl
import ro.serdiz.se.domain.model.Respons.Successl
import ro.serdiz.se.domain.model.Response
import ro.serdiz.se.domain.repository.*
import javax.inject.Inject
import ro.serdiz.se.domain.repository.AuthRepositor

import androidx.lifecycle.viewModelScope


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepositor,
    val oneTapClient: SignInClient
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(Successl(false))
        private set
    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Succes(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Response.Succes(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loadings
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        oneTapSignInResponse = Response.Loadings
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential)
    }
    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Loadingl
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }
}






