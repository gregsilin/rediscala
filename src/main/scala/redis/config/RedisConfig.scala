package redis.config

import com.typesafe.config.{ConfigFactory, Config}

import scala.concurrent.duration._
import scala.util.Try

object RedisConfig {

  private lazy val conf: Config = ConfigFactory.load()

  lazy val RedisConnectionTimeout: FiniteDuration = {
    val timeoutValue: Int = Try(conf getInt("rediscala.connection.timeout")) getOrElse {
      println("Connection setting missing, falling back to default")
      15
    }

    println(s"Redis connection timeout $timeoutValue seconds")
    timeoutValue.seconds
  }

  lazy val RedisReconnectTimeout: FiniteDuration = {
    val timeoutValue: Int = Try(conf getInt("rediscala.reconnect.timeout")) getOrElse {
      println("Reconnect setting missing, falling back to default")
      2
    }
    println(s"Redis connection timeout $timeoutValue seconds")
    timeoutValue.seconds
  }
}
