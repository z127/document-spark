package com.peter.bigdata

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @description:
 * @author oscar
 * @date 2023/4/5 11:32
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    // program  arguements input output
    // D:\ideaprojects\spark-wordcount\input D:\ideaprojects\spark-wordcount\output
    //1.create sparkconf
    val conf=new SparkConf().setAppName("WC").setMaster("local[3]")
    //2.create sparkcontext
    val sc= new SparkContext(conf)


    //3.read tje specified file
    val resultRdd:RDD[(String,Int)]=sc.textFile(args(0),1).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    resultRdd.saveAsTextFile(args(1))
    println("parttion_str: "+resultRdd.partitions.length)
    val moreRDD:RDD[(String,Int)]=resultRdd.map(t=>(t._1,t._2*5))
    println("partition count : "+moreRDD.toDebugString)

    //resultRdd.collect().foreach(println)
//    while(true)
//        {
//
//        }
    sc.stop()
  }
}
