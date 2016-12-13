package study.redis;

import redis.clients.jedis.Jedis;

public class Client1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("localhost");
		System.out.println(((Message)ObjectUtil.bytesToObject(jedis.get("onemessage".getBytes()))).getContent());
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.close();
	}

}
