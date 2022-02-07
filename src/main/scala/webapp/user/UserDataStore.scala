package webapp.user

import java.util.UUID

trait HasUserDataStore {
  val userDataStore: UserDataStore
}

trait UserDataStore {

  def getAllUsers: Either[Exception, Seq[User]]

  def getUserById(id: UUID): Either[Exception, User]

  def saveUser(user: User): Either[Exception, User]

  def deleteUserById(id: UUID): Either[Exception, User]

}
