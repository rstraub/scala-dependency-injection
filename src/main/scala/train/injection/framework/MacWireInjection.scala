package train.injection.framework

import com.softwaremill.macwire.wire
import train.loading.{CraneController, TrainLoader}
import train.shunting.{PointSwitcher, TrainCarCoupler, TrainShunter}
import train.station.{TrainDispatch, TrainStation}

object MacWireInjection extends App {
  lazy val pointSwitcher = wire[PointSwitcher]
  lazy val trainCarCoupler = wire[TrainCarCoupler]
  lazy val trainShunter = wire[TrainShunter]

  lazy val craneController = wire[CraneController]
  lazy val trainLoader = wire[TrainLoader]
  lazy val trainDispatch = wire[TrainDispatch]

  lazy val trainStation = wire[TrainStation]

  trainStation.prepareAndDispatchNextTrain()
}
