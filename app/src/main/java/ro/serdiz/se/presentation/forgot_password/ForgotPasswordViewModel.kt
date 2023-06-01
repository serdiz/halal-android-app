package ro.serdiz.se.presentation.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.model.Respons.Loadingl
import ro.serdiz.se.domain.model.Respons.Successl
import ro.serdiz.se.domain.repository.AuthRepositor
import ro.serdiz.se.domain.repository.SendPasswordResetEmailResponse
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repo: AuthRepositor
): ViewModel() {
    var sendPasswordResetEmailResponse by mutableStateOf<SendPasswordResetEmailResponse>(Successl(false))

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        sendPasswordResetEmailResponse = Loadingl
        sendPasswordResetEmailResponse = repo.sendPasswordResetEmail(email)
    }
}