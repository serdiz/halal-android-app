package ro.serdiz.se.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.model.Respons
import ro.serdiz.se.domain.repository.AuthRepositor
import ro.serdiz.se.domain.repository.ReloadUserResponse
import ro.serdiz.se.domain.repository.RevokeAccessResponse1
import javax.inject.Inject

@HiltViewModel
class ProfileViewModell @Inject constructor(
    private val repo: AuthRepositor
): ViewModel() {
    var revokeAccessResponse1 by mutableStateOf<RevokeAccessResponse1>(Respons.Successl(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Respons.Successl(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Respons.Loadingl
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccessl() = viewModelScope.launch {
        revokeAccessResponse1 = Respons.Loadingl
        revokeAccessResponse1 = repo.revokeAccessl()
    }
}