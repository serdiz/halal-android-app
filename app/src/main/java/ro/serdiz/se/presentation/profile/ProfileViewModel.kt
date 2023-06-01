//package ro.serdiz.se.presentation.profile
//
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import ro.serdiz.se.domain.model.Response
//import ro.serdiz.se.domain.repository.ProfileRepository
//import ro.serdiz.se.domain.repository.RevokeAccessResponse
//import ro.serdiz.se.domain.repository.SignOutResponse
//import javax.inject.Inject
//
//@HiltViewModel
//class ProfileViewModel @Inject constructor(
//    private val repo: ProfileRepository
//): ViewModel() {
//    val displayName get() = repo.displayName
//    val photoUrl get() = repo.photoUrl
//
//    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Succes(false))
//        private set
//    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Succes(false))
//        private set
//
//
//    fun signOut() = viewModelScope.launch {
//        signOutResponse = Response.Loading
//        signOutResponse = repo.signOut()
//        println("fjnjgn")
//    }
//    fun revokeAccess() = viewModelScope.launch {
//        revokeAccessResponse = Response.Loading
//        revokeAccessResponse = repo.revokeAccess()
//    }
//}