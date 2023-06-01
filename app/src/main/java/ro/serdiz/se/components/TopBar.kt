package ro.serdiz.se.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ro.serdiz.se.R
import ro.serdiz.se.core.Constants.REVOKE_ACCESS
import ro.serdiz.se.core.Constants.SIGN_OUT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import androidx.compose.ui.Alignment

@Composable
fun TopBar(
    title: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
    photo: String?,
    username: String?

) {
    var openMenu by remember { mutableStateOf(false) }
    val Green = Color(0xFF17C734)
    val dropdownButtonColor = Color.White
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
            .height(230.dp),
        backgroundColor = Green,

        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "dr.halal",
                    modifier = Modifier.padding(
                        start = 1.dp,
                        bottom = 100.dp, end = 250.dp
                    ),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                )
                Image(
                    painter = rememberImagePainter(if (photo.isNullOrEmpty()) R.drawable.ic_user else photo),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).clip(CircleShape)
                )
                IconButton(
                    onClick = {
                        openMenu = !openMenu
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = null,
                    )
                }
            }

        },

        actions = {
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                },
                modifier = Modifier.height(240.dp)
            ) {
                DropdownMenuItem(
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = "Sign Out"
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = "Revoke Access"
                    )
                }
            }

        }
    )
}
