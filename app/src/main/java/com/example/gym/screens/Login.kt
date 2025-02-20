package com.example.gym.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.gym.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    destination: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // Fallback background color
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.gym_girl), // Replace with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Crop the image to fit the screen
        )

        // Login Content at the Bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp, vertical = 16.dp) // Added horizontal padding for side spacing
                .imePadding()
                .align(Alignment.BottomCenter) // Align the Column at the bottom
                .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp)), // Semi-transparent white background with rounded corners
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayLarge,
                color = Color.Black // Change to black for better visibility
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = userId,
                onValueChange = { userId = it },
                label = { Text("Mobile Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp) // Added internal horizontal padding for extra spacing
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                value = password,
                onValueChange = { password = it.trim() },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), // Added internal horizontal padding for extra spacing
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedButton(
                onClick = {
                    if (userId == "1" && password == "s") {
                        destination() // Navigate to home or the next screen
                    } else {
                        Toast.makeText(context, "Invalid User ID or Password", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set button background color to black
                    contentColor = Color.White // Set button text color to white
                )
            ) {
                Text("Login")
            }
        }
    }
}




@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}

//onLoginSuccess = { _, _ -> }