package train.injection.manual

import train.loading.TrainLoader
import train.shunting.TrainShunter
import train.station.{TrainDispatch, TrainStation}

trait StationModule {
  lazy val trainDispatch: TrainDispatch = new TrainDispatch()

  lazy val trainStation: TrainStation =
    new TrainStation(trainShunter, trainLoader, trainDispatch)

  // dependencies of the module
  def trainShunter: TrainShunter
  def trainLoader: TrainLoader
}
