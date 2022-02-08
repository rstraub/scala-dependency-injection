package webapp.todo

import cats.data.Reader
import cats.implicits._
import webapp.user.{User, UserDomain, UserReaderService}
import webapp.workitem.{WorkItemDomain, WorkItemReaderService}

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

object ToDoReaderService {
  def getAllToDos: Reader[ToDoContext, Either[Exception, Seq[ToDo]]] = {
    def getUser(uuid: UUID)(ctx: ToDoContext): Either[Exception, User] = UserReaderService.getUserById(uuid)(ctx)

    Reader((ctx: ToDoContext) =>
      for {
        items <- WorkItemReaderService.getAllWorkItems(ctx)
        users <- items.flatMap(_.assignedUserId).traverse(uuid => getUser(uuid)(ctx))
        todos = items.map(item =>
          ToDo(
            id = item.id,
            description = item.description,
            due = item.due,
            assignedUser = item.assignedUserId.flatMap(id => users.find(_.id == id))
          )
        )
      } yield todos
    )
  }

  def getToDoByUserId(userId: UUID): Reader[ToDoContext, Either[Exception, ToDo]] = Reader(ctx =>
    for {
      user <- UserReaderService.getUserById(userId)(ctx)
      todo <- WorkItemReaderService
        .getWorkItemByUserId(userId)(ctx)
        .map(it =>
          ToDo(
            id = it.id,
            description = it.description,
            due = it.due,
            assignedUser = Some(user)
          )
        )
    } yield todo
  )
}
