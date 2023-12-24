package com.example.amphibians.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.amphibians.R
import coil.compose.AsyncImage

import com.example.amphibians.model.AmpData
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen( ampUiState: AmpUiState ,
    retryAction: ( ) -> Unit ,
    modifier: Modifier = Modifier
)



{

    when(ampUiState){

        is  AmpUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmpUiState.Success  ->  PhotosGridScreen(ampUiState.photos    , modifier)
        is  AmpUiState.Error -> ErrorScreen(retryAction ,modifier = modifier.fillMaxSize())

    }





}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier){



    Image(painter = painterResource(id = R.drawable.loading_img),
        contentDescription ="loading image " )



}


@Composable
fun ErrorScreen(retryAction: () -> kotlin.Unit , modifier: Modifier = Modifier)
{


    Column(modifier = modifier ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment =  Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "loading failed ", modifier = Modifier.padding(16.dp))

        Button(onClick = { }) {
            Text("to be implemented ")
        }

    }


}

@Composable
fun PhotosGridScreen(photos: List<AmpData>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = photos) {
                photo -> MarsPhotoCard(photo,
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()

        )
        }
    }
}


@Composable
fun MarsPhotoCard(photo: AmpData, modifier: Modifier = Modifier) {


    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Text(text = photo.name + "(${photo.type})",
            style  = MaterialTheme.typography.headlineSmall  ,
                    modifier = Modifier.padding( 8.dp)

        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.img_src)
                .crossfade(true)
                .build(),
            contentDescription = "amp phot and description ",

            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            /*
            he Mars photo is not filling the entire screen. To fill available space on screen,
            in HomeScreen.kt in AsyncImage, set the contentScale to ContentScale.Crop.
             */
            contentScale =  ContentScale.Crop  ,

            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Preview
@Composable
fun PhotosGridScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { AmpData("$it", "check" , "","") }
        PhotosGridScreen(mockData)
    }
}