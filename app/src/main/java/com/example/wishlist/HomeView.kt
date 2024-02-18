package com.example.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun HomeView() {
    var addDialogOpen by remember {
        mutableStateOf(false)
    }
    var wishlistItems by remember{ mutableStateOf(listOf<WishlistItem>()) }
    Scaffold(
        topBar = {
            AppBarView(title = "WishList")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { addDialogOpen = true },
                containerColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
           items(wishlistItems){
               WishListItem(title = it.title, description = it.description)
           }
        }
        if (addDialogOpen)
            DialogInput(onClose = {
                addDialogOpen = false
            }, onAdd = {item ->
                val newList = mutableListOf<WishlistItem>()
                newList.addAll(wishlistItems)
                newList.add(item)
                wishlistItems = newList.toList()
                addDialogOpen = false
            })
    }
}

@Composable
fun DialogInput(onClose: () -> Unit, onAdd:(item: WishlistItem) -> Unit) {
    val title = remember {
        mutableStateOf("")
    }
    var description by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onClose() }) {
        Surface(
            modifier = Modifier
                .background(Color.White)
                .padding(20.dp),
//            shape = RoundedCornerShape(20.dp)
        ) {
            Column {
                Text(text = "Add a wishlist item", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text(text = "Title") })
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") })
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { onClose() }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(onClick = {onAdd(WishlistItem(title = title.value, description=description))}) {
                        Text(text = "Add")
                    }
                }

            }
        }
    }
}

@Composable
fun WishListItem(title: String, description: String) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 4.dp)) {
        Card {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = title, fontWeight = FontWeight.SemiBold)
                Text(text = description)
            }
        }
    }
}