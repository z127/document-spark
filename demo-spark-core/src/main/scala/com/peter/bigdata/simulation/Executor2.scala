package com.peter.bigdata.simulation

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor2 {

    def main(args: Array[String]): Unit = {

        // start the server,wait for the connection
        val server = new ServerSocket(8888)
        println("Server started, wait for the data")

        // wait for the connection from client
        val client: Socket = server.accept()
        val in: InputStream = client.getInputStream
        val objIn = new ObjectInputStream(in)
        val task: SubTask = objIn.readObject().asInstanceOf[SubTask]
        val ints: List[Int] = task.compute()
        println("the  node [8888] computing result is ：" + ints)
        objIn.close()
        client.close()
        server.close()
    }
}
