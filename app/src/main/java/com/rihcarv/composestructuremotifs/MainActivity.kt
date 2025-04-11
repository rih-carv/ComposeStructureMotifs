package com.rihcarv.composestructuremotifs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rihcarv.composestructuremotifs.ui.theme.ComposeStructureMotifsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStructureMotifsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonExamples(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val examplesMap = mapOf<String, @Composable () -> Unit>(
    "Remember + state" to { CounterButton() },
    "Only state + recomposition" to { CounterButtonWithOnlyStateAndRecomposition() },
    "Only remember" to { CounterButtonWithOnlyRemember() },
    "Simple var" to { CounterButtonWithSimpleVar() },
    "Simple var + recomposition" to { CounterButtonWithSimpleVarAndRecomposition() },
)

@Composable
fun ButtonExamples(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            items = examplesMap.entries.toList(),
            key = { it.key }
        ) {
            ListItem(
                headlineContent = { Text(it.key) },
                supportingContent = it.value
            )
        }
    }
}

@Composable
fun CounterButton(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    var update by remember { mutableIntStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter, Update: $update")
        }
        Button(onClick = { update++ }) {
            Text(text = "Update")
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CounterButtonWithOnlyStateAndRecomposition(modifier: Modifier = Modifier) {
    var counter by mutableIntStateOf(0)
    var update by remember { mutableIntStateOf(0) }

    @Suppress("UNUSED_EXPRESSION")
    update // triggers recomposition at this level by reading value

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter, Update: $update")
        }
        Button(onClick = { update++ }) {
            Text(text = "Update")
        }
    }
}

@Composable
fun CounterButtonWithOnlyRemember(modifier: Modifier = Modifier) {
    var counter = remember { 0 }
    var update by remember { mutableIntStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter, Update: $update")
        }
        Button(onClick = { update++ }) {
            Text(text = "Update")
        }
    }
}

@Composable
fun CounterButtonWithSimpleVar(modifier: Modifier = Modifier) {
    var counter = 0
    var update by remember { mutableIntStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter, Update: $update")
        }
        Button(onClick = { update++ }) {
            Text(text = "Update")
        }
    }
}

@Composable
fun CounterButtonWithSimpleVarAndRecomposition(modifier: Modifier = Modifier) {
    var counter = 0
    var update by remember { mutableIntStateOf(0) }

    @Suppress("UNUSED_EXPRESSION")
    update // triggers recomposition at this level by reading value

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter, Update: $update")
        }
        Button(onClick = { update++ }) {
            Text(text = "Update")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterButtonsPreview() {
    ComposeStructureMotifsTheme {
        ButtonExamples()
    }
}