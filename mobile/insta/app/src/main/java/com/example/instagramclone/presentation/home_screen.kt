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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage

import kotlinx.coroutines.launch

import  androidx.lifecycle.viewmodel.compose.viewModel
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
                    message = "Hello World",
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
        InstaStatus(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()

        )
    }

}

@Composable
fun InstaStatus(modifier: Modifier = Modifier) {
    val listData = listOf(1, 2, 3, 4, 5, 6, 7 , 8, 9, 10)


    Box(
        modifier = modifier
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {

            listData.forEach { index ->
                val imageUrl = "https://source.unsplash.com/random/53$index/?face"

                StatusBox(
                    imageUrl = imageUrl,
                    title = "Tile $index",
                )
            }
        }
    }

}

@Composable
fun StatusBox(
    shape: Shape = CircleShape,

    imageUrl: String,
    title: String,
    viewModel: SnackBarScreenViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {

        Box(
            modifier = Modifier
                .padding(4.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val colors = listOf(Color(0xFFFBAA47), Color(0xFFD91A46), Color(0xFFA60F93))

                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(shape)
                        .border(
                            width = 3.dp,
                            brush = Brush.horizontalGradient(colors = colors),
                            shape = shape,
                        )
                        .clickable {
                            // log the click
                            Log.v("StatusBox", "Clicked on $title")
                            viewModel.setMessageShown()
                        }

                )

                Text(text = title)
            }

        }
    }
}


class SnackBarScreenViewModel(
) : ViewModel() {

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }


    }
}

