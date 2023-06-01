package ro.serdiz.se.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmPassword(
    confirmPassword: TextFieldValue,
    onConfirmPasswordValueChange: (newValue: TextFieldValue) -> Unit
) {
    val focusRequester = FocusRequester()

    Column(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { newValue ->
                onConfirmPasswordValueChange(newValue)
            },
            label = {
                Text(text = "Confirm Password")
            },
            placeholder = {
                Text(text = "Confirm your password")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
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
            )
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
