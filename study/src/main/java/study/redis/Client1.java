package study.redis;

import redis.clients.jedis.Jedis;

public class Client1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("192.168.99.100",32770);
		jedis.set("test", "123asfsdklf2i34powfjejsadkf");
//		System.out.println(((Message)ObjectUtil.bytesToObject(jedis.get("onemessage".getBytes()))).getContent());
		String value = jedis.get("foo");
		System.out.println(value);
		System.out.println(jedis.get("test"));
		jedis.close();
	}

}
