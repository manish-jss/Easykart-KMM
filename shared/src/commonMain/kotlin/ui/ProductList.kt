package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.ProductData
import viewmodel.ProductListViewModel


@Composable
fun ProductListUI(viewModel: ProductListViewModel) {
    val productListState = viewModel.productList.collectAsState()

    AnimatedVisibility(productListState.value.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
            content = {
                items(productListState.value) {
                    ProductListItem(it)
                }
            }
        )
    }

}

@Composable
fun ProductListItem(productItem: ProductData) {

    Card(
        modifier = Modifier.padding(5.dp)
            .clickable { }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {


            KamelImage(
                asyncPainterResource(productItem.image),
                contentDescription = productItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
            )


            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = productItem.title,
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )
            Text(
                text = "RS ${productItem.price}",
                style = MaterialTheme.typography.body2,
                color = Color.Green
            )
            Text(
                text = "Free Delivery",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

