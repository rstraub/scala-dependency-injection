package train.injection.framework

import com.softwaremill.macwire.wire
import train.loading.{CraneController, TrainLoader}
import train.shunting.PointSwitcher

trait LoadingModule {
  lazy val craneController: CraneController = wire[CraneController]
  lazy val trainLoader: TrainLoader = wire[TrainLoader]

  // dependency of the module
  def pointSwitcher: PointSwitcher

}
