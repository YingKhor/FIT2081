package com.fit2081.labweek2

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample() {
    // Create a TopAppBarState object to control the behavior of the TopAppBar.
    // rememberTopAppBarState() is a composable function that creates a TopAppBarState that is remembered across compositions.
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    // The onBackPressedDispatcher is used to handle the back button press in the app.
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        topBar = {
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
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Week 2 Lab."
        )
    }
}
