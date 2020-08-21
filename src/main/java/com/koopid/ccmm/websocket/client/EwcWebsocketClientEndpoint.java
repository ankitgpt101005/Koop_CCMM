package com.koopid.ccmm.websocket.client;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.exception.SocketConnectionHandler;

@Component
@ClientEndpoint(configurator = ClientConfigurator.class)
public class EwcWebsocketClientEndpoint {

	private static final Logger log = LoggerFactory.getLogger(EwcWebsocketClientEndpoint.class);

	Session userSession = null;
	private MessageHandler messageHandler;
	private String routingContext;

	public String getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(String routingContext) {
		this.routingContext = routingContext;
	}

	public Session ewcWebsocketClientEndpointContainer(URI endpointURI) {

		Session socketSession = null;
		WebSocketContainer container;
		container = ContainerProvider.getWebSocketContainer();
		try {
			socketSession = container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new SocketConnectionHandler("Error connecting to web socket server");

		}
		return socketSession;
	}

	@OnOpen
	public void onOpen(Session userSession) {
		log.debug("[{}] wsOpen ", this.routingContext);
		this.userSession = userSession;
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		log.debug("[{}] wsClose: {}", this.routingContext, reason);
		this.userSession = null;
		CollectionConfig.getChatSessionMap().remove(this.routingContext);
	}

	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	public void sendMessage(String message, Session userSession) {
		log.debug("[{}] wsSendMessage - {}", this.routingContext, message);
		userSession.getAsyncRemote().sendText(message);
	}

	public static interface MessageHandler {
		public void handleMessage(String message);
	}

}
