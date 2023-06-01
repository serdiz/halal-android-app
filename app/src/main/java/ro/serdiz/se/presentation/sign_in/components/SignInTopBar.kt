package ro.serdiz.se.presentation.sign_in.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import ro.serdiz.se.core.Constants.SIGN_IN_SCREEN

@Composable
fun SignInTopBar() {
    TopAppBar (
        title = {
            Text(
                text = SIGN_IN_SCREEN
            )
        }
    )
}