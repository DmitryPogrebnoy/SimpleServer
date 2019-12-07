import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ClientHandler(private val clientSocket: Socket): Runnable {
    private companion object {
        var numberConnections = 0
    }

    private val connectionId: Int
    private val printWriter: PrintWriter
    private val bufferReader: BufferedReader

    init {
        connectionId = ++numberConnections
        printWriter = PrintWriter(clientSocket.outputStream, true)
        bufferReader = BufferedReader(InputStreamReader(clientSocket.inputStream))
        println("Connection $connectionId")
    }

    override fun run() {
        while (true) {
            val line = bufferReader.readLine() ?: break
            println("Received from connection $connectionId: $line ")
            printWriter.write("$line\n")
            printWriter.flush()
            if (line == "exit") break
        }
        bufferReader.close()
        printWriter.close()
        clientSocket.close()
        println("Connection $connectionId closed")
    }
}

