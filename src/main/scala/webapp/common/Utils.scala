package webapp.common

import java.util.UUID
import scala.util.{Failure, Success, Try}

object Utils {
  def parseId(id: String): Either[Exception, UUID] = Try(UUID.fromString(id)) match {
    case Failure(_)     => Left(new RuntimeException("Id passed is not a UUID"))
    case Success(value) => Right(value)
  }
}
