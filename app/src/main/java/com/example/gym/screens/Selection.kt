package com.example.gym.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gym.AndroidViewModelProvider
import com.example.gym.viewmodel.GymViewModel
import com.example.gym.network.Gym
import androidx.compose.runtime.getValue

@Composable
fun SelectionScreen(
    destination: (String, String) -> Unit,
    viewModel: GymViewModel = viewModel(factory = AndroidViewModelProvider.Factory) // Use Hilt for dependency injection
) {
    val gymState by viewModel.gymResponseState.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // White background
    ) {
        when {
            loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            error != null -> {
                Text(
                    text = "Error: ${error}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            gymState != null -> {
                GymCardList(
                    gyms = gymState!!.result,
                    onGymClick = destination // Pass the navigation lambda
                )
            }
        }
    }
}

@Composable
fun GymCardList(
    gyms: List<Gym>,
    onGymClick: (String, String) -> Unit // Add a lambda for click handling
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        gyms.forEach { gym ->
            GymCard(
                gym = gym,
                onClick = onGymClick // Pass the lambda to GymCard
            )
        }
    }
}

@Composable
fun GymCard(
    gym: Gym,
    onClick: (String, String) -> Unit // Add a lambda for click handling
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(gym.gymName, gym.location) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Black // Black card
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = gym.gymName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // White text
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = gym.location,
                    fontSize = 14.sp,
                    color = Color.LightGray // Slight contrast
                )
            }
            Text(
                text = "${gym.memberCount} Members",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White // White text
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewGymCard() {
    GymCardList(placeholderGyms, {s, g ->})
}

val placeholderGyms = listOf(
    Gym(
        id = 1,
        gymName = "Anytime Fitness",
        location = "Chennai",
        gymId = 52178887,
        memberCount = 1
    ),
    Gym(
        id = 2,
        gymName = "Deccan Fit",
        location = "Delhi",
        gymId = 13914649,
        memberCount = 1
    ),
    Gym(
        id = 3,
        gymName = "RJK Unisex Gym",
        location = "Mumbai",
        gymId = 69573478,
        memberCount = 3
    ),
    Gym(
        id = 4,
        gymName = "Mittal Muscle Fitness",
        location = "Kanpur",
        gymId = 86966844,
        memberCount = 1
    )
)