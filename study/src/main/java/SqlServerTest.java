import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlServerTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://kfpy3ve91l.database.chinacloudapi.cn:1433;database=morryAVvP7cGIqv4;user=morry@kfpy3ve91l;password=Maolei1990;encrypt=true;hostNameInCertificate=*.database.chinacloudapi.cn;loginTimeout=30;";
		Connection con = DriverManager.getConnection(url);
		System.out.println(con);
	}

}
