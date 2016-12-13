package study.assertt;

public class AssertTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  //断言1结果为true，则继续往下执行
		try{
        assert true;
        System.out.println("断言1没有问题，Go！");
 
        System.out.println("\n-----------------\n");
 
        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

}

class StringUtils {
	public static String encode(String str) {
		assert str != null : "加密的字符串为null";
		/* 加密处理 */
		return str;
	}
}