package ro.serdiz.se.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.serdiz.se.R
import ro.serdiz.se.components.SmallSpacer
import ro.serdiz.se.core.Constants
import ro.serdiz.se.core.Constants.WELCOME_MESSAGE

@Composable
fun ProfileContentl(
    username: String,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToProductSearchScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,

    ) {
    val Green = Color(0xFF17C734)

    Scaffold(

        bottomBar = {
            BottomAppBar(backgroundColor = Green

            ) {
                NavigationIcon(
                    icon = R.drawable.img_6,
                    text = "Search",
                    onClick = navigateToProductSearchScreen
                )
                NavigationIcon(
                    icon = R.drawable.ic_user,
                    text = "Profile",
                    onClick = navigateToProfileScreen
                )
                NavigationIcon(
                    icon = R.drawable.ic_user,
                    text = "Home",
                    onClick = navigateToHomeScreen
                )
            }
        },

    content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(bottom = 4.dp)
                                .padding(vertical = 12.dp)
                                .padding(start = 10.dp)
                        )

                        Text(
                            modifier = Modifier.clickable { navigateToForgotPasswordScreen() },
                            text = "Изменить пароль",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                SmallSpacer()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Изменить язык",
                        fontSize = 18.sp
                    )
                }

                SmallSpacer()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Рейтинг и отзыв",
                        fontSize = 18.sp
                    )
                }

                SmallSpacer()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_5),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Мои запросы",
                        fontSize = 18.sp
                    )
                }

                SmallSpacer()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_4),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        modifier = Modifier.clickable { },
                        text = "О нас",
                        fontSize = 18.sp
                    )
                }
                SmallSpacer()
            }
        }
    }
        )
    }



@Composable
fun NavigationIcon(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }

}


