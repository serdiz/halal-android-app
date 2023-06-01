package ro.serdiz.se.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import ro.serdiz.se.core.Constants
import ro.serdiz.se.domain.model.Respons.Failurel
import ro.serdiz.se.domain.model.Respons.Successl
import ro.serdiz.se.domain.model.Response
import ro.serdiz.se.domain.repository.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import com.google.firebase.firestore.FieldValue.serverTimestamp
import kotlinx.coroutines.tasks.await
import ro.serdiz.se.core.Constants.CREATED_AT
import ro.serdiz.se.core.Constants.DISPLAY_NAME
import ro.serdiz.se.core.Constants.EMAIL
import ro.serdiz.se.core.Constants.PHOTO_URL
import ro.serdiz.se.core.Constants.SIGN_IN_REQUEST
import ro.serdiz.se.core.Constants.SIGN_UP_REQUEST
import ro.serdiz.se.core.Constants.USERS
import ro.serdiz.se.domain.model.Response.Failure
import ro.serdiz.se.domain.model.Response.Succes
import ro.serdiz.se.domain.repository.OneTapSignInResponse
import ro.serdiz.se.domain.repository.SignInWithGoogleResponse


@Singleton
class AuthRepositoryImpllog @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(Constants.SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(Constants.SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepositor {
        override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String
    ): SignUpResponse {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override suspend fun sendEmailVerification(): SendEmailVerificationResponse {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ): SignInResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override suspend fun reloadFirebaseUser(): ReloadUserResponse {
        return try {
            auth.currentUser?.reload()?.await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccessl(): RevokeAccessResponse1 {
        return try {
            auth.currentUser?.delete()?.await()
            Successl(true)
        } catch (e: Exception) {
            Failurel(e)
        }
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)


    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Succes(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Succes(signUpResult)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            Response.Succes(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(Constants.USERS).document(uid).set(user).await()
        }
    }
}
fun FirebaseUser.toUser() = mapOf(
    Constants.DISPLAY_NAME to displayName,
    Constants.EMAIL to email,
    Constants.PHOTO_URL to photoUrl?.toString(),
    Constants.CREATED_AT to FieldValue.serverTimestamp()
)