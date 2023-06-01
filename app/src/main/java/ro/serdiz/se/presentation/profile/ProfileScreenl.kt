package ro.serdiz.se.presentation.profile
import androidx.compose.runtime.Immutable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.text.input.TextFieldValue
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import ro.serdiz.se.R
import ro.serdiz.se.components.TopBar
import ro.serdiz.se.core.Constants.PROFILE_SCREEN
import ro.serdiz.se.presentation.profile.components.ProfileContentl
import ro.serdiz.se.presentation.profile.components.RevokeAccessl

@Composable
fun ProfileScreenl(
    viewModel: ProfileViewModell = hiltViewModel(),
    username: String,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToProductSearchScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,




//    navigateBack: () -> Unit

) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var username by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                title = "",
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccessl()
                },
                photo = "https://example.com/profile-photo.jpg",
          username = ""
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ProfileContentl(
                    username = username,
                    navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                    navigateToProductSearchScreen = navigateToProductSearchScreen,
                    navigateToProfileScreen = navigateToProfileScreen,
                    navigateToHomeScreen = navigateToHomeScreen

                )

            }
        },

        scaffoldState = scaffoldState
    )

    RevokeAccessl(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
    ProfileImage()
//    Text(
//        modifier = Modifier.clickable { navigateToForgotPasswordScreen() },
//        text = "Изменить пароль",
//        fontSize = 15.sp
//    )
}

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("username") }
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = "Username", modifier = Modifier.width(100.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = Color.Black
            )
        )
    }
}


