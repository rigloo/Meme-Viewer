package com.rigosapps.memeviewer

import ImageCard
import android.os.Bundle
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.rigosapps.memeviewer.components.*
import com.rigosapps.memeviewer.helpers.Categories
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
    val memes = memeViewModel.memes
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    val pagerState = rememberPagerState()

//    if (pagerState.currentPage == 50) {
//        memeViewModel.generateMemes()
//
//    }

    if (dialogShown.value) {

        MyDialog(value = "", setShowDialog = { dialogShown.value = it }

        ) {
            Timber.i("Value: $it")
            subredditViewModel.addSubreddit(Subreddit(0, it.replace(" ", "").replace("\n", "")))
        }

    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = { Text(text = currentName) }, actions = {
            MultiToggleButton(
                currentSelection = memeViewModel.category,
                toggleStates = listOf<String>(Categories.HOT, Categories.TOP, Categories.RANDOM),
                onToggleChange = {
                    memeViewModel.category = it
                    memeViewModel.fetchMemes(currentName, 50)
                }
            )
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
        else
            VerticalPager(

                count = memes.size,
            ) { page ->
                // Our page content

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            DarkColor
                        ),
                ) {
                    ImageCard(
                        url = memes[page].url,
                        contentDescription = memes[page].title,
                        title = memes[page].title,
                        author = "",

                        modifier = Modifier.fillMaxSize(.99f),
                        isGif = memes[page].url.endsWith(".gif")


                    ) {
                        //onFav

                        memeViewModel.addMeme(memes[page])


                    }

                }

            }


    }


}






