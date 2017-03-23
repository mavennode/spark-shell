package com.redoop.spark.sql

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Mars on 2017/1/11.
  */
object Wordcount {
  def main(args: Array[String]) {
//    if(args.length < 1) {
//      System.err.println("Usage: <file>")
//      System.out.println("spark-submit --master yarn-client --class com.Mars.spark.Wordcount --name wordcount --executor-memory 400M --driver-memory 512M wordcount.jar hdfs://192.168.0.33:8020/user/hadoop/wordcount.txt")
//      System.exit(1)
//    }
    val conf = new SparkConf().setAppName("SparkwordcountApp").setMaster("local")
    val sc = new SparkContext(conf)
    //SparkContext 是把代码提交到集群或者本地的通道
//    val line = sc.textFile(args(0))
    val line = sc.textFile("D:/Test/wordcount.txt")
    //把读取的内容保存给line变量，其实line是一个MappedRDD，Spark的所有操作都是基于RDD的
    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect.foreach(println)
    sc.stop
  }
}
