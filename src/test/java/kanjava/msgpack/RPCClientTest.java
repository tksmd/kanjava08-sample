package kanjava.msgpack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.msgpack.rpc.error.RemoteError;

public class RPCClientTest {

	private static RPCClient<RPCHandler> RPC;

	@BeforeClass
	public static void クライアントを準備() throws Exception {
		RPC = RPCClient.newRPCClient();
		RPC.start();
	}

	@AfterClass
	public static void クライアントを停止() throws Exception {
		RPC.stop();
	}

	@Test
	public void お約束の呼び出し() throws Exception {
		RPCHandler handler = RPC.getHandler();
		String actual = handler.hello("Kanjava!");
		assertThat(actual, is("Hello Kanjava!"));
	}

	@Test
	public void オブジェクトのやりとり() throws Exception {
		RPCHandler handler = RPC.getHandler();
		List<RPCMessage> actual = handler.getMessages();
		assertThat(actual.size(), is(4));

		assertThat(actual.get(0).id, is(1));
		assertThat(actual.get(0).body, is("s_kozake"));

		assertThat(actual.get(1).id, is(2));
		assertThat(actual.get(1).body, is("tksmd"));

		assertThat(actual.get(2).id, is(3));
		assertThat(actual.get(2).body, is("hakurai"));

		assertThat(actual.get(3).id, is(4));
		assertThat(actual.get(3).body, is("irof?!"));
	}

	@Test(expected = RemoteError.class)
	public void エラー() throws Exception {
		RPCHandler handler = RPC.getHandler();
		String error = handler.error("エラーかなー");
		System.out.println(error);
	}

}
