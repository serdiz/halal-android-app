package ro.serdiz.se.di

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ro.serdiz.se.R
import ro.serdiz.se.core.Constants
import ro.serdiz.se.data.repository.AuthRepositoryImpllog
//import ro.serdiz.se.data.repository.ProfileRepositoryImpl
import ro.serdiz.se.domain.repository.AuthRepositor
//import ro.serdiz.se.domain.repository.ProfileRepository
import javax.inject.Named

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore

import dagger.hilt.components.SingletonComponent
import ro.serdiz.se.core.Constants.SIGN_IN_REQUEST
import ro.serdiz.se.core.Constants.SIGN_UP_REQUEST
import javax.inject.Singleton
@Module
@InstallIn(ViewModelComponent::class)
class App {

@Provides
    fun provideFirebaseAuth() = Firebase.auth
    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(Constants.SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build())
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(Constants.SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(Constants.SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(Constants.SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore
    ): AuthRepositor = AuthRepositoryImpllog(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        db = db
    )

//    @Provides
//    fun provideProfileRepository(
//        auth: FirebaseAuth,
//        oneTapClient: SignInClient,
//        signInClient: GoogleSignInClient,
//        db: FirebaseFirestore
//    ): ProfileRepository = ProfileRepositoryImpl(
//        auth = auth,
//        oneTapClient = oneTapClient,
//        signInClient = signInClient,
//        db = db
//    )
}