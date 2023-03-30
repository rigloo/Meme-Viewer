import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rigosapps.memeviewer.components.GifImage
import com.rigosapps.memeviewer.ui.theme.DarkColor
import com.rigosapps.memeviewer.ui.theme.LightPurple
import com.rigosapps.quoteapp.R
import timber.log.Timber


@Composable
fun ImageCard(
    url: String,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    isFaved: Boolean,
    isGif: Boolean,
    onFav: () -> Unit,
) {
    var isFaved by remember { mutableStateOf<Boolean>(isFaved) }
    Card(
        modifier = modifier
            .fillMaxWidth(.5f)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(

                    onDoubleTap = {
                        Timber.e("Double tap meme! Add to Fav database.")
                        isFaved = !isFaved
                        onFav()
                    },

                    onTap = { /* Called on Tap */ }
                )
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {

            if (isGif)
                GifImage(url = url, modifier = Modifier.fillMaxSize())
            else
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.FillBounds
                )
            if (isFaved) {
                Icon(
                    painter = painterResource(R.drawable.favorite_filled_icon),
                    tint = LightPurple,

                    contentDescription = "Like this meme",
                    modifier = Modifier
                        .align(
                            Alignment.TopEnd
                        )
                        .padding(8.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 1690f

                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {

                Column(verticalArrangement = Arrangement.Bottom) {

                    Text(
                        title, style = TextStyle(
                            color = Color.White, fontSize = 25.sp, shadow = Shadow(
                                color = DarkColor, offset = Offset(2F, 4F), blurRadius = 1f
                            )

                        )
                    )


                }

            }


        }
    }

}


