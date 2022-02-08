package webapp.todo

import cats.data.Reader
import webapp.common.Utils

trait ToDoContext extends ToDoDomain
class ToDoHandler(ctx: ToDoContext) {
  import ToDoService._

  def bindGetAll: Either[Exception, Seq[ToDo]] = ctx.getAllToDos

  def bindGetByUserId(userId: String): Either[Exception, ToDo] = for {
    id   <- Utils.parseId(userId)
    todo <- ctx.getToDoByUserId(id)
  } yield todo
}

object ToDoReaderHandler {
  def bindGetAll: Reader[ToDoContext, Either[Exception, Seq[ToDo]]] = ToDoReaderService.getAllToDos

  def bindGetByUserId(userId: String): Reader[ToDoContext, Either[Exception, ToDo]] = Reader { ctx =>
    for {
      id   <- Utils.parseId(userId)
      todo <- ToDoReaderService.getToDoByUserId(id)(ctx)
    } yield todo
  }
}
