package train.injection.manual

object ManualModules extends App {
  lazy val modules = new ShuntingModule with LoadingModule with StationModule

  modules.trainStation.prepareAndDispatchNextTrain()
}
