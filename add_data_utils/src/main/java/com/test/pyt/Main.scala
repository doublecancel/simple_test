package com.test.pyt

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * Created by Administrator on 2017/7/23 0023.
  */
class Main {

}

object Conn{

  val driverName = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT"
  val user = "root"
  val password = "root"
  val table = "User"
  case class Column(var fieldName : String, var fieldType : String, var isPrimary : Boolean = false)
  var container : List[Column] = List()
//  val mappings : Map[String, Any] = Map("bigint"->Long, "varchar"->String, "datetime"->java.util.Date)

  def getTableInfo = {
    Class.forName(driverName)
    val connection =  DriverManager.getConnection(url, user, password)
    val pst = connection.prepareStatement("desc " + table)
    val ret = pst.executeQuery();
    while (ret.next){
      container = container :+ Column(ret.getString("Field"), ret.getString("Type"))
    }
    ret.close()
    pst.close()
    connection.close()
  }

  def create = {
    Class.forName(driverName)
    val connection =  DriverManager.getConnection(url, user, password)
    val pst = connection.prepareStatement("insert into User (name, age, gender, email, phone, create_date, " +
      "create_user, modify_date, modify_user, status, version) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    pst.setString(1, "kafka")
    val ret = pst.executeQuery();
    while (ret.next){
      container = container :+ Column(ret.getString("Field"), ret.getString("Type"))
    }
    ret.close()
    pst.close()
    connection.close()
  }

  def main(args: Array[String]): Unit = {
    getTableInfo
    container.foreach(println(_))
  }

}
