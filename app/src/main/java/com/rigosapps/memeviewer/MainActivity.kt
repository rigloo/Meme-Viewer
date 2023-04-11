package com.rigosapps.memeviewer

import ImageCard
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.rigosapps.memeviewer.components.*
import com.rigosapps.memeviewer.helpers.Categories
import com.rigosapps.memeviewer.helpers.Constants
import com.rigosapps.memeviewer.model.Subreddit
import com.rigosapps.memeviewer.ui.theme.*
import com.rigosapps.memeviewer.viewModels.MemeViewModel
import com.rigosapps.memeviewer.viewModels.SubredditViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            QuoteAppTheme {

                MainScreen()

            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {


    val dialogShown = remember {
        mutableStateOf<Boolean>(false)

    }


    val memeViewModel = viewModel<MemeViewModel>()
    val subredditViewModel = viewModel<SubredditViewModel>()
    val currentName = memeViewModel.currentSubreddit
    val error = memeViewModel.error
    val isLoading = memeViewModel.isLoading
    val memes by memeViewModel.memes.collectAsState(
        initial = emptyList()
    )
    val favMemes by memeViewModel.favMemes.collectAsState(
        initial = emptyList()
    )

    val inFavs = memeViewModel.inFavs
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    val pagerState = rememberPagerState()

    val toShow = if (inFavs) favMemes
    else memes

    if (dialogShown.value) {

        MyDialog(value = "", setShowDialog = { dialogShown.value = it }

        ) {
            Timber.i("Value: $it")
            subredditViewModel.addSubreddit(Subreddit(0, it.replace(" ", "").replace("\n", "")))
        }

    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = { Text(text = currentName) }, actions = {
            MultiToggleButton(currentSelection = memeViewModel.category,
                toggleStates = listOf<String>(Categories.HOT, Categories.TOP, Categories.RANDOM),
                onToggleChange = {
                    memeViewModel.category = it
                    memeViewModel.fetchMemes(currentName, Constants.FETCH_COUNT)
                })
        }, navigationIcon = {


            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }


            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                )
            }

        })
    }, drawerContent = {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DarkPurple)
        ) {
            DrawerHeader()
            DrawerBody(dialogShown = dialogShown) {
                scope.launch { scaffoldState.drawerState.close() }


            }
        }


    }


    ) {

        if (isLoading) MyProgressIndicator()
        else if (error) ErrorMessage()
        else VerticalPager(


            count = toShow.size,
        ) { page ->
            // Our page content
            Timber.e("Current Page: ${pagerState.currentPage}")
//                if ( pagerState.currentPage == Constants.FETCH_COUNT + 1 )
//                    memeViewModel.fe


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        DarkColor
                    ),
            ) {
                val context = LocalContext.current
                ImageCard(
                    url = toShow[page].url,
                    contentDescription = toShow[page].title,
                    title = toShow[page].title,
                    isFaved = toShow[page].key != 0L,

                    modifier = Modifier.fillMaxSize(.99f),
                    isGif = toShow[page].url.endsWith(".gif")


                ) {

                    //onFav
                    if (toShow[page].key == 0L) {
                        memeViewModel.addMeme(toShow[page])
                        Toast.makeText(context, "Added meme to favorites!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        memeViewModel.deleteMeme(toShow[page])
                        Toast.makeText(
                            context, "Removed meme from favorites!", Toast.LENGTH_SHORT
                        ).show()
                    }


                }

            }

        }


    }


}






