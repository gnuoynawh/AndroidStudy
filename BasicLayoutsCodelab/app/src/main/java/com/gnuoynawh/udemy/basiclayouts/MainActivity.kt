package com.gnuoynawh.udemy.basiclayouts

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.gnuoynawh.udemy.basiclayouts.ui.theme.BasicLayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicLayoutsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = Dp(56f))
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    BasicLayoutsCodelabTheme {
        Surface {
            SearchBar()
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dp(88f))
                .clip(CircleShape)
        )
        Text(
            stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = Dp(22f), bottom = Dp(8f)),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutsCodelabTheme {
        Surface {
            AlignYourBodyElement(
                drawable = R.drawable.ab1_inversions,
                text = R.string.inversions,
                modifier = Modifier.padding(Dp(8f))
            )
        }
    }
}

@Composable
fun FavoriteCard(
    @DrawableRes drawable: Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(Dp(255f))
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(Dp(80f))
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = Dp(16f))
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCardPreview() {
    BasicLayoutsCodelabTheme {
        Surface {
            FavoriteCard(
                drawable = R.drawable.fc2_nature_meditations,
                text = R.string.nature_meditations,
                modifier = Modifier.padding(Dp(8f))
            )
        }
    }
}

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    val alignYourBodyData = listOf(
        R.drawable.ab1_inversions to R.string.inversions,
        R.drawable.ab1_inversions to R.string.inversions,
        R.drawable.ab1_inversions to R.string.inversions,
        R.drawable.ab1_inversions to R.string.inversions,
        R.drawable.ab1_inversions to R.string.inversions,
        R.drawable.ab1_inversions to R.string.inversions,
    ).map { DrawableStringPair(it.first, it.second) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dp(8f)),
        contentPadding = PaddingValues(horizontal = Dp(16f)),
        modifier = modifier
    ) {
        items(alignYourBodyData) {item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyPreview() {
    BasicLayoutsCodelabTheme {
        Surface {
            AlignYourBodyRow()
        }
    }
}

@Composable
fun FavoriteCardGrid(
    modifier: Modifier = Modifier
) {
    val favoriteCardData = listOf(
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
        R.drawable.fc2_nature_meditations to R.string.nature_meditations,
    ).map { DrawableStringPair(it.first, it.second) }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = Dp(16f)),
        horizontalArrangement = Arrangement.spacedBy(Dp(16f)),
        verticalArrangement = Arrangement.spacedBy(Dp(16f)),
        modifier = modifier.height(Dp(168f))
    ) {
        items(favoriteCardData) {item ->
            FavoriteCard(
                drawable = item.drawable,
                text = item.text,
                Modifier.height(Dp(80f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCardGridPreview() {
    BasicLayoutsCodelabTheme {
        Surface {
            FavoriteCardGrid()
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = Dp(40f), bottom = Dp(20f))
                .padding(horizontal = Dp(16f))
        )
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicLayoutsCodelabTheme {
        Column {
            Spacer(Modifier.height(Dp(16f)))
            HomeSection(
                title = R.string.alignyourbody
            ) {
                AlignYourBodyRow()
            }
            HomeSection(
                title = R.string.favoritecard
            ) {
                FavoriteCardGrid()
            }
            Spacer(Modifier.height(Dp(16f)))
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(Dp(16f)))
        SearchBar(Modifier.padding(horizontal = Dp(16f)))
        HomeSection(
            title = R.string.alignyourbody
        ) {
            AlignYourBodyRow()
        }
        HomeSection(
            title = R.string.favoritecard
        ) {
            FavoriteCardGrid()
        }
        Spacer(Modifier.height(Dp(16f)))
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    BasicLayoutsCodelabTheme {
        HomeScreen()
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    BasicLayoutsCodelabTheme {
        SootheBottomNavigation()
    }
}

@Composable
fun MyApp() {
    Scaffold(
        bottomBar = {
            SootheBottomNavigation()
        }
    ) { padding ->
        HomeScreen(Modifier.padding(padding))
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BasicLayoutsCodelabTheme {
        MyApp()
    }
}