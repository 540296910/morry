package study.redis;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Jedis;

public class ClientDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("localhost");
		Message mes = new Message();
		mes.setContent("你好");
		mes.setId(100);
		jedis.set("onemessage".getBytes(),ObjectUtil.objectToBytes(mes));
		System.out.println(((Message)ObjectUtil.bytesToObject(jedis.get("onemessage".getBytes()))).getContent());
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.close();
	}

}
