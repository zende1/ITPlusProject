
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._

object gamescreen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[play.mvc.Http.Request,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: play.mvc.Http.Request, user: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>ITSD Card Game Main Screen</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href=""""),_display_(/*8.39*/routes/*8.45*/.Assets.at("css/uikit.css")),format.raw/*8.72*/("""" />
        
        <script src=""""),_display_(/*10.23*/routes/*10.29*/.Assets.at("js/jquery.3.4.1.js")),format.raw/*10.61*/(""""></script> 
        <script src=""""),_display_(/*11.23*/routes/*11.29*/.Assets.at("js/uikit.js")),format.raw/*11.54*/(""""></script>
        <script src=""""),_display_(/*12.23*/routes/*12.29*/.Assets.at("js/uikit-icons.js")),format.raw/*12.60*/(""""></script>
		<script src=""""),_display_(/*13.17*/routes/*13.23*/.Assets.at("js/hexi.min.js")),format.raw/*13.51*/(""""></script>

    </head>
    <body id="mainBody" wsdata=""""),_display_(/*16.34*/routes/*16.40*/.GameScreenController.socket.webSocketURL(request)),format.raw/*16.90*/("""" onload="init()">


	

	<script src=""""),_display_(/*21.16*/routes/*21.22*/.Assets.at("js/cardgame.js")),format.raw/*21.50*/(""""></script>
	<script type="text/javascript">
	
	// // Load them google fonts before starting...!
	window.WebFontConfig = """),format.raw/*25.25*/("""{"""),format.raw/*25.26*/("""
    	"""),format.raw/*26.6*/("""google: """),format.raw/*26.14*/("""{"""),format.raw/*26.15*/("""
        	"""),format.raw/*27.10*/("""families: ['Roboto']
    	"""),format.raw/*28.6*/("""}"""),format.raw/*28.7*/(""",

   		active: function() """),format.raw/*30.25*/("""{"""),format.raw/*30.26*/("""
        	"""),format.raw/*31.10*/("""// do something
        	init();
   		"""),format.raw/*33.6*/("""}"""),format.raw/*33.7*/("""
	"""),format.raw/*34.2*/("""}"""),format.raw/*34.3*/(""";
	
	let stageWidth = 1920;
	let stageHeight = 1080;
	let moveVelocity = 2;
	
	var ws;
	var userDataSession;
	var g;
	var gameActorInitalized = false;
	var gameStart = false;
	var sinceLastHeartbeat = 0;
	
	// game objects
	let boardTiles = new Map()
	let spriteContainers = new Map()
	let sprites = new Map()
	let attackLabels = new Map()
	let healthLabels = new Map()
	let handContainers = [null,null,null,null,null,null]
	let handSprites = [null,null,null,null,null,null];
	let cardJSON = [null,null,null,null,null,null];
	let cardPreview = null;
	let prevewCountdown = 0;
	
	let activeMoves = new Map()
	let activeProjectiles = [];
	let drawUnitQueue = [];
	let drawTileQueue = [];
	
	let player1ManaIcons = new Map()
	let player2ManaIcons = new Map()
	
	let player1Health = null;
	let player2Health = null;
	
	let player1Notification = null;
	let player2Notification = null;
	let player1NotificationText = null;
	let player2NotificationText = null;
	
	let playingEffects = [];
	
	function init() """),format.raw/*77.18*/("""{"""),format.raw/*77.19*/("""
		"""),format.raw/*78.3*/("""openWebSocketConnection();
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	
	
	"""),format.raw/*82.2*/("""function openWebSocketConnection() """),format.raw/*82.37*/("""{"""),format.raw/*82.38*/("""
        """),format.raw/*83.9*/("""var wsURL = document.getElementById("mainBody").getAttribute("wsdata");

        //alert(wsURL);
        ws = new WebSocket(wsURL);
        ws.onmessage = function (event) """),format.raw/*87.41*/("""{"""),format.raw/*87.42*/("""
            """),format.raw/*88.13*/("""var message;
            message = JSON.parse(event.data);
			console.log(message);
            switch (message.messagetype) """),format.raw/*91.42*/("""{"""),format.raw/*91.43*/("""
                case "actorReady":
					initHexi(message.preloadImages);

					gameActorInitalized = true;
					break;
				case "drawTile":
					//console.log(message);
					drawTileQueue.push(message);
					break;
				case "drawUnit":
					drawUnitQueue.push(message);
					break;
				case "moveUnit":
				    moveUnit(message.unitID,message.tilex,message.tiley);
                    break;
				case "moveUnitToTile":
					moveUnitToTile(message);
					break;
				case "setUnitHealth":
					setUnitHealth(message);
					break;
				case "setUnitAttack":
					setUnitAttack(message);
					break;
				case "setPlayer1Health":
					setPlayer1Health(message);
					break;
				case "setPlayer2Health":
					setPlayer2Health(message);
					break;
				case "setPlayer1Mana":
					setPlayer1Mana(message);
					break;
				case "setPlayer2Mana":
					setPlayer2Mana(message);
					break;
				case "addPlayer1Notification":
					addPlayer1Notification(message);
					break;
				case "addPlayer2Notification":
					addPlayer2Notification(message);
					break;
				case "playUnitAnimation":
					playUnitAnimation(message);
					break;
				case "drawCard":
					drawCard(message);
					break;
				case "deleteCard":
					deleteCard(message);
					break;
				case "playEffectAnimation":
					playEffectAnimation(message);
					break;
				case "deleteUnit":
					deleteUnit(message);
					break;
				case "drawProjectile":
					drawProjectile(message);
					break;
                default:
                    return console.log(message);
            """),format.raw/*154.13*/("""}"""),format.raw/*154.14*/("""
        """),format.raw/*155.9*/("""}"""),format.raw/*155.10*/(""";
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/("""
	
	"""),format.raw/*158.2*/("""</script>
     
    </body>
</html>
"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request,user:String): play.twirl.api.HtmlFormat.Appendable = apply(request,user)

  def f:((play.mvc.Http.Request,String) => play.twirl.api.HtmlFormat.Appendable) = (request,user) => apply(request,user)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-06-28T21:33:36.784517300
                  SOURCE: D:/ITProject/ITSD-DT2021-Template/app/views/gamescreen.scala.html
                  HASH: f3675ccfb67c90c34999b73a1ad4752fafee5da3
                  MATRIX: 935->1|1076->47|1104->49|1366->285|1380->291|1427->318|1492->356|1507->362|1560->394|1623->430|1638->436|1684->461|1746->496|1761->502|1813->533|1869->562|1884->568|1933->596|2021->657|2036->663|2107->713|2178->757|2193->763|2242->791|2395->916|2424->917|2458->924|2494->932|2523->933|2562->944|2616->971|2644->972|2701->1001|2730->1002|2769->1013|2836->1053|2864->1054|2894->1057|2922->1058|3994->2102|4023->2103|4054->2107|4110->2136|4138->2137|4174->2146|4237->2181|4266->2182|4303->2192|4507->2368|4536->2369|4578->2383|4734->2511|4763->2512|6393->4113|6423->4114|6461->4124|6491->4125|6523->4129|6552->4130|6586->4136
                  LINES: 27->1|32->1|33->2|39->8|39->8|39->8|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|44->13|44->13|44->13|47->16|47->16|47->16|52->21|52->21|52->21|56->25|56->25|57->26|57->26|57->26|58->27|59->28|59->28|61->30|61->30|62->31|64->33|64->33|65->34|65->34|108->77|108->77|109->78|110->79|110->79|113->82|113->82|113->82|114->83|118->87|118->87|119->88|122->91|122->91|185->154|185->154|186->155|186->155|187->156|187->156|189->158
                  -- GENERATED --
              */
          