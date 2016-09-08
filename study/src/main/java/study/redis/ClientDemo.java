package study.redis;

import redis.clients.jedis.Jedis;

public class ClientDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("localhost");
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.close();
	}

}
