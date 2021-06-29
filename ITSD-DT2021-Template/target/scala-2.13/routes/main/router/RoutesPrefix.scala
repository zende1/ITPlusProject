// @GENERATOR:play-routes-compiler
// @SOURCE:D:/ITProject/ITSD-DT2021-Template/conf/routes
// @DATE:Mon Jun 28 21:33:36 BST 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
