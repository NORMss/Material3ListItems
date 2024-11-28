package ru.normno.mymaterial3listitems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.normno.mymaterial3listitems.ui.theme.MyMaterial3ListItemsTheme

enum class FruitCategory {
    BERRY, TROPICAL, CITRUS, DRUPES, MELON,
}

data class Fruit(
    val color: Color,
    val name: String,
    val description: String,
    val category: FruitCategory,
    val isSelected: Boolean = false,
)

private val SAMPLE_LIST = listOf(
    Fruit(
        Color(0XFFE4D96F),
        "Strawberry",
        "Small, sweet, and juicy red berry.",
        FruitCategory.BERRY,
        false
    ),
    Fruit(
        Color(0XFFFFAF4D),
        "Orange",
        "Citrus fruit rich in vitamin C.",
        FruitCategory.CITRUS,
        false
    ),
    Fruit(Color(0XFFd5ff00), "Banana", "Soft, sweet tropical fruit.", FruitCategory.TROPICAL, true),
    Fruit(
        Color(0XFFa9c77e),
        "Kiwi",
        "Small fruit with green flesh and a tangy taste.",
        FruitCategory.TROPICAL,
        false
    ),
    Fruit(
        Color(0XFFFD6F7D),
        "Watermelon",
        "Large, juicy melon with a green rind and red flesh.",
        FruitCategory.MELON,
        false
    ),
    Fruit(
        Color(0XFFAF6297),
        "Blackberry",
        "Small, tart berry that is dark purple to black.",
        FruitCategory.BERRY,
        true
    ),
    Fruit(
        Color(0XFFCF0234),
        "Cherry",
        "Small, round, sweet or tart drupe.",
        FruitCategory.DRUPES,
        false
    ),
    Fruit(
        Color(0XFFF9BF58),
        "Mango",
        "Tropical fruit with sweet, juicy orange flesh.",
        FruitCategory.TROPICAL,
        true
    ),
    Fruit(
        Color(0XFFF4D81C),
        "Lemon",
        "Sour citrus fruit, often used for its juice.",
        FruitCategory.CITRUS,
        false
    ),
    Fruit(Color(0XFFEFFFEF), "Honeydew", "Sweet green-fleshed melon.", FruitCategory.MELON, false),
    Fruit(
        Color(0XFFEC5690),
        "Raspberry",
        "Soft, sweet berry with a tart edge.",
        FruitCategory.BERRY,
        true
    ),
    Fruit(
        Color(0XFFFF7431),
        "Papaya",
        "Soft, tropical fruit with orange flesh.",
        FruitCategory.TROPICAL,
        false
    ),
    Fruit(
        Color(0XFFFEEA63),
        "Pineapple",
        "Tropical fruit with spiky skin and sweet, tangy flesh.",
        FruitCategory.TROPICAL,
        true
    ),
    Fruit(
        Color(0XFF00FF00),
        "Lime",
        "Small, green, and very sour citrus fruit.",
        FruitCategory.CITRUS,
        false
    ),
    Fruit(
        Color(0XFFFFE5B4),
        "Peach",
        "Soft, fuzzy fruit with sweet yellow or white flesh.",
        FruitCategory.DRUPES,
        true
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMaterial3ListItemsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var fruits by remember {
                        mutableStateOf(SAMPLE_LIST)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = innerPadding,
                    ) {
                        items(
                            items = fruits,
                            key = {
                                it.name
                            }
                        ) { fruit ->
                            ListItem(
                                headlineContent = {
                                    Text(text = fruit.name)
                                },
                                supportingContent = {
                                    Text(text = fruit.description)
                                },
                                overlineContent = {
                                    Text(text = fruit.category.name)
                                },
                                leadingContent = {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = null,
                                        tint = fruit.color,
                                    )
                                },
                                trailingContent = {
                                    Checkbox(
                                        checked = fruit.isSelected,
                                        onCheckedChange = {
                                            fruits = fruits.map { currentFruit ->
                                                if (currentFruit == fruit)
                                                    currentFruit.copy(isSelected = !currentFruit.isSelected)
                                                else currentFruit
                                            }
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        fruits = fruits.map { currentFruit ->
                                            if (currentFruit == fruit)
                                                currentFruit.copy(isSelected = !currentFruit.isSelected)
                                            else currentFruit
                                        }
                                    }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}