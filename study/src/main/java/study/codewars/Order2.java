package study.codewars;

public class Order2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countBits(10));
		System.out.println(Integer.bitCount(1234));
	}
	
	public static int countBits(int n){
		// Show me the code!
		String binary = Integer.toBinaryString(n);
		int count = 0;
		for(int i = 0;i<binary.length();i++){
			if(binary.charAt(i) == '1'){
				count ++;
			}
		}
		return count;
	}

}
