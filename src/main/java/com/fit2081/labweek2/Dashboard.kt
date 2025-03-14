package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBarNav() },
                    bottomBar = { BottomBarNav() },
                    floatingActionButton = { FloatingButton() }
                ) { innerPadding ->

                    Column(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Dashboard",
                            style = TextStyle(fontSize = 40.sp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNav() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    CenterAlignedTopAppBar(
        // The colors property is used to customize the appearance of the TopAppBar.
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        // Title displayed in the center of the app bar
        title = {
            Text(
                "Centered Top App Bar",
                maxLines = 1,
                // The Ellipsis property is used to truncate the text if it exceeds the available space.
                overflow = TextOverflow.Ellipsis
            )
        },
        // Navigation icon (back button) with appropriate behavior
        navigationIcon = {
            IconButton(onClick = {
                // onBackPressedDispatcher is used to handle the back button press in the app.
                // It takes the current activity out of the back stack and shows the previous activity.
                onBackPressedDispatcher?.onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        // Action icons (currently a dropdown menu)
        actions = {
            DropDownMenu()
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun BottomBarNav(){
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier.height(60.dp),
        content = {
            IconButton(onClick = { /* TODO: Add action */ }) {
                Icon(Icons.Filled.Check, contentDescription = "Check icon")
            }
            IconButton(onClick = { context.startActivity(Intent(context, SecondActivity::class.java)) }) {
                Icon(Icons.Filled.Home, contentDescription = "Go Home")
            }
            Button(onClick = { /* TODO: Add button action */ }) {
                Text("Click Me")
            }
        }
    )
}

@Composable
fun FloatingButton(){
    val context = LocalContext.current

    FloatingActionButton(onClick = { context.startActivity(Intent(context, AddItem::class.java)) }) {
        Icon(Icons.Filled.Add, contentDescription = "Add something")
    }
}
