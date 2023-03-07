package com.example.binsearch.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.binsearch.data.models.request.BIN
import com.example.binsearch.ui.viewmodels.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    Scaffold() {
        Column() {
            Row(modifier = Modifier.padding(5.dp,0.dp)) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.search,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { viewModel.updateSearch(it.replace(" ","")) },
                    label = { Text("Поиск") },
                    isError = viewModel.isError,
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.getBin(viewModel.search)
                                viewModel.updateSearch("")
                            }
                        ) {
                            Icon(

                                Icons.Default.Search,
                                contentDescription = "",
                            )
                        }
                    }
                )
            }

            Row() {
                if (viewModel.isError) {
                    Text(
                        text = viewModel.errorText,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption

                    )
                }
            }

            val list = viewModel.binList.observeAsState(initial = emptyList())
            LazyColumn {
                items(list.value) {
                    BinView(it)
                }
            }
        }
    }
}

@Composable
fun BinView(bin: BIN) {
    val context = LocalContext.current

    val contactsDialog = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${bin.bank?.phone}"));
    val webDialog = Intent(Intent.ACTION_VIEW, Uri.parse("http://${bin.bank?.url}"));
    val mapDialog = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:${bin.country?.latitude},${bin.country?.longitude}")
    );


    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Number:",
            fontWeight = FontWeight(500)
        )
        Text(
            text = "Length: ${bin.number?.length}",
            modifier = Modifier.padding(paddingValues)
        )
        Text(
            text = "Luhn: ${if (bin.number?.luhn == true) "yes" else "no"}",
            modifier = Modifier.padding(paddingValues)
        )
        Text(
            text = "Scheme: ${bin.scheme}",
        )
        Text(text = "Type: ${bin.type}")
        Text(text = "Brand: ${bin.brand}")
        Text(text = "Prepaid:  ${if (bin.prepaid) "yes" else "no"}")
        Text(
            text = "Country:",
            fontWeight = FontWeight(500)
        )
        Text(
            text = "${bin.country?.emoji} ${bin.country?.name}",
            modifier = Modifier.padding(paddingValues)
        )
        Text(
            text = "Currency: ${bin.country?.currency}",
            modifier = Modifier.padding(paddingValues)
        )
        Text(
            text = "latitude - longitude ${bin.country?.latitude} ${bin.country?.longitude}",
            modifier = Modifier
                .padding(paddingValues)
                .clickable { context.startActivity(mapDialog) }
        )
        Text(
            text = "Bank:",
            fontWeight = FontWeight(500)
        )

        Text(
            text = "Name: ${bin.bank?.name} ",
            modifier = Modifier.padding(paddingValues)
        )
        Text(
            text = "Url: ${bin.bank?.url} ",
            modifier = Modifier
                .padding(paddingValues)
                .clickable { context.startActivity(webDialog) }
        )
        Text(
            text = "Phone: ${bin.bank?.phone} ",
            modifier = Modifier
                .padding(paddingValues)
                .clickable { context.startActivity(contactsDialog) }
        )
        Text(
            text = "City: ${bin.bank?.city} ",
            modifier = Modifier.padding(paddingValues)
        )
    }
    Divider(Modifier.padding(vertical = 3.dp), color = Color.LightGray, thickness = 1.dp)
}

private val paddingValues = PaddingValues(10.dp, 0.dp, 0.dp, 0.dp)