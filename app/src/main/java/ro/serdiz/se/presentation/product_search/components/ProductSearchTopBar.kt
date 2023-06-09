package ro.serdiz.se.presentation.product_search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job
import ro.serdiz.se.core.Constants.NO_TITLE
import ro.serdiz.se.core.Constants.SEARCH

@Composable
@ExperimentalComposeUiApi
fun ProductSearchTopBar(
    search: TextFieldValue,
    onSearchTextChanged: (newSearchText: TextFieldValue) -> Unit,
    onCloseIconClick: () -> Unit,
    navigateBack: () -> Unit,
) {
    val focusRequester = FocusRequester()
    val keyboard = LocalSoftwareKeyboardController.current
    val Green = Color(0xFF17C734)

    TopAppBar(
        title = {
            Text(
                text = NO_TITLE
            )

        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 2.dp
                    )
                    .focusRequester(focusRequester),
                value = search,
                onValueChange = { newSearchText ->
                    onSearchTextChanged(newSearchText)
                },
                placeholder = {
                    Text(
                        text = SEARCH
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Green,
                    unfocusedIndicatorColor = Green,
                    backgroundColor = Green,
                    cursorColor = LocalContentColor.current.copy(
                        alpha = LocalContentAlpha.current
                    ),
                    placeholderColor = Color.LightGray
                ),
                trailingIcon = {
                    IconButton(
                        onClick = onCloseIconClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                )
            )
            LaunchedEffect(Unit) {
                coroutineContext.job.invokeOnCompletion {
                    focusRequester.requestFocus()
                }
            }
        },
        backgroundColor = Green
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
@Preview

fun ProductSearchTopBarPreview() {
    ProductSearchTopBar(
        search = TextFieldValue(),
        onSearchTextChanged = {},
        onCloseIconClick = {},
        navigateBack = {}
    )
}
