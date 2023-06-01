    package ro.serdiz.se.presentation.sign_in.components
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Column
    import androidx.compose.material.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.viewinterop.AndroidView
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.Button
    import androidx.compose.material.ButtonDefaults
    import androidx.compose.material.MaterialTheme.colors
    import androidx.compose.material.Text
    import androidx.compose.runtime.*
    import androidx.compose.runtime.saveable.rememberSaveable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.ExperimentalComposeUiApi
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.platform.LocalSoftwareKeyboardController
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.hilt.navigation.compose.hiltViewModel
    import com.google.android.gms.auth.api.identity.BeginSignInResult
    import ro.serdiz.se.R
    import ro.serdiz.se.components.EmailField
    import ro.serdiz.se.components.PasswordField
    import ro.serdiz.se.components.ProgressBar
    import ro.serdiz.se.components.SmallSpacer
    import ro.serdiz.se.core.Constants.FORGOT_PASSWORD
    import ro.serdiz.se.core.Constants.NO_ACCOUNT
    import ro.serdiz.se.core.Constants.NO_PRODUCT_NAME
    import ro.serdiz.se.core.Constants.NO_VALUE
    import ro.serdiz.se.core.Constants.PROFILE_SCREEN
    import ro.serdiz.se.core.Constants.SIGN_IN
    import ro.serdiz.se.core.Constants.VERTICAL_DIVIDER
    import ro.serdiz.se.core.Utils
    import ro.serdiz.se.domain.model.Response
    import ro.serdiz.se.presentation.auth.components.SignInButton
    import ro.serdiz.se.presentation.sign_in.SignInViewModel
    import androidx.compose.ui.graphics.Color

    @Composable
    @ExperimentalComposeUiApi
    fun SignInContent(
        padding: PaddingValues,
        signIn: (email: String, password: String) -> Unit,
        navigateToForgotPasswordScreen: () -> Unit,
        navigateToSignUpScreen: () -> Unit,
        navigateToProfileScreen: () -> Unit,
        navigateToProductSearchScreen: () -> Unit,
        oneTapSignIn: () -> Unit
    ) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(NO_VALUE)) }
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dr_halal),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .padding(vertical = 12.dp),
        )
        Text(
            modifier = Modifier.clickable {
                navigateToSignUpScreen()
            },
            text = NO_ACCOUNT,
            fontSize = 16.sp
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .clickable { navigateToForgotPasswordScreen() },
                text = FORGOT_PASSWORD,
                fontSize = 15.sp
            )
        }
        SmallSpacer()
            CustomButton(
                onClick = {
                    keyboard?.hide()
                    signIn(email.text, password.text)
                },
                text = SIGN_IN
            )
        Text(
            text = "Or",
        modifier = Modifier.padding(top = 20.dp),
        color = Color.Black
        )

    SmallSpacer()
            Box(
                modifier = Modifier
                    .padding(5.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                SignInButton(
                    onClick = oneTapSignIn
                )
            }

    }
    }



    @Composable
    fun CustomButton(
        onClick: () -> Unit,
        text: String
    ) {
        val Green = Color(0xFF17C734)
        val TextColor = Color.White
        Button(
            onClick = onClick,
            modifier = Modifier.padding(top = 4.dp)
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




    @OptIn(ExperimentalComposeUiApi::class)
    @Preview
    @Composable
    fun SignInContentPrev() {
        SignInContent(
            padding = PaddingValues(23.dp),
            signIn = { email, password -> /* TODO: Implement sign in logic */ },
            navigateToForgotPasswordScreen = { /* TODO: Implement navigation logic */ },
            navigateToSignUpScreen = { /* TODO: Implement navigation logic */ },
            navigateToProfileScreen = { /* TODO: Implement navigation logic */ },
            oneTapSignIn = { /* TODO: Implement one-tap sign-in logic */ } ,
                    navigateToProductSearchScreen = { }

        )
    }