package study.common;

public class UUID {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uuid = java.util.UUID.randomUUID().toString();
		long uuidl = java.util.UUID.randomUUID().timestamp();
		//02315bff-e5b7-4b09-99da-6a6cd7226567
		System.out.println(uuid);
		System.out.println(uuidl);
	}

}
