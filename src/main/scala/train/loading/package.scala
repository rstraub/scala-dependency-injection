package train

import com.softwaremill.macwire.wire

package object loading {
  trait LoadingModule {
    lazy val craneController: CraneController = wire[CraneController]
    lazy val trainLoader: TrainLoader = wire[TrainLoader]

    // dependency of the module
    def pointSwitcher: PointSwitcher
  }
}
