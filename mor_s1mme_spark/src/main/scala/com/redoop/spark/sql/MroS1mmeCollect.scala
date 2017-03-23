package com.redoop.spark.sql
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.util.matching._

/**
  * Created by spark on 2016/12/30.
  * spark-submit --class com.redoop.spark.sql.MroS1mmeCollect --master yarn-client ./s1mmemro.jar /data/mro /data/s1mm1 /data/result
  */
object MroS1mmeCollect {
  def main(args:Array[String]){

//    if (args.length < 3) {
//      System.out.println("Usage: SparkSQL Action[Optins args[]")
//      System.out.println("$SPARK_HOME/bin/spark-submit --class com.redoop.spark.sql.MroS1mmeCollect --master yarn-cluster MroS1mmeCollect.jar /mro /m1mme /out")
//      System.exit(1)
//    }

    val conf=new SparkConf().setAppName("MroS1mmeSparkApp").setMaster("local")
    val sc=new SparkContext(conf)
    val result = sc.textFile("D:/mllib.txt")
    result.collect().foreach(println)
//    var i = 1
//    val result1 = result.map(x => x.mkString(""+i+1,"",""))
//    val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)

//    sqlContext.sql("create EXTERNAL table IF NOT EXISTS mc(a int)")
//      sqlContext.sql("create EXTERNAL table IF NOT EXISTS s1 (b string)  ROW FORMAT DELIMITED FIELDS TERMINATED BY ' \001' location '"+args(1)+"'");

//    val result = sqlContext.sql("select ")
    //    val data = result1.map(x =>x.mkString("|").replaceAll("null", ""))
//
//    data.collect().foreach(x => println(x))

//    data.coalesce(1,true).saveAsTextFile(args(2))

    //sqlContext.sql("drop table mro_csh_20161205")
    //sqlContext.sql("drop table s1mme_pair_csh_20161205")
//    sqlContext.clearCache()
    sc.stop()
  }
}
