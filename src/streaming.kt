import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

data class StreamData(val id: Int, val content: String)

class StreamProcessor {
    private val channel = Channel<StreamData>()

    fun startStreaming() = runBlocking {
        launch {
            for (i in 1..10) {
                delay(1000L) // Simulate data generation delay
                channel.send(StreamData(i, "Content $i"))
            }
            channel.close()
        }
    }

    fun processStream() = runBlocking {
        channel.consumeAsFlow()
            .onEach { data ->
                println("Processing: \${data.content}")
            }
            .catch { e -> println("Error: \${e.message}") }
            .collect()
    }
}

fun main() {
    val processor = StreamProcessor()
    processor.startStreaming()
    processor.processStream()
}
