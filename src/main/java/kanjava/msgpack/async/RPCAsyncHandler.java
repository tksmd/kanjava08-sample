package kanjava.msgpack.async;

import org.msgpack.rpc.Future;

public interface RPCAsyncHandler {	

	// heavy が呼ばれる
	public Future<String> heavyAsync();
	
	// light が呼ばれる
	public Future<String> lightAsync();

}
