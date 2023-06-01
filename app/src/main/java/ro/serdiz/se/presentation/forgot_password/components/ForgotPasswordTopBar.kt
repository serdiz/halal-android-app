package ro.serdiz.se.presentation.forgot_password.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ro.serdiz.se.components.BackIcon
import ro.serdiz.se.core.Constants.FORGOT_PASSWORD_SCREEN

@Composable
fun ForgotPasswordTopBar(
    navigateBack: () -> Unit
) {
    val Green = Color(0xFF17C734)

    TopAppBar (
        title = {
            Text(
                text = FORGOT_PASSWORD_SCREEN
            )
        },
        backgroundColor = Green,
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}