package com.rigosapps.memeviewer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.composeautoresizedtext.AutoResizedText
import com.rigosapps.memeviewer.ui.theme.LightPurple
import com.rigosapps.memeviewer.ui.theme.Pink
import com.rigosapps.memeviewer.viewModels.MemeViewModel
import com.rigosapps.memeviewer.viewModels.SubredditViewModel

@Composable
fun MyDrawer(modifier: Modifier = Modifier) {


}


@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = LightPurple)
            .border(
                border = BorderStroke(2.dp, Color.Transparent), shape = RoundedCornerShape(50),

                ),
        contentAlignment = Alignment.Center

    ) {


        Text(
            text = "Meme Sources",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.align(Alignment.BottomCenter))


    }


}

@Composable
fun DrawerFooter(modifier: Modifier = Modifier) {

    FloatingActionButton(onClick = { /*TODO*/ }) {

    }


}


@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    dialogShown: MutableState<Boolean>,
    dismissDrawer: () -> Unit
) {

    val subredditViewModel = viewModel<SubredditViewModel>()
    val memeViewModel = viewModel<MemeViewModel>()


    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    //val subredditViewModel = ViewModelProvider(viewModelStoreOwner).get(SubredditViewModel::class.java)


    val subredditList by subredditViewModel.subreddits.observeAsState()


    Box(modifier) {

        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            item {
                DrawerItemNonDelete(title = "Favorites", modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                        memeViewModel.fetchFavMemes()
                        dismissDrawer()

                    })
            }

            //if subreddit list not null output this

            subredditList?.let {

                items(count = it.size) { index ->

                    DrawerItem(
                        title = subredditList!![index].title,
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {

                                memeViewModel.fetchMemes(subreddit = it[index].title, count = 50)
                                dismissDrawer()


                            }
                    ) {
                        subredditViewModel.deleteSubreddit(it[index])

                    }
                }
            }


        }






        FloatingActionButton(
            onClick = {
                dialogShown.value = true

            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
        ) {

            Icon(Icons.Filled.Add, contentDescription = "Add a Meme Source")

        }
    }


}


@Composable
fun DrawerItem(modifier: Modifier = Modifier, title: String, onDelete: () -> Unit) {
    val subredditViewModel = viewModel<SubredditViewModel>()

    Card(modifier.fillMaxWidth()) {
        Box(modifier.fillMaxSize()) {

            Text(text = title, fontSize = 30.sp, modifier = Modifier.padding(8.dp))
           // AutoResizedText(text = title, modifier = Modifier.fillMaxSize())

            IconButton(

                onClick = onDelete,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(8.dp),
            ) {
                Icon(

                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Add a Meme Source"
                )

            }

        }

    }


}

@Composable
fun DrawerItemNonDelete(modifier: Modifier = Modifier, title: String) {

    Card(
        modifier
            .fillMaxWidth()
            .background(color = Pink)
    ) {
        Box(modifier.fillMaxSize()) {

            Text(text = title, fontSize = 30.sp, modifier = Modifier.padding(8.dp))

        }

    }


}



