// @GENERATOR:play-routes-compiler
// @SOURCE:D:/ITProject/ITSD-DT2021-Template/conf/routes
// @DATE:Mon Jun 28 21:33:36 BST 2021

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  GameScreenController_0: controllers.GameScreenController,
  // @LINE:10
  Assets_1: controllers.Assets,
  // @LINE:12
  webjars_Routes_0: webjars.Routes,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    GameScreenController_0: controllers.GameScreenController,
    // @LINE:10
    Assets_1: controllers.Assets,
    // @LINE:12
    webjars_Routes_0: webjars.Routes
  ) = this(errorHandler, GameScreenController_0, Assets_1, webjars_Routes_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, GameScreenController_0, Assets_1, webjars_Routes_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """game""", """controllers.GameScreenController.index(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """gamews""", """controllers.GameScreenController.socket()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    prefixed_webjars_Routes_0_3.router.documentation,
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_GameScreenController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("game")))
  )
  private[this] lazy val controllers_GameScreenController_index0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GameScreenController_0.index(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GameScreenController",
      "index",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """game""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_GameScreenController_socket1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("gamews")))
  )
  private[this] lazy val controllers_GameScreenController_socket1_invoker = createInvoker(
    GameScreenController_0.socket(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GameScreenController",
      "socket",
      Nil,
      "GET",
      this.prefix + """gamews""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Assets_at2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at2_invoker = createInvoker(
    Assets_1.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:12
  private[this] val prefixed_webjars_Routes_0_3 = Include(webjars_Routes_0.withPrefix(this.prefix + (if (this.prefix.endsWith("/")) "" else "/") + "webjars"))


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_GameScreenController_index0_route(params@_) =>
      call { 
        controllers_GameScreenController_index0_invoker.call(
          req => GameScreenController_0.index(req))
      }
  
    // @LINE:7
    case controllers_GameScreenController_socket1_route(params@_) =>
      call { 
        controllers_GameScreenController_socket1_invoker.call(GameScreenController_0.socket())
      }
  
    // @LINE:10
    case controllers_Assets_at2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at2_invoker.call(Assets_1.at(path, file))
      }
  
    // @LINE:12
    case prefixed_webjars_Routes_0_3(handler) => handler
  }
}
