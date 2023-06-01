//package ro.serdiz.se.presentation
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import ro.serdiz.se.domain.repository.AuthRepositor
//import javax.inject.Inject
//
//@HiltViewModel
//class   MainViewModel @Inject constructor(
//    private val repo: AuthRepositor
//): ViewModel() {
//    init {
//        getAuthState()
//    }
//
//    fun getAuthState() = repo.getAuthState(viewModelScope)
//
//    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
//}