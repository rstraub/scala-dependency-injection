package webapp.user

import cats.data.Reader

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

object UserReaderService {
  def getAllUsers: Reader[UserDomain, Either[Exception, Seq[User]]] = Reader(ctx => ctx.userDataStore.getAllUsers)

  def getUserById(id: UUID): Reader[UserDomain, Either[Exception, User]] = Reader(ctx => ctx.userDataStore.getUserById(id))

  def saveUser(user: User): Reader[UserDomain, Either[Exception, User]] = Reader(ctx => ctx.userDataStore.saveUser(user))

  def deleteUserById(id: UUID): Reader[UserDomain, Either[Exception, User]] = Reader(ctx => ctx.userDataStore.deleteUserById(id))
}
