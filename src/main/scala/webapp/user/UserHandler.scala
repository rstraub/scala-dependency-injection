package webapp.user

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
