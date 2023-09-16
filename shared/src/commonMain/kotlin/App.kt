import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.ProductListUI
import viewmodel.ProductListViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val viewModel: ProductListViewModel = getViewModel(Unit, viewModelFactory {
            ProductListViewModel()
        })
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Easykart",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                )
            }
        ) {
            ProductListUI(viewModel)
        }

    }
}




expect fun getPlatformName(): String