package app.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

@Service
public class AlertService {
	List<WebSocketSession> list;
	
	@Autowired
	Gson gson;
	
	
	public AlertService() {
		list = new ArrayList<>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean addSocket(WebSocketSession target) {
		return list.add(target);
	}

	public boolean removeSocket(WebSocketSession target) {
		return list.remove(target);
	}
	
	public void sendAll(String txt) {
		TextMessage msg = new TextMessage(txt);
		for(int i=0; i<list.size(); i++) {
			try {
				list.get(i).sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendAll(Map map) {
		sendAll(gson.toJson(map));
	}
}
