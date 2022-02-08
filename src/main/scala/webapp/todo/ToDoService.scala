package webapp.todo

import cats.implicits._
import webapp.user.UserDomain
import webapp.workitem.WorkItemDomain

import java.util.UUID

trait ToDoDomain extends WorkItemDomain with UserDomain
object ToDoService {
  implicit class ToDoService(ctx: ToDoDomain) {
    import webapp.user.UserService._
    import webapp.workitem.WorkItemService._

    def getAllToDos: Either[Exception, Seq[ToDo]] =
      for {
        items <- ctx.getAllWorkItems
        users <- items.flatMap(_.assignedUserId).traverse(ctx.getUserById)
        todos = items.map(item =>
          ToDo(
            id = item.id,
            description = item.description,
            due = item.due,
            assignedUser = item.assignedUserId.flatMap(id => users.find(_.id == id))
          )
        )
      } yield todos

    def getToDoByUserId(userId: UUID): Either[Exception, ToDo] =
      for {
        user <- ctx.getUserById(userId)
        todo <- ctx
          .getWorkItemByUserId(userId)
          .map(it =>
            ToDo(
              id = it.id,
              description = it.description,
              due = it.due,
              assignedUser = Some(user)
            )
          )
      } yield todo

  }
}
