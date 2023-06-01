package ro.serdiz.se.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import ro.serdiz.se.domain.model.Respons
import ro.serdiz.se.domain.model.Response

typealias SignUpResponse = Respons<Boolean>
typealias SendEmailVerificationResponse = Respons<Boolean>
typealias SignInResponse = Respons<Boolean>
typealias ReloadUserResponse = Respons<Boolean>
typealias SendPasswordResetEmailResponse = Respons<Boolean>
typealias RevokeAccessResponse1 = Respons<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>
typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepositor {
    val currentUser: FirebaseUser?
   val isUserAuthenticatedInFirebase: Boolean

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse
    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

    fun signOut()

    suspend fun revokeAccessl(): RevokeAccessResponse1

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}