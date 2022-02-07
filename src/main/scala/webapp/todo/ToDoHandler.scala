package webapp.todo

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
