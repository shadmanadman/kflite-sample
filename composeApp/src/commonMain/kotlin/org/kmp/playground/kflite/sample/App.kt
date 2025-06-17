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


@Composable
fun RunModelWithImageSample() {
    val scope = rememberCoroutineScope()
    val inputImage = imageResource(Res.drawable.example_model_input)
    scope.launch {
        // init model
        Kflite.init(Res.readBytes("files/efficientdet-lite2.tflite"))

        println("TensorInputCount: ${Kflite.getInputTensorCount()}")
        println("TensorOutputCount: ${Kflite.getOutputTensorCount()}")

        // Prepare input data: Example model takes 4D array as an input
        val inputImageWidth = Kflite.getInputTensor(0).shape[1]
        val inputImageHeight = Kflite.getInputTensor(0).shape[2]
        val modelInputSize =
            FLOAT_TYPE_SIZE * inputImageWidth * inputImageHeight * PIXEL_SIZE

        // Prepare output data: Example model has 3D array as an output
        val firstOutputShape = Kflite.getOutputTensor(0).shape[0]
        val secondOutputShape = Kflite.getOutputTensor(0).shape[1]
        val thirdOutputShape = Kflite.getOutputTensor(0).shape[2]

        val modelOutputSize = Array(firstOutputShape) {
            Array(secondOutputShape) {
                FloatArray(thirdOutputShape)
            }
        }

        // Run model
        Kflite.run(
            listOf(
                inputImage.toScaledByteBuffer(
                    inputWidth = inputImageWidth,
                    inputHeight = inputImageHeight,
                    inputAllocateSize = modelInputSize
                )
            ),
            mapOf(Pair(0, modelOutputSize))
        )
        println("Output of first detection: ${modelOutputSize[0][0].joinToString()}")

        // Close model after use
        Kflite.close()
    }
}

private const val FLOAT_TYPE_SIZE = 3
private const val PIXEL_SIZE = 1

