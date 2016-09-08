package study.common;

public class ByteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "B4SpPbhSVf082DFTqq";
		for ( byte b : s.getBytes()) {
			System.out.println("(byte)0x"+b+",");
		}
	}

}
