package study.codewars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Order1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(order("is2 Thi1s T4est 3a"));
	}

	public static String order(String words) {
		// ...
		if(words == null ||"".equals(words)){
			return "";
		}
		String[] wordArray = words.split(" ");
		int[] ints = new int[wordArray.length];
		String result = "";
		Map<Integer, String> index = new HashMap<Integer, String>();
		for (String word : wordArray) {
			for(int i = 0;i< word.length();i++){
				try{
					int a = (int)word.charAt(i);
					Integer.parseInt(""+word.charAt(i));
					ints[index.size()] = a;
					index.put(a, word);
					break;
				}catch(Exception e){
					//
				}
			}
		}
		Arrays.sort(ints);
		int j = 0;
		for(int i : ints){
			if(j == ints.length -1) {
				result += index.get(i); 
			} else {
				result += index.get(i) +" "; 
			}
			j++;
		}
		return result;
	}

}
