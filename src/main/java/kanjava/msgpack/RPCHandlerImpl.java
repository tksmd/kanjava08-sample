package kanjava.msgpack;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPCHandlerImpl implements RPCHandler {

	protected static Logger LOG = LoggerFactory.getLogger(RPCHandlerImpl.class);

	@Override
	public String hello(String message) {
		LOG.debug("called hello");
		return "Hello " + message;
	}

	@Override
	public List<RPCMessage> getMessages() {
		LOG.debug("called getMessages");
		List<RPCMessage> ret = new ArrayList<RPCMessage>();
		ret.add(create(1,"s_kozake"));
		ret.add(create(2,"tksmd"));
		ret.add(create(3,"hakurai"));
		ret.add(create(4,"irof?!"));
		return ret;
	}

	private static RPCMessage create(int id, String body) {
		RPCMessage ret = new RPCMessage();
		ret.id = id;
		ret.body = body;
		return ret;
	}

}
