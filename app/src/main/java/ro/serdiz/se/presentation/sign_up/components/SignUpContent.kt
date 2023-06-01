package ro.serdiz.se.presentation.sign_up.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.serdiz.se.components.*
import ro.serdiz.se.core.Constants.ALREADY_USER
import ro.serdiz.se.core.Constants.NO_VALUE
import ro.serdiz.se.core.Constants.SIGN_UP

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    padding: PaddingValues,
    signUp: (email: String, password: String) -> Unit,
    navigateBack: () -> Unit
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }
    val keyboard = LocalSoftwareKeyboardController.current
    var username by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }
    var confirmPassword by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(top = 130.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
                text = "Sign Up",
                fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp
                )
            )
        UsernameField(
            username = username,
            onUsernameValueChange = { newValue ->
                username = newValue
            }
        )
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        ConfirmPassword(
            confirmPassword = confirmPassword,
            onConfirmPasswordValueChange = { newValue ->
                confirmPassword = newValue
            }
        )

        SmallSpacer()
        CustomButton1(
            onClick = {
                keyboard?.hide()
                signUp(email.text, password.text)
            },
                text = SIGN_UP,
        )
        SmallSpacer()
        Text(
            modifier = Modifier.clickable {
                navigateBack()
            },
            text = ALREADY_USER,
            fontSize = 15.sp
        )
    }
}
@Composable
fun CustomButton1(
    onClick: () -> Unit,
    text: String
) {
    val Green = Color(0xFF17C734)
    val TextColor = Color.White
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 4.dp)
            .width(310.dp)
            .height(50.dp),
        shape = RoundedCornerShape(24.dp)

        ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Green,
        )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = TextColor
        )
    }

}