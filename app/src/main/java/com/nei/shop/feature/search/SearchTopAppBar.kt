@file:OptIn(ExperimentalMaterial3Api::class)

package com.nei.shop.feature.search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(
    onSearch: (String) -> Unit = {},
    history: Set<String> = emptySet(),
    onHistory: (String) -> Unit = {},
) {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val animateDp by animateDpAsState(
        if (expanded) 0.dp else 16.dp,
        label = "animateDpPadding"
    )

    val onSearchAction: (String) -> Unit = {
        expanded = false
        onHistory(query)
        onSearch(query)
    }
    Spacer(
        Modifier
            .background(Color.Yellow)
            .windowInsetsPadding(SearchBarDefaults.windowInsets)
            .fillMaxWidth()
            .height(72.dp)
    )
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = animateDp),
        inputField = {
            SearchBarDefaults.InputField(
                modifier = Modifier,
                query = query,
                onQueryChange = { query = it },
                onSearch = onSearchAction,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text("Busca en mercadolibre") },
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
                    } else if (query.isNotBlank()) {
                        IconButton(onClick = {
                            query = ""
                            onSearch(query)
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            history.forEach { resultText ->
                ListItem(
                    headlineContent = { Text(resultText) },
                    leadingContent = { Icon(Icons.Filled.AccessTime, contentDescription = null) },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    modifier = Modifier
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
}