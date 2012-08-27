package kanjava.msgpack;

import org.msgpack.annotation.Message;

@Message
public class RPCMessage {

	public int id;

	public String body;

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("id=").append(id);
		buf.append(",");
		buf.append("body=").append(body);
		return buf.toString();
	}

}
