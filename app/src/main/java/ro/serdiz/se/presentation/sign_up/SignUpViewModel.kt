package ro.serdiz.se.presentation.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.model.Respons
import ro.serdiz.se.domain.repository.AuthRepositor
import ro.serdiz.se.domain.repository.SendEmailVerificationResponse
import ro.serdiz.se.domain.repository.SignUpResponse
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepositor

): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Respons.Successl(false))
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
        Respons.Successl(
            false
        )
    )
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Respons.Loadingl
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Respons.Loadingl
        sendEmailVerificationResponse = repo.sendEmailVerification()
    }
}