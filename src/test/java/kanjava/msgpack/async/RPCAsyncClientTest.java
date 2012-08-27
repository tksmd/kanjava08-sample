package kanjava.msgpack.async;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.msgpack.rpc.Future;

public class RPCAsyncClientTest {

	private static RPCAsyncClient RPC;

	@BeforeClass
	public static void クライアントを準備() throws Exception {
		RPC = new RPCAsyncClient();
		RPC.start();
	}

	@AfterClass
	public static void クライアントを停止() throws Exception {
		RPC.stop();
	}

	@Test(timeout = 12000)
	public void 非同期呼び出し() throws Exception {
		RPCAsyncHandler handler = RPC.getHandler();

		Future<String> f1 = handler.heavyAsync();
		Future<String> f2 = handler.lightAsync();
		
		assertThat(f2.get(), is("light"));
		assertThat(f1.get(), is("heavy"));
	}

}
