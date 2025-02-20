package com.example.gym.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    gymName: String,
    location: String,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        CardItem("My Clients", Icons.Default.Settings, Icons.Default.PersonAdd),
        CardItem("My Trainers", Icons.Default.Settings, Icons.Default.PersonAdd),
        CardItem("My Products", Icons.Default.Settings, Icons.Default.PersonAdd),
        CardItem("My Offers", Icons.Default.Settings, Icons.Default.PersonAdd),
        CardItem("Gym Profile", Icons.Default.Settings, Icons.Default.PersonAdd)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display Gym Name and Location at the top
        Text(
            text = "Welcome to $gymName - $location",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Display the list of items
        items.forEach { item ->
            CardItemView(item)
        }
    }
}


@Composable
fun CardItemView(item: CardItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row {
                IconButton(onClick = { /* Handle settings icon click */ }) {
                    Icon(
                        imageVector = item.settingsIcon,
                        contentDescription = "Settings",
                        tint = Color.Gray
                    )
                }
                IconButton(onClick = { /* Handle add icon click */ }) {
                    Icon(
                        imageVector = item.addIcon,
                        contentDescription = "Add",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

data class CardItem(
    val title: String,
    val settingsIcon: ImageVector,
    val addIcon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun PreviewCardListScreen() {
    MainScreen("selva", "selva")
}