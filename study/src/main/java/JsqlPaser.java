import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;

public class JsqlPaser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from dk_message " + " where app_code in"
				+ " (select distinct du.app_code"
				+ "  from tgpms.dk_user_security DUS, DK_USERAPP DU"
				+ "  where DUS.user_name = '622157404565475328'"
				+ "   and DUS.sercurity_code = 'APPSUPPORT'"
				+ "   AND DUS.ORG_ID = DU.ORG_ID) " + " and msg_to is null";
		String sql1 = "SELECT dp.dpname1 AS 部门, cust_dp_SumOddfre.sum_oddfare AS 当月卡总余额 "
				+ "FROM (SELECT T_Department.DpCode1, SUM(custid_SumOddfare_group.sum_oddfare)          "
				+ "      AS sum_oddfare          FROM (SELECT l2.CustomerID, SUM(r1.oddfare) AS sum_oddfare  "
				+ "               FROM (SELECT CustomerID, MAX(OpCount) AS max_opcount                       "
				+ "   FROM (SELECT CustomerID, OpCount, RTRIM(CAST(YEAR(OpDt)                              "
				+ "          AS char)) + '-' + RTRIM(CAST(MONTH(OpDt) AS char))   "
				+ "   + '-' + RTRIM(DAY(0)) AS dt                       "
				+ "          FROM T_ConsumeRec                             "
				+ "    UNION                            "
				+ "      SELECT CustomerID, OpCount, RTRIM(CAST(YEAR(cashDt)     "
				+ "     AS char)) + '-' + RTRIM(CAST(MONTH(cashDt) AS char))"
				+ " + '-' + RTRIM(DAY(0)) AS dt  "
				+ " FROM T_Cashrec) l1   "
				+ " WHERE (dt <= '2005-6-1')/*输入查询月份,可用参数传递*/  "
				+ "  GROUP BY CustomerID) l2 INNER JOIN    "
				+ " (SELECT CustomerID, OpCount, oddfare  "
				+ " FROM T_ConsumeRec   "
				+ "   UNION   "
				+ " SELECT CustomerID, OpCount, oddfare   "
				+ "  FROM T_Cashrec) r1 ON l2.CustomerID = r1.CustomerID AND    "
				+ "  r1.OpCount = l2.max_opcount   "
				+ " GROUP BY l2.CustomerID) custid_SumOddfare_group INNER JOIN  "
				+ "  T_Customers ON    "
				+ "  custid_SumOddfare_group.CustomerID = T_Customers.CustomerID INNER JOIN  "
				+ " T_Department ON SUBSTRING(T_Customers.Account, 1, 2)  "
				+ " = T_Department.DpCode1 AND SUBSTRING(T_Customers.Account, 3, 2) "
				+ "  = T_Department.DpCode2 AND SUBSTRING(T_Customers.Account, 5, 3)  "
				+ "  = T_Department.DpCode3          GROUP BY DpCode1) cust_dp_SumOddfre INNER JOIN    "
				+ "       (SELECT DISTINCT dpcode1, dpname1       "
				+ "    FROM t_department) dp ON dp.dpcode1 = cust_dp_SumOddfre.DpCode1 ";
		TablesNamesFinder tnf = new TablesNamesFinder();
		Statement statement;
		try {
			statement = CCJSqlParserUtil.parse(sql1);
			List<String> tables = tnf.getTableList(statement);
			for (String table : tables) {
				System.out.println(table);
			}
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
