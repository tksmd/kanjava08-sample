package kanjava.msgpack;

import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;

public class RPCServer {

	static final int PORT = 8888;

	private EventLoop loop;

	public void start() throws Exception {
		loop = EventLoop.defaultEventLoop();
		Server server = new Server(loop);
		server.listen(PORT);
		server.serve(new RPCHandlerImpl());
		loop.join();
	}

	public void stop() {
		loop.shutdown();
	}

	public static void main(String[] args) {
		RPCServer server = new RPCServer();
		try {
			server.start();
		} catch (Exception e) {
			server.stop();
		}
	}

}
