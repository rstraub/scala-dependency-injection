package webapp

import webapp.Dry.map
import webapp.todo.{ToDo, ToDoContext, ToDoHandler, ToDoReaderHandler}
import webapp.user.{LiveUserDataStore, UserDataStore}
import webapp.workitem.{LiveWorkItemDataStore, WorkItemDataStore}

trait ApplicationContext extends ToDoContext

object LiveContext extends ApplicationContext {
  override lazy val workItemDataStore: WorkItemDataStore = new LiveWorkItemDataStore()
  override lazy val userDataStore: UserDataStore         = new LiveUserDataStore()
}

object Application extends App {
  new ToDoHandler(LiveContext)
    .bindGetByUserId("e9918fd2-6e44-4f30-845b-677ab286ea5a")
    .map(map)
    .map(println)
}

object ApplicationWithReader extends App {
  ToDoReaderHandler
    .bindGetByUserId("e9918fd2-6e44-4f30-845b-677ab286ea5a")(LiveContext)
    .map(map)
    .map(println)
}

object Dry {
  def map(toDo: ToDo): (String, Option[String]) = (toDo.description, toDo.assignedUser.map(_.name))
}
