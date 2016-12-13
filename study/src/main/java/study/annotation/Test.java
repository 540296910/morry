package study.annotation;

import java.lang.reflect.Field;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class<?> c = Class.forName("study.annotation.Worker");
		System.out.println(c.getName());
		Field[] fields = c.getDeclaredFields();
		System.out.println(fields.length);
		for(Field field : fields) {
			System.out.println(field);
			System.out.println(field.getName());
			System.out.println(field.getAnnotation(NotNeed.class));
			NotNeed not = field.getAnnotation(NotNeed.class);
			if(not != null) {
				System.out.println(not.value());
			}
			
		}
		String s = "UPDATE";
		if(!s.equals("VALIDA")&&!s.equals("UPDATE")){
			System.out.println("*********************************");
		}
	}

}
