package webapp.user

import cats.data.Reader
import webapp.common.Utils

trait UserContext extends UserDomain
class UserHandler(ctx: UserContext) {
  import UserService._

  def bindGetAll: Either[Exception, Seq[User]] = ctx.getAllUsers

  def bindGetById(id: String): Either[Exception, User] = for {
    uuid   <- Utils.parseId(id)
    result <- ctx.getUserById(uuid)
  } yield result

  def bindPost(user: User): Either[Exception, User] = ctx.saveUser(user)

  def bindDeleteById(id: String): Either[Exception, User] = for {
    uuid   <- Utils.parseId(id)
    result <- ctx.deleteUserById(uuid)
  } yield result

}

object UserReaderHandler {
  def bindGetAll: Reader[UserContext, Either[Exception, Seq[User]]] = UserReaderService.getAllUsers

  def bindGetById(id: String): Reader[UserContext, Either[Exception, User]] = Reader(ctx =>
    for {
      uuid   <- Utils.parseId(id)
      result <- UserReaderService.getUserById(uuid)(ctx)
    } yield result
  )

  def bindPost(user: User): Reader[UserContext, Either[Exception, User]] = UserReaderService.saveUser(user)

  def bindDeleteById(id: String): Reader[UserContext, Either[Exception, User]] = Reader(ctx =>
    for {
      uuid   <- Utils.parseId(id)
      result <- UserReaderService.deleteUserById(uuid)(ctx)
    } yield result
  )
}
