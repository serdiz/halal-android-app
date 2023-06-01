package ro.serdiz.se.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EmailField(
    email: TextFieldValue,
    onEmailValueChange: (newValue: TextFieldValue) -> Unit
) {
    val focusRequester = FocusRequester()

    Column(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                onEmailValueChange(newValue)
            },
            label = {
                Text(text = "Email")
                            },
            placeholder = {
                Text(text = "Enter your email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
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






