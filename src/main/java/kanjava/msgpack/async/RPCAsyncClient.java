package kanjava.msgpack.async;

import kanjava.msgpack.RPCClient;

public class RPCAsyncClient extends RPCClient<RPCAsyncHandler> {

	public RPCAsyncClient() {
		super(RPCAsyncHandler.class);
	}

}
