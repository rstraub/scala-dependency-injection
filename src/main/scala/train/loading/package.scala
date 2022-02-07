package train

import train.shunting.PointSwitcher

package object loading {
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
