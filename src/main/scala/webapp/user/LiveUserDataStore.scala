package webapp.user

import cats.implicits._

import java.util.UUID

class LiveUserDataStore extends UserDataStore {

  private var users = List(
    User(id = UUID.fromString("e9918fd2-6e44-4f30-845b-677ab286ea5a"), name = "Demo von User"),
    User(name = "UserName")
  )

  override def getAllUsers: Either[Exception, Seq[User]] = Right(users)

  override def getUserById(id: UUID): Either[RuntimeException, User] =
    Either.fromOption(users.find(_.id == id), new RuntimeException(s"User with uuid $id not found"))

  override def saveUser(user: User): Either[RuntimeException, User] = {
    users = user :: users
    Right(user)
  }

  override def deleteUserById(id: UUID): Either[RuntimeException, User] = getUserById(id)
    .map { user =>
      users = users.filterNot(_.id == id)
      user
    }

}
