package train.injection.manual

import train.loading.{CraneController, TrainLoader}
import train.shunting.PointSwitcher

trait LoadingModule {
  lazy val craneController: CraneController = new CraneController()
  lazy val trainLoader: TrainLoader =
    new TrainLoader(craneController, pointSwitcher)

  // dependency of the module
  def pointSwitcher: PointSwitcher
}
