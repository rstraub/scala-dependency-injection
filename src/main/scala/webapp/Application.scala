package webapp

import webapp.todo.{ToDoContext, ToDoHandler}
import webapp.user.{LiveUserDataStore, UserDataStore}
import webapp.workitem.{LiveWorkItemDataStore, WorkItemDataStore}

trait ApplicationContext extends ToDoContext
object Application extends App {

  val LiveContext = new ApplicationContext {
    override lazy val workItemDataStore: WorkItemDataStore = new LiveWorkItemDataStore()
    override lazy val userDataStore: UserDataStore         = new LiveUserDataStore()
  }

  val result = new ToDoHandler(LiveContext)
    .bindGetByUserId("e9918fd2-6e44-4f30-845b-677ab286ea5a")
    .map(toDo => (toDo.description, toDo.assignedUser.map(_.name)))

  println(result)

}
