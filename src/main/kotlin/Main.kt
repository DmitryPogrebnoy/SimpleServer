import java.net.ServerSocket

fun main(args: Array<String>) {
    val serverSocket = ServerSocket(12345)
    try {
        while (true) {
            Thread(ClientHandler(serverSocket.accept())).start()
        }
    }
    finally {
        serverSocket.close()
        println("Closing server socket")
    }
}