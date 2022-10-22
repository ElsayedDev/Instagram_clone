import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen() {


    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Row(
//                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        var showMenu by remember { mutableStateOf(false) }

                        Box(modifier = Modifier.weight(1f)) {
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.instagram_logo_icon),
                                    contentDescription = "logo",
//                            modifier = Modifier.size(24.dp)

                                )
                            }

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }) {
                                DropdownMenuItem(onClick = { }) {
                                    Text(text = "Followers")
                                    Text(text = "Favorites")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(10.dp))


                    }
                },


                )

        }
    ) {
//        Text(text = "Home Screen")
        InstaStatus()
    }

}

@Composable
fun InstaStatus() {
    val listData = listOf<Int>(1, 2, 4, 6, 5, 5)


    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {

            listData.forEach { index ->
                val imageUrl = "https://source.unsplash.com/random/52$index/?face"

                StatusBox(imageUrl = imageUrl)
            }
        }
    }

}

@Composable
fun StatusBox(shape: Shape = CircleShape, imageUrl : String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {

        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(4.dp)
                .clip(shape)

        ){
    Column {
        AsyncImage(
            model =imageUrl ,
            contentDescription = "Image",
            contentScale = ContentScale.Crop,

            )

        Text(text = "Name")
    }

        }
    }
}

