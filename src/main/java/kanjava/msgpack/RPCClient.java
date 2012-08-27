package kanjava.msgpack;

import org.msgpack.rpc.Client;
import org.msgpack.rpc.loop.EventLoop;

public class RPCClient {

	private Client client;

	private EventLoop loop;

	private RPCHandler handler;

	public void start() throws Exception {
		loop = EventLoop.defaultEventLoop();
		client = new Client("localhost", RPCServer.PORT, loop);
		handler = client.proxy(RPCHandler.class);
	}

	public void stop() {
		client.close();
		loop.shutdown();
	}
	
	public RPCHandler getHandler() {
		return handler;
	}

}
