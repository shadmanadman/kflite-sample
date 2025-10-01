package org.kmp.playground.kflite.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kflitesample.composeapp.generated.resources.Res
import kflitesample.composeapp.generated.resources.example_model_input
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.imageResource
import org.kmp.playground.kflite.Kflite
import org.kmp.playground.kflite.toScaledByteBuffer

@Composable
fun App() {
    RunModelWithImageSample()
}



