package com.acmegroup.apitesting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acmegroup.apitesting.ui.theme.ApiTestingTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiClient.apiService.getUsers().enqueue(object: Callback<List<UserItem>> {
            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    setContent {
                        UserListScreen(users ?: emptyList())
                    }
                } else {
                    setContent {
                        ApiTestingTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                Greeting("onResponse")
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                Log.e("YOUR ERROR TAG HERE", "Copying failed", t);
                setContent {
                    ApiTestingTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Greeting("onFailure")
                        }
                    }
                }
            }
        })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApiTestingTheme {
        Greeting("Android")
    }
}