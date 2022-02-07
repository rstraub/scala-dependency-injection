package train

import com.softwaremill.macwire.wire

package object station {
  trait StationModule {
    lazy val trainDispatch: TrainDispatch = wire[TrainDispatch]

    lazy val trainStation: TrainStation = wire[TrainStation]

    // dependencies of the module
    def trainShunter: TrainShunter
    def trainLoader: TrainLoader
  }
}
