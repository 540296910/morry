package study.activemq;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import study.websocket.WebSocketTest;

public class MessageClients {
	private static ConcurrentMap<String, WebSocketTest> clients = new ConcurrentHashMap<String, WebSocketTest>();
	public static void addClient (String name,WebSocketTest socket) {
		clients.put(name, socket);
	}
	
	public static WebSocketTest getClient(String name) {
		return clients.get(name);
	}
	
	public static ConcurrentMap<String, WebSocketTest> getClients() {
		return clients;
	}
	
	public static void clear() {
		clients.clear();
	}
	
	public static void remove(String name) {
		clients.remove(name);
	}
	
	public static int getCounts () {
		return clients.size();
	}
}