@file:OptIn(ExperimentalMaterial3Api::class)

package com.nei.shop.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nei.shop.domain.Product
import com.nei.shop.feature.search.SearchViewModel.State
import com.nei.shop.ui.theme.ShopTheme

@Composable
fun SearchScreen(
    state: State,
    onSearch: (String) -> Unit = {},
    history: Set<String> = emptySet(),
    onHistory: (String) -> Unit = {},
    onProductClick: (Product) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        when (state) {
            is State.Error -> {
                Message(
                    message = state.message,
                    paddingValues = innerPadding
                )
            }

            State.Loading -> {
                Message(
                    message = "Loading...",
                    paddingValues = innerPadding
                )
            }

            is State.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(top = 72.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(items = state.products) { product ->
                        ItemProduct(product) {
                            onProductClick(product)
                        }
                    }
                }
            }

            State.Init -> {
                Message(
                    message = "Busca algo",
                    paddingValues = innerPadding
                )
            }
        }
    }

    TopAppBar(
        onSearch = onSearch,
        history = history,
        onHistory = onHistory
    )
}

@Composable
fun Message(paddingValues: PaddingValues, message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            message,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Preview
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    ShopTheme {
        SearchScreen(
            state = State.Success(
                listOf(
                    Product(
                        id = "1",
                        title = "Product 1",
                        thumbnail = "https://i.pravatar.cc/300",
                        price = 100.0,
                        originalPrice = 200.0
                    ),
                    Product(
                        id = "2",
                        title = "Product 2",
                        thumbnail = "https://i.pravatar.cc/300",
                        price = 100.0,
                        originalPrice = 200.0
                    ),
                    Product(
                        id = "3",
                        title = "Product 3",
                        thumbnail = "https://i.pravatar.cc/300",
                        price = 100.0,
                        originalPrice = 200.0
                    ),
                    Product(
                        id = "4",
                        title = "Product 4",
                        thumbnail = "https://i.pravatar.cc/300",
                        price = 100.0,
                        originalPrice = 200.0
                    ),
                )
            ),
        )
    }
}


@Preview
@Composable
fun HomePreview2(modifier: Modifier = Modifier) {
    ShopTheme {
        SearchScreen(
            state = State.Init,
        )
    }
}