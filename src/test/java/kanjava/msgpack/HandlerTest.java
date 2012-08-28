package kanjava.msgpack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.msgpack.MessageTypeException;
import org.msgpack.rpc.Client;
import org.msgpack.rpc.Request;
import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;

public class HandlerTest {

	public interface TestInterface {
		public String method();
	}

	public class TestHandler {
		public void method(Request request) {
			request.sendResult("ok");
		}
	}

	@Test
	public void インターフェースと実装は違っていてもよい() throws Exception {
		EventLoop loop = EventLoop.start();

		Server server = new Server(loop);
		server.serve(new TestHandler());
		server.listen(8969);

		Client client = new Client("127.0.0.1", 8969, loop);
		TestInterface handler = client.proxy(TestInterface.class);
		try {
			String actual = handler.method();
			assertThat(actual, is("ok"));
		} finally {
			server.close();
			client.close();
			loop.shutdown();
		}
	}

	public class ExecptionHandler {

		public String method() {
			return delegate.method();
		}

		private Delegate delegate;

		public void setDelegate(Delegate delegate) {
			this.delegate = delegate;
		}

		class Delegate {
			String method() {
				return "out!";
			}
		}
	}

	@Test
	public void publicメソッドには注意してね() throws Exception {
		EventLoop loop = EventLoop.start();
		Server server = new Server(loop);
		try {
			server.serve(new ExecptionHandler());
		} catch (MessageTypeException e) {
			assertTrue("setDelegate を RPC メソッドとして登録しようとする", true);
		} finally {
			server.close();
			loop.shutdown();
		}
	}

}
