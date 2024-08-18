package com.example.filemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filemanager.ui.theme.FileManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FileManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FileManagerApp()
                }
            }
        }
    }
}

@Composable
fun FileManagerApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(8.dp))
        RecentsSection()
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Files", fontSize = 20.sp, color = Color.White)
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.White)
        }
    }
}

@Composable
fun RecentsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        DocumentThumbnails()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "0", fontSize = 16.sp, color = Color.Gray)
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                innerTextField()
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Search", color = Color.Gray)
            }
        }
    )
}

@Composable
fun DocumentThumbnails() {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        for (i in 1..5) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray)
                    .padding(8.dp)
                    .clickable {}
            ) {
                Text(text = "document$i", color = Color.White, modifier = Modifier.align(Alignment.BottomCenter))
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Recents") },
            label = { Text("Recents") },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.DeviceUnknown, contentDescription = "Device") },
            label = { Text("Device") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Send, contentDescription = "Send") },
            label = { Text("Send") },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FileManagerTheme {
        FileManagerApp()
    }
}
