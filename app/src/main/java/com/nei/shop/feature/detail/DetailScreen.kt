@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)

package com.nei.shop.feature.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nei.shop.LocalSharedTransitionScope
import com.nei.shop.feature.detail.model.ProductDetail
import com.nei.shop.network.formatToCOP
import com.nei.shop.ui.theme.ShopTheme

@Composable
fun DetailScreen(
    product: ProductDetail,
    // TODO: check null safety to preview
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    onBackClick: () -> Unit = {}
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 16.dp, horizontal = 32.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shadowElevation = 4.dp,
                tonalElevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.then(
                    if (animatedVisibilityScope != null && sharedTransitionScope != null) {
                        with(sharedTransitionScope) {
                            Modifier.sharedElement(
                                state = rememberSharedContentState(key = "product-image-${product.id}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
                    } else Modifier
                )
            ) {
                AsyncImage(
                    model = product.thumbnail.replace("-I", "-O"),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (product.originalPrice != product.price) {
                Text(
                    text = formatToCOP(product.originalPrice),
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(0.5f)
                )
            }
            Text(
                text = formatToCOP(amount = product.price),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview(modifier: Modifier = Modifier) {
    ShopTheme {
        DetailScreen(
            product = ProductDetail(
                title = "Product 1",
                thumbnail = "https://i.pravatar.cc/300",
                price = 100.0,
                originalPrice = 200.0,
                id = "1"
            )
        )
    }
}