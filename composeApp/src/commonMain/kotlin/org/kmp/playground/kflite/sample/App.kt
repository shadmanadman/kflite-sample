package org.kmp.playground.kflite.sample

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kflitesample.composeapp.generated.resources.Res
import kotlinx.coroutines.coroutineScope
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.playground.kflite.Kflite

@Composable
fun App() {
    GetModelInputOutputCountTest()
}


@Composable
fun GetModelInputOutputCountTest(){
    var bytes1 by remember {
        mutableStateOf(ByteArray(0))
    }

    LaunchedEffect(Unit) {
        bytes1 = Res.readBytes("files/efficientdet-lite2.tflite")
        println("ReadBytes get run")
        Kflite.init(bytes1)
        println("TensorInputCount: ${Kflite.getInputTensorCount()}")
        println("TensorOutputCount: ${Kflite.getOutputTensorCount()}")
    }
}