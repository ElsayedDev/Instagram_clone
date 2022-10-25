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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

import  androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
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
            imageUrl = "https://source.unsplash.com/random/536/?face",
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
            Box (
                modifier = Modifier
                    .background(color = Color.Cyan)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)

                    ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)

                ) {
                    // profile image
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
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
            }

            // post image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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


sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem( "Home", R.drawable.insta_camera_inco, "home")
    object Search : BottomNavItem( "Search", R.drawable.insta_camera_inco, "search")
    object Add : BottomNavItem( "Add", R.drawable.insta_camera_inco, "add")
    object Likes : BottomNavItem( "Likes", R.drawable.insta_camera_inco, "likes")
    object Profile : BottomNavItem( "Profile", R.drawable.insta_camera_inco, "profile")
}

@Composable
fun BottomNavigation(navController : NavController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Add,
        BottomNavItem.Likes,
        BottomNavItem.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 12.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
