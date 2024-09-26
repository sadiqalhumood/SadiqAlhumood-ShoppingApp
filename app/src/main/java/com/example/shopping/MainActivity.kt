package com.example.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopping.ui.theme.ShoppingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingTheme {
                Surface(){
                        Shop()
                }
            }
        }
    }
}

@Composable
fun Shop(){



    var item by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    data class Items(val name: String, val quantity: String){
        var isChecked by mutableStateOf(false)
    }
    var items = remember { mutableStateListOf<Items>() }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text("Shopping List", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = item,
                onValueChange = { item = it },
                label = { Text("Item") },
                modifier = Modifier.fillMaxWidth()
            )

        }

        Row {
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            ElevatedButton(
                onClick = {
                    if (item.isNotBlank() && quantity.isNotBlank()) {
                        items += Items(item, quantity)
                        item = ""
                        quantity = ""
                    }
                }
            )
            {
                Text("Add Item to Shopping List")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        LazyColumn {
            itemsIndexed(items) { index, item ->

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isChecked,
                        onCheckedChange = { checked ->
                            item.isChecked = checked
                        }
                    )

                    Text(text = item.name + " - (" + item.quantity + ")")
                }

            }


            }
        }
    }

