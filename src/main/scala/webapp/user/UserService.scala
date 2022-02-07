package webapp.user

import java.util.UUID

trait UserDomain extends HasUserDataStore
object UserService {
  implicit class UserService(val ctx: UserDomain) {

    def getAllUsers: Either[Exception, Seq[User]] = ctx.userDataStore.getAllUsers

    def getUserById(id: UUID): Either[Exception, User] = ctx.userDataStore.getUserById(id)

    def saveUser(user: User): Either[Exception, User] = ctx.userDataStore.saveUser(user)

    def deleteUserById(id: UUID): Either[Exception, User] = ctx.userDataStore.deleteUserById(id)

  }
}
