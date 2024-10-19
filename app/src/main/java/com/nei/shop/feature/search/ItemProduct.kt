package com.nei.shop.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nei.shop.domain.Product
import com.nei.shop.network.formatToCOP
import com.nei.shop.ui.theme.ShopTheme

@Composable
fun ItemProduct(product: Product, onClick: () -> Unit = {}) {
    Row(
        Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            if (product.originalPrice != product.price) {
                Text(
                    text = formatToCOP(product.originalPrice),
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.alpha(0.5f)
                )
            }
            Text(
                text = formatToCOP(amount = product.price),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun ItemProductPreview() {
    ShopTheme {
        Surface {
            ItemProduct(
                Product(
                    id = "0",
                    title = "Product 1",
                    thumbnail = "https://i.pravatar.cc/300",
                    price = 100.0,
                    originalPrice = 200.0
                )
            )
        }
    }
}