package com.redoop.sql.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by whoami on 2016/12/30.
  * spark/bin/spark-submit --class org.sparkjvm.hiveQL.HiveFromSpark
  * --master spark://bigdatat01:18888 spark-code-1.0-SNAPSHOT-jar-with-dependencies.jar 'show tables' 'print'
  *
  */
object SparkSQLTest {

  def main(args: Array[String]) {

    if (args.length < 2) {
      System.out.println("Usage: SparkSQL Action[Optins print/hive]")
      System.out.println("$SPARK_HOME/bin/spark-submit --class com.redoop.spark.sql.MroS1mmeCollect --master yarn-cluster MroS1mmeCollect.jar 'show databases;show tables;' 'print'")
      System.exit(1)
    }

    //new SparkContext
    val sparkConf = new SparkConf().setAppName("SparkHiveQLApp")
    val sc = new SparkContext(sparkConf)

    // sc is an existing SparkContext.
    val hc = new HiveContext(sc)

    val sql = args(0).toString().split(";")

    for (i <- 0 to sql.length-1) {
      val HiveQL = sql(i)
      //Queries are expressed in HiveQL
      if (args(1).toString() == "print") {
        println("执行HiveQL >>>>>>>>>>>>>" + HiveQL + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        hc.sql(HiveQL).collect().foreach(println)
      }

      if (args(1).toString() == "hive") {
        println("执行HiveQL >>" + args(0).toString() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        hc.sql(HiveQL)
      }
    }

    sc.stop()
  }

}
