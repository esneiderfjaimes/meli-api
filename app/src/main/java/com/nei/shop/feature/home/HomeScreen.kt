package com.nei.shop.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nei.shop.MainViewModel.State
import com.nei.shop.domain.Product
import com.nei.shop.ui.theme.ShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    state: State,
    onSearch: (String) -> Unit,
    history: Set<String> = emptySet(),
    onHistory: (String) -> Unit = {}
) {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val onSearchAction: (String) -> Unit = {
        expanded = false
        onHistory(query)
        onSearch(query)
    }

    Box(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }) {
        Text(text = history.size.toString())
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .semantics { traversalIndex = 0f },
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = onSearchAction,
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text("Hinted search text") },
                        leadingIcon = {
                            if (expanded)
                                IconButton(onClick = { expanded = false }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            else Icon(Icons.Default.Search, contentDescription = null)
                        },
                        trailingIcon = {
                            if (expanded) IconButton(
                                onClick = {
                                    onSearchAction(query)
                                }
                            ) {
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                        },
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
/*                LazyColumn {
                    items(items = history) { resultText ->
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier =
                            Modifier
                                .clickable {
                                    expanded = false
                                    query = resultText
                                    onSearch(query)
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }

                }*/


                Column(Modifier.verticalScroll(rememberScrollState())) {
                    history.forEach { resultText ->
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier =
                            Modifier
                                .clickable {
                                    expanded = false
                                    query = resultText
                                    onSearch(query)
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }) { innerPadding ->
            Text("Products $history")
            when (state) {
                is State.Error -> {
                    Text(
                        state.message,
                        modifier = Modifier
                            .padding(innerPadding)
                            .align(Alignment.Center)
                    )
                }

                State.Loading -> {
                    Text(
                        "Loading...",
                        modifier = Modifier
                            .padding(innerPadding)
                            .align(Alignment.Center)
                    )
                }

                is State.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .semantics { traversalIndex = 1f }
                            .padding(innerPadding)
                    ) {
                        item {
                            Text("Products $history")
                        }

                        items(items = state.products) { product ->
                            Row {

                                AsyncImage(
                                    model = product.thumbnail,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                )
                                Column {
                                    Text(product.title)
                                }
                            }
                        }
                    }
                }

                State.Init -> {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            "Busca algo",
                            modifier = Modifier
                                .padding(innerPadding)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    ShopTheme {
        Home(
            state = State.Success(
                listOf(
                    Product(
                        title = "Product 1",
                        thumbnail = "https://i.pravatar.cc/300"
                    ),
                    Product(
                        title = "Product 2",
                        thumbnail = "https://i.pravatar.cc/300"
                    ),
                    Product(
                        title = "Product 3",
                        thumbnail = "https://i.pravatar.cc/300"
                    ),
                    Product(
                        title = "Product 4",
                        thumbnail = "https://i.pravatar.cc/300"
                    ),
                )
            ),
            onSearch = {},
        )
    }
}


@Preview
@Composable
fun HomePreview2(modifier: Modifier = Modifier) {
    ShopTheme {
        Home(
            state = State.Init,
            onSearch = {},
        )
    }
}