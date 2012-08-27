package kanjava.msgpack;

import java.util.List;

public interface RPCHandler {

	public String hello(String message);

	public List<RPCMessage> getMessages();
		
	public String error(String message);
	
	public String heavy();
	
	public String light();

}
