package redis.config

import com.typesafe.config.{ConfigFactory, Config}

import scala.concurrent.duration._
import scala.util.Try

object RedisConfig {

  private lazy val conf: Config = ConfigFactory.load()

  private def parseDuration(s: String): FiniteDuration = {
    val duration = Duration(s)
    duration match {
      case t: FiniteDuration => t
      case _ => throw new IllegalArgumentException(s"Cannot parse $s as a duration.")
    }
  }

  lazy val RedisConnectionTimeout: FiniteDuration = {
    val confValue = Option(conf getString "rediscala.connection.timeout")
    confValue map parseDuration getOrElse 15.seconds
  }

  lazy val RedisReconnectTimeout: FiniteDuration = {
    val confValue = Option(conf getString "rediscala.reconnect.interval")
    confValue map parseDuration getOrElse 2.seconds
  }
}
