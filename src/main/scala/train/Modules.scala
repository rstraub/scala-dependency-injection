package train

import train.loading.LoadingModule
import train.shunting.ShuntingModule
import train.station.StationModule

object Modules extends App {
  val modules = new ShuntingModule with LoadingModule with StationModule

  modules.trainStation.prepareAndDispatchNextTrain()
}
