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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gym.AndroidViewModelProvider
import kotlinx.coroutines.launch
import com.example.gym.R
import com.example.gym.navgraph.LocalGymViewModel
import com.example.gym.viewmodel.GymViewModel

@Composable
fun LoginScreen(
    destination: () -> Unit, // Navigation callback
    modifier: Modifier = Modifier
) {
    val viewModel = LocalGymViewModel.current // Access the ViewModel through CompositionLocal
    var userId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    // Observe ViewModel states
    val gymResponse by viewModel.gymResponseState.collectAsState()
    val errorMessage by viewModel.error.collectAsState()
    val loginSuccess by viewModel.loginSuccess.collectAsState()

    // Reset login success when entering this screen
    SideEffect {
        viewModel.resetLoginSuccess()  // Reset loginSuccess to false when the login screen is displayed
    }

    // Show Toast if error message exists
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    // Handle login success and navigate
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            destination() // Navigate to selection screen
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lscreenbg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.Black
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
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        value = password,
                        onValueChange = { password = it.trim() },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    ElevatedButton(
                        onClick = {
                            if (userId.isNotBlank() && password.isNotBlank()) {
                                viewModel.login(userId, password) // Trigger login
                            } else {
                                Toast.makeText(context, "Enter valid details", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Login")
                    }
                }
            }
        }
    }
}







@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}

//onLoginSuccess = { _, _ -> }