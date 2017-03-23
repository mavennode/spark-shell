package com.redoop.spark.sql

import java.util.UUID

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/1/10.
  */
object MroS1mmeCollect1 {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("MroS1mmeSparkApp").setMaster("local")
    val sc = new SparkContext(conf)

    //1.读取样本数据
    val data1 = sc.textFile("D:/Test/test1.txt") //文件中每一行就是RDD中的一个元素
    val data2 = sc.textFile("D:/Test/test2.txt")
    //转换RDD
//    (1,(20,1,3,小明))
//    (2,(23,2,4,小红))
//    (2,(11,2,5,小赵))
//    (3,(24,3,4,小刚))
//    (4,(35,4,5,小说))
//    (5,(32,5,3,小艾))
    val result1 = data1.map(x=>{
      val line = x.split(" ")
      val age = line(0)
      val grade = line(1)
      val grade1 = line(2)
      val name = line(3)
      (grade,(age,grade,grade1,name))
    })
//    (1,(1,1,3))
//    (2,(2,2,2))
//    (3,(2,3,1))
//    (3,(3,3,1))
//    (2,(3,2,3))
    val result2 = data2.map(x=>{
      val line = x.split(" ")
      val age = line(0)
      val grade = line(1)
      val grade1 = line(2)
      (grade,(age,grade,grade1))
    })
//    (4,((35,4,5,小说),None))
//    (5,((32,5,3,小艾),None))
//    (2,((23,2,4,小红),Some((2,2,2))))
//    (2,((23,2,4,小红),Some((3,2,3))))
//    (2,((11,2,5,小赵),Some((2,2,2))))
//    (3,((24,3,4,小刚),Some((2,3,1))))
//    (3,((24,3,4,小刚),Some((3,3,1))))
//    (1,((20,1,3,小明),Some((1,1,3))))
    println(".....................................................")
    val result = result1.leftOuterJoin(result2).map(x => {
      (x._1,x._2._1._1,x._2._1._2,x._2._1._3,x._2._1._4,x._2._2)
    }).filter(_._6!=None).groupBy(x => x._1).map(x => {
      val key = x._1
      val values = x._2
      //按age升序，并以grade升序，取出每一组里前3个
      val sortvalues = values.toList.sortWith(_._2<_._2).take(3)
      (key,sortvalues)
    }).sortByKey().foreach(println)
    println(".....................................................")
    sc.stop()
  }
}
