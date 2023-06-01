package ro.serdiz.se.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordField(
    password: TextFieldValue,
    onPasswordValueChange: (newValue: TextFieldValue) -> Unit
) {
    val focusRequester = FocusRequester()
    val passwordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordValueChange,
            label = { Text(text = "Password") },
            placeholder = {
                Text(text = "Enter your Password")
            },
            singleLine = true,
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility.value = !passwordVisibility.value }
                ) {
                    val icon = if (passwordVisibility.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
        )
        Layout(
            content = {
                Divider(color = Color.Black, thickness = 1.dp)
            }
        ) { measurables, constraints ->
            val dividerPlaceable = measurables.first().measure(constraints)
            layout(constraints.maxWidth, dividerPlaceable.height) {
                dividerPlaceable.placeRelative(0, 0)
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}





//
//@Composable
//fun PasswordField(
//    password: TextFieldValue,
//    onPasswordValueChange: (newValue: TextFieldValue) -> Unit
//) {
//    var passwordIsVisible by remember { mutableStateOf(false) }
//
//    OutlinedTextField(
//        value = password,
//        onValueChange = { newValue ->
//            onPasswordValueChange(newValue)
//        },
//        label = {
//            Text(
//                text = PASSWORD_LABEL
//            )
//        },
//        singleLine = true,
//        visualTransformation = if (passwordIsVisible) {
//            VisualTransformation.None
//        } else {
//            PasswordVisualTransformation()
//        },
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Password
//        ),
//        trailingIcon = {
//            val icon = if (passwordIsVisible) {
//                Icons.Filled.Visibility
//            } else {
//                Icons.Filled.VisibilityOff
//            }
//            IconButton(
//                onClick = {
//                    passwordIsVisible = !passwordIsVisible
//                }
//            ) {
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null
//                )
//            }
//        }
//    )
//}