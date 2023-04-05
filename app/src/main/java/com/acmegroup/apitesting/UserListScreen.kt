package com.acmegroup.apitesting

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun UserListScreen(users: List<UserItem>) {
    Column(

    ) {


        Image(
            painter = painterResource(id = R.drawable.headerimage),
            contentDescription = "header",
            modifier = Modifier
                .padding(bottom = 8.dp)

        )
        Text(text = "BenutzerIn-Verwaltung",
             fontSize = 24.sp,
             color = Color(0xFF005EA8),
             modifier = Modifier
                 .padding(start = 16.dp)
        )
        LazyColumn {
            items(users) { user ->
                UserListItem(user)
            }
        }
    }
}

@Composable
fun UserListItem(user: UserItem) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = user.vorname,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.edit),
            contentDescription = "Right Image",
            modifier = Modifier
                .size(32.dp)
                .clickable { showDialog = true }
        )
        if (showDialog) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    showDialog = false
                },
                title = {
                    Text(text = user.vorname)
                },
                text = {
                    Column() {
                        Text("Vorname: " + user.vorname)
                        Text("Nachname: " + user.nachname)
                        Text("Firma: " + user.firma)
                        Text("Login: " + user.benutz_name)
                        Text("Admin: " + user.admin)
                    }
                },
                confirmButton = {
                    Button(

                        onClick = {
                            showDialog = false
                        }) {
                        Text("Back")
                    }
                },
            )
        }
    }
}