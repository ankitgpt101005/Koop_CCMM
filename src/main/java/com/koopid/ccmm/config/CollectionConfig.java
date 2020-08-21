package com.koopid.ccmm.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

public class CollectionConfig {
	private static Map<String, Session> chatSessionMap = new HashMap<>();
	private static List<String> routeQueuedList = new ArrayList<>();
	private static List<String> routeList = new ArrayList<>();

	private CollectionConfig() {
		throw new IllegalStateException();
	}

	public static Map<String, Session> getChatSessionMap() {
		return chatSessionMap;
	}

	public static List<String> getRouteQueuedList() {
		return routeQueuedList;
	}

	public static List<String> getRouteList() {
		return routeList;
	}
}
