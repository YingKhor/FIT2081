package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

private const val usernameStatic: String = "123"
private const val passwordStatic: String = "123"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    // State to hold the username input. It's mutable and tied to the composable lifecycle.
    var username by remember { mutableStateOf("123") }
    // State to hold the password input. It's mutable and tied to the composable lifecycle.
    var password by remember { mutableStateOf("123") }

    // This context will be used to show a toast message
    val context = LocalContext.current

    // Surface acts as a container that fills the available space and applies a background color.
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Add image at the top
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.fit2081_logo_yellow),
                contentDescription = "Fit Logo",
                modifier = Modifier.size(200.dp) // Adjust size as needed
            )

            // Add space between logo and username
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField( // Username input field
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth()

            )

            // Add space between username and password
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField( // Password input field
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Check if the username and password are correct
                    if (username == usernameStatic && password == passwordStatic) {
                        // If correct, show a toast message
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context, Dashboard::class.java))
                    } else {
                        // If incorrect, show a toast message
                        Toast.makeText(context, "Incorrect Credentials", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogin(){
    LoginScreen()
}

