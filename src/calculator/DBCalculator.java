package calculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import decoder.Decoder;

public class DBCalculator
{
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
	
	public DBCalculator(String DBName, String clickLogLocation,
			String impressionLogLocation, 	
			String serverLogLocation)
	{
		Connection conn = null;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DBName + ".db");
        } catch (Exception e) 
        {
            System.err.println(e);
        }
        
        try 
        {
            String sql = "";
            Statement stmt;
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
           
            sql = "CREATE TABLE IF NOT EXISTS CLICKCOST" +
                    "(ID          INTEGER PRIMARY KEY NOT NULL," +
                    " UID         CHAR(50)            NOT NULL," +
                    " DATE        DATETIME            NOT NULL, " +
                    " COST        REAL                NOT NULL, UNIQUE(ID) ON CONFLICT IGNORE);";
            stmt.executeUpdate(sql);
            conn.commit();

            Decoder decoder = new Decoder();
            CSVReader reader = decoder.getRawData(clickLogLocation, "ClickLog");
            String[] nextLine;
            int i = 0;
            while ((nextLine = reader.readNext()) != null)
    		{
    			System.out.println("Read Click log line");
    			sql = "INSERT INTO CLICKCOST (ID,UID,DATE,COST) " +
                        "VALUES (" + i + ",'" + nextLine[1] + "','" + fmt.parse(nextLine[0]) +
                        "'," + nextLine[2] + ");";
    			stmt.addBatch(sql);
    			i++;
    			
             }

            stmt.executeBatch();
            conn.commit();

            sql = "CREATE TABLE IF NOT EXISTS IMPRESSIONLOG" +
                    "(UID      CHAR(50)        NOT NULL," +
                    "GENDER    CHAR(50)        NOT NULL," +
                    "INCOME    CHAR(50)        NOT NULL," +
                    "CONTEXT   CHAR(50)        NOT NULL," +
                    "AGE       CHAR(50)        NOT NULL," +
                    "DATE      DATETIME        NOT NULL," +
                    "COST      FLOAT           NOT NULL, UNIQUE(UID,DATE) ON CONFLICT IGNORE);";
            stmt.executeUpdate(sql);
            conn.commit();

           reader = decoder.getRawData(impressionLogLocation, "ImpressionLog");

           while ((nextLine = reader.readNext()) != null)
           {
   				System.out.println("Read Impression log line");
   				sql = "INSERT INTO IMPRESSIONLOG (UID,GENDER,INCOME,CONTEXT,AGE,DATE,COST) " +
   						"VALUES ('" + nextLine[1] + "','" + nextLine[2] + "','" + nextLine[4] + "','" + nextLine[5]
   						+ "','" + nextLine[3] + "','" + fmt.parse(nextLine[0]) + "'," + nextLine[6] + ");";
   				stmt.addBatch(sql);
            }

            stmt.executeBatch();
            conn.commit();
           
            sql = "CREATE TABLE IF NOT EXISTS SERVERLOG" +
                    "(ID             INTEGER PRIMARY KEY NOT NULL," +
                    " UID            CHAR(50)            NOT NULL," +
                    " DATESTART      DATETIME            NOT NULL, " +
                    " DATEEND        DATETIME            NOT NULL, " +
                    " PAGEVIEW       LONG                NOT NULL," +
                    " CONVERSATION   INTEGER             NOT NULL, UNIQUE(ID) ON CONFLICT IGNORE);";
            stmt.executeUpdate(sql);
            conn.commit();
            
            reader = decoder.getRawData(serverLogLocation, "ImpressionLog");
            i = 0;
            while ((nextLine = reader.readNext()) != null)
            {
            	int conversion = 0;
            	if(nextLine[4].equals("Yes"))
            	{
            		conversion = 1;
            	}
    				System.out.println("Read Server log line");
    				sql = "INSERT INTO SERVERLOG (ID,UID,DATESTART,DATEEND,PAGEVIEW,CONVERSATION) " +
                            "VALUES (" + i + ",'" + nextLine[1] + "','" + fmt.parse(nextLine[0]) +
                            "','" + fmt.parse(nextLine[2]) + "'," + nextLine[3] + "," + conversion + ");";
                    stmt.addBatch(sql);
    				i++;
    			
             }

            stmt.executeBatch();
            conn.commit();

            sql = "CREATE TABLE IF NOT EXISTS ALLT AS SELECT USERID, DATESTART, DATEEND, PAGEVIEW, CONVERSATION, CLICKDATE, CLICKCOST, IMPRESSIONDATE, GENDER, INCOME, CONTEXT, AGE, IMPRESSIONCOST " +
                    "FROM SERVERLOG join " +
                    "(SELECT CLICKCOST.ID AS CLICKID, CLICKCOST.UID AS USERID, CLICKCOST.DATE AS CLICKDATE, CLICKCOST.COST AS CLICKCOST, max(IMPRESSIONLOG.DATE) AS IMPRESSIONDATE, GENDER, INCOME, CONTEXT, AGE, IMPRESSIONLOG.COST AS IMPRESSIONCOST " +
                    "FROM (IMPRESSIONLOG INNER JOIN CLICKCOST " +
                    "ON IMPRESSIONLOG.UID = CLICKCOST.UID AND IMPRESSIONLOG.DATE <= CLICKCOST.DATE) GROUP BY CLICKCOST.UID, CLICKCOST.DATE) " +
                    "on SERVERLOG.ID = CLICKID;";

            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close();
            conn.commit();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
	}
	
	private String makeQuery(ArrayList<String> query){
        String ret = "";
        for (int i = 0; i < query.size(); ++i) 
        {
            if (i == 0) 
            {
                ret += "WHERE ";
            } 
            else
            {
            	ret += " AND ";
           	}
           
            ret += query.get(i);
        }
        ret += ";";
        return ret;
    }
}























