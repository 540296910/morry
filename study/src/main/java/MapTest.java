import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String key = "map12";
		int hash = key.hashCode();
		System.out.println(hash);
		int index = (hash & 0x7FFFFFFF) % 11;
		System.out.println(index);
		System.out.println(0x7FFFFFFF);
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		System.out.println(new java.util.Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new java.util.Date()));
	}

}
