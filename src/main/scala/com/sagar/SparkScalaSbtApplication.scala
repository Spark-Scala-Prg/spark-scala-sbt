package com.sagar

import org.apache.spark.sql.SparkSession

object SparkScalaSbtApplication {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("Spark Scala SBT Example")
      .master("local[*]")

      .config("spark.driver.bindAddress", "localhost") // 👈 Bind to localhost

      .getOrCreate()

    // Set log level to reduce Spark logging
    spark.sparkContext.setLogLevel("ERROR")

    // ✅ Read CSV file
    val filePath = "src/main/resources/data/employees.csv"
    val df = spark.read
      .option("header", "true") // ✅ First row as header
      .option("inferSchema", "true") // ✅ Automatically infer data types
      .csv(filePath)

    // ✅ Show DataFrame
    df.show()

    // ✅ Print schema
    df.printSchema()

    // ✅ Filter example - Employees with salary > 65000
    df.filter("salary > 65000").show()

    // ✅ Group by example - Average salary per department
    df.groupBy("department")
      .avg("salary")
      .show()
    println("Spark UI: " + spark.sparkContext.uiWebUrl.getOrElse("Not started"))

    // 👇 Keep SparkSession alive
    println("Spark application is running. Press Ctrl+C to stop.")
    Thread.sleep(Long.MaxValue)

    // ✅ Stop Spark session
    spark.stop()
  }
}
