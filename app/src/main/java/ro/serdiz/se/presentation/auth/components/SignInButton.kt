package ro.serdiz.se.presentation.auth.components



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.serdiz.se.R
import ro.serdiz.se.core.Constants.SIGN_IN_WITH_GOOGLE
@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp)
//            .align(Alignment.BottomCenter)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.purple_700)
            ),
            onClick = onClick
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SIGN_IN_WITH_GOOGLE,
                    fontSize = 17.sp

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = null,
                    modifier = Modifier
//                        .padding(bottom = 45.dp)
//                        .padding(vertical = 12.dp),
                    )

            }

        }
    }
}

