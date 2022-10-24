import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instagramclone.presentation.SnackBarScreenViewModel
import kotlinx.coroutines.flow.*


@Composable
fun HomeScreen(
    viewModel: SnackBarScreenViewModel = viewModel()
) {


    val scaffoldState = rememberScaffoldState()

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

        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
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
            .padding(10.dp)
    ) {

        PostBox(
            imageUrl = "https://source.unsplash.com/random/53/?face",
            title = "Tile 1",
        )
    }

}

@Composable
fun PostBox(
    shape: Shape = CircleShape,

    imageUrl: String,
    title: String,
    viewModel: SnackBarScreenViewModel = viewModel()
) {



}
