import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import  androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.SnackBarScreenViewModel
import kotlinx.coroutines.flow.*


@Composable
fun HomeScreen(
    viewModel: SnackBarScreenViewModel = viewModel()
) {


    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()


    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = viewModel.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
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

        },
        bottomBar = { BottomNavigation(navController) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()

                .verticalScroll(rememberScrollState())
        ) {
            InstaStatus()

            PostWidget(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            )
        }

    }

}


@Composable
fun PostWidget(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
//            .padding(10.dp)
    ) {

        PostBox(
            imageUrl = "https://source.unsplash.com/random/506/?face",
            title = "Tile 1",
        )
    }

}

@Composable
fun PostBox(

    imageUrl: String,
    title: String,
    viewModel: SnackBarScreenViewModel = viewModel()
) {

    // container of the post
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.showSnackBarMessage("Clicked on $title")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            // post header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                // profile image
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                // profile name
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
            }


            ImagePreviewWidget( imageUrl = imageUrl)

            // post footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                // like button
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.insta_camera_inco),
                        contentDescription = "Like",
                        modifier = Modifier.size(16.dp)
                    )
                }

                // comment button
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.insta_camera_inco),
                        contentDescription = "Comment",
                        modifier = Modifier.size(16.dp)
                    )
                }

                // share button
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.insta_camera_inco),
                        contentDescription = "Share",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }

}


@Composable
fun ImagePreviewWidget(
    imageUrl : String
){
    var scale by remember { mutableStateOf(1f) }
    val state = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
        if(scale < 1){
            scale = 1F
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .transformable(state = state)
//            .pointerInput(Unit) {
//                detectTransformGestures { _, _, zoom, _ ->
//                    scale = when {
//                        scale < 0.5f -> 0.5f
//                        scale > 3f -> 3f
//                        else -> scale * zoom
//                    }
//                }
//            }
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = "Post Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
//                        .height(300.dp)
        )
    }
}
