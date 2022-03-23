package com.datt16.mycounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.datt16.mycounter.ui.theme.MyCounterTheme
import com.datt16.mycounter.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = CounterViewModel()

        setContent {
            MyCounterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    color = MaterialTheme.colors.background
                ) {
                    CounterCard(
                        model.counterName,
                        model.count,
                        { model.increaseCount() },
                        { model.decreaseCount() }
                    )
                }
            }
        }
    }
}

@Composable
fun CounterCard(
    counterName: String = "SampleCounter",
    value: LiveData<Int> = MutableLiveData(10),
    onPlusButtonClicked: () -> Unit = {},
    onMinusButtonClicked: () -> Unit = {}
) {
    val count = value.observeAsState()
    MyCounterTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row() {
                    Text(style = Typography.subtitle1, text = counterName)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    count.value?.let { cnt ->
                        Text(style = Typography.h3, text = "$cnt")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onPlusButtonClicked) {
                        Text(text = "+")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onMinusButtonClicked) {
                        Text(text = "-")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCounterTheme {
        CounterCard()
    }
}