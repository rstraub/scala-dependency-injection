package train.injection.framework

import com.softwaremill.macwire.wire
import train.loading.TrainLoader
import train.shunting.TrainShunter
import train.station.{TrainDispatch, TrainStation}

trait StationModule {
  lazy val trainDispatch: TrainDispatch = wire[TrainDispatch]

  lazy val trainStation: TrainStation = wire[TrainStation]

  // dependencies of the module
  def trainShunter: TrainShunter
  def trainLoader: TrainLoader
}
