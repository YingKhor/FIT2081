package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fit2081.labweek2.ui.theme.LabWeek2Theme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
                QuizApplication()
            }
        }
    }
}

@Composable
fun QuizApplication(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.fit2081_logo_yellow),
                contentDescription = "Quiz Image",
                modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)
            )

            val context = LocalContext.current
            var checked1 by remember { mutableStateOf(false) }
            var checked2 by remember { mutableStateOf(false) }
            var checked3 by remember { mutableStateOf(false) }

            Column( modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.Start) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checked1, onCheckedChange = { checked1 = it })
                    // since checked1 is a mutable state, it represents the new value that lambda function receives when the checkbox state changes
                    Text("Q1: The Earth is Flat.")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checked2, onCheckedChange = { checked2 = it })
                    Text("Q2: Canberra is the capital of Australia.")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checked3, onCheckedChange = { checked3 = it })
                    Text("Q3: The speed of sound is 1,236 km/h.")
                }
            }

            Button(onClick = {
                var resultBoolean = !checked1 && checked2 && checked3;
                val intent = Intent(context, ResultActivity::class.java).apply {
                    // Replace context with this@SecondActivity if directly write the code in setContent instead of inside a @Composable function
                    putExtra("isPassed", resultBoolean) // attach extra data to the intent, so the new activity can retrieve it
                }
                context.startActivity(intent) // if no variable named "context" being declared, just write "startActivity(intent)"
            }) {
                Text("Check Result")
            }

        }
    }

}
