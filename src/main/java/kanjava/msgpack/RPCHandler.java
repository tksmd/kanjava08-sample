package kanjava.msgpack;

import java.util.List;

public interface RPCHandler {

	public String hello(String message);

	public List<RPCMessage> getMessages();

}
