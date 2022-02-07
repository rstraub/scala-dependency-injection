package train.manual

import train.injection.manual.LoadingModule
import train.shunting
import train.shunting.PointSwitcher

class TestLoadingModule extends LoadingModule {
  override def pointSwitcher: shunting.PointSwitcher = new PointSwitcher {
    override def switch(): Unit = println("actually a fake")
  }
}
