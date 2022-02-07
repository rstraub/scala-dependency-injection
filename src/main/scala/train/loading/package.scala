package train

import com.softwaremill.macwire.wire
import train.shunting.PointSwitcher

package object loading {
  trait LoadingModule {
    lazy val craneController: CraneController = wire[CraneController]
    lazy val trainLoader: TrainLoader = wire[TrainLoader]

    // dependency of the module
    def pointSwitcher: PointSwitcher
  }

  class CraneController() {
    def move(): Unit = println("move with crane")

  }

  class TrainLoader(craneController: CraneController,
                    pointSwitcher: PointSwitcher) {
    def load(): Unit = {
      println("loading train")
      craneController.move()
      pointSwitcher.switch()
    }
  }

}
