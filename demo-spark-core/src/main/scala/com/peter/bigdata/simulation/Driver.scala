package com.peter.bigdata.simulation

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

/**
 * @description:
 * @author oscar
 * @date 2023/4/5 16:43
 */
object Driver {
  def main(args: Array[String]): Unit = {

    // connect to the server
    val client1 = new Socket("localhost", 9999)
    val client2 = new Socket("localhost", 8888)

    val task = new Task()

    val out1: OutputStream = client1.getOutputStream
    val objOut1 = new ObjectOutputStream(out1)

    val subTask = new SubTask()
    subTask.logic = task.logic
    subTask.datas = task.datas.take(2)

    objOut1.writeObject(subTask)
    objOut1.flush()
    objOut1.close()
    client1.close()

    val out2: OutputStream = client2.getOutputStream
    val objOut2 = new ObjectOutputStream(out2)

    val subTask1 = new SubTask()
    subTask1.logic = task.logic
    subTask1.datas = task.datas.takeRight(2)
    objOut2.writeObject(subTask1)
    objOut2.flush()
    objOut2.close()
    client2.close()
    println("client send data to server completed")

  }

}
