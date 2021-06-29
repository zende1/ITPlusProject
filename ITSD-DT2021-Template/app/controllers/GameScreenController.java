package controllers;

import javax.inject.Inject;

import actors.GameActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import play.data.Form;
import play.data.FormFactory;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;
import structures.User;

/**
 * This is the Controller class for the game. 
 * @author Dr. Richard McCreadie
 *
 */
public class GameScreenController extends Controller {

	private final ActorSystem actorSystem;
	private final Materializer materializer;
	Form<User> userForm = null;
	
	
	@Inject
	public GameScreenController(FormFactory formFactory, ActorSystem actorSystem, Materializer materializer) {
		this.actorSystem = actorSystem;
		this.materializer = materializer;
		userForm = formFactory.form(User.class);
	}

	/**
	 * This responds to the request for creation of the Websocket 
	 * @return
	 */
	public WebSocket socket() {

		return WebSocket.Json.accept(
				request -> ActorFlow.actorRef(this::createGameActor, actorSystem, materializer));
	}

	/**
	 * This method responds to the original request for the /game screen
	 * @param request
	 * @return
	 */
	public Result index(Http.Request request) {
		return ok(views.html.gamescreen.render(request, null));
	}
	
	public Props createGameActor(ActorRef out) {
		return Props.create(GameActor.class, out); // calls the constructor for Game Actor
	}
}