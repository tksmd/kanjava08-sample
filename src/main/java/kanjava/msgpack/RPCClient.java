package kanjava.msgpack;

import org.msgpack.rpc.Client;
import org.msgpack.rpc.loop.EventLoop;

public class RPCClient<T> {

	private Client client;

	private EventLoop loop;

	private Class<T> handlerClass;

	protected RPCClient(Class<T> handlerClass) {
		this.handlerClass = handlerClass;
	}
	
	public static RPCClient<RPCHandler> newRPCClient() {
		return new RPCClient<RPCHandler>(RPCHandler.class);
	}

	public void start() throws Exception {
		loop = EventLoop.defaultEventLoop();
		client = new Client("localhost", RPCServer.PORT, loop);
	}

	public void stop() {
		client.close();
		loop.shutdown();
	}

	public T getHandler() {
		return client.proxy(handlerClass);
	}

}
