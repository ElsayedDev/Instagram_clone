import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.instagramclone.R
import com.example.instagramclone.presentation.SnackBarScreenViewModel

@Composable
fun InstaStatus(modifier: Modifier = Modifier) {
    val listData = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


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
                            viewModel.showSnackBarMessage("status with $title clicked")
                        }

                )

                Text(text = title)
            }

        }
    }
}
