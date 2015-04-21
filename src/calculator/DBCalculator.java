package calculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVReader;

import decoder.Decoder;

public class DBCalculator implements CalculatorInterface
{
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Connection conn = null;
	
	public DBCalculator(String DBName, String clickLogLocation,
			String impressionLogLocation, 	
			String serverLogLocation)
	{
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
           
            sql = "CREATE TABLE IF NOT EXISTS CLICKLOG" +
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
    			sql = "INSERT INTO CLICKLOG (ID,UID,DATE,COST) " +
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
                    " PAGEVIEW       INTEGER             NOT NULL," +
                    " CONVERSION   INTEGER             NOT NULL, UNIQUE(ID) ON CONFLICT IGNORE);";
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
            	Date endDate;
            	if (nextLine[2].equals("n/a")) {
                    endDate = new Date(0);
                } else {
                    endDate = fmt.parse(nextLine[2]);
                }
    			System.out.println("Read Server log line");
    			sql = "INSERT INTO SERVERLOG (ID,UID,DATESTART,DATEEND,PAGEVIEW,CONVERSION) " +
                           "VALUES (" + i + ",'" + nextLine[1] + "','" + fmt.parse(nextLine[0]) +
                           "','" + endDate + "'," + nextLine[3] + "," + conversion + ");";
                stmt.addBatch(sql);
    			i++;
    			
             }

            stmt.executeBatch();
            conn.commit();

            sql = "CREATE TABLE IF NOT EXISTS ALLT AS SELECT USERID, DATESTART, DATEEND, PAGEVIEW, CONVERSION, CLICKDATE, CLICKCOST, IMPRESSIONDATE, GENDER, INCOME, CONTEXT, AGE, IMPRESSIONCOST " +
                    "FROM SERVERLOG join " +
                    "(SELECT CLICKLOG.ID AS CLICKID, CLICKLOG.UID AS USERID, CLICKLOG.DATE AS CLICKDATE, CLICKLOG.COST AS CLICKCOST, max(IMPRESSIONLOG.DATE) AS IMPRESSIONDATE, GENDER, INCOME, CONTEXT, AGE, IMPRESSIONLOG.COST AS IMPRESSIONCOST " +
                    "FROM (IMPRESSIONLOG INNER JOIN CLICKLOG " +
                    "ON IMPRESSIONLOG.UID = CLICKLOG.UID AND IMPRESSIONLOG.DATE <= CLICKLOG.DATE) GROUP BY CLICKLOG.UID, CLICKLOG.DATE) " +
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
	
	public void close() 
	{
        try 
        {
           conn.close();
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public Date getMinDate(String val, String opt)
	{
        try 
        {
            String sql = "SELECT min(" + val + ") as res from " + opt + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            Date date = (Date) fmt.parse(rs.getString("res"));
            return date;
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            return new Date(0);
        }
    }

    public Date getMaxDate(String val, String opt)
    {
        try
        {
            String sql = "SELECT max(" + val + ") as res from " + opt + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            Date date = (Date) fmt.parse(rs.getString("res"));
            return date;
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
            return new Date(0);
        }
    }
	
	public double getClickCount(FilterOption fo) 
	{
        Double qr = getFilteredClickLog(fo, "COUNT(*)");
        return qr;
    }
	
	/**
	 * 
	 * @param filter
	 * @param option
	 * @param interval in seconds
	 * @return
	 */
    public ArrayList<Double> getClickCount(FilterOption filter, String option, int interval) 
    {
        ArrayList<Double> countList = new ArrayList<>();
        Date date1 = filter.startDate;
        if(date1 == null) {
            date1 = getMinDate("DATE", "CLICKLOG");
        }
        Date date2 = filter.endDate;
        if(date2 == null) {
            date2 = getMaxDate("DATE", "CLICKLOG");
        }
        for(int i=(int) (date1.getTime()/1000/interval); i <= (date2.getTime()/1000/interval); i++)
        {
            FilterOption fo = filter.clone();
            fo.startDate = new Date(i*interval*1000);
            fo.endDate = new Date(((i+1)*interval - 1)*1000);

            double count = getFilteredClickLog(fo, option);
            countList.add(count);
        }
        return countList;
    }
    private Double getFilteredClickLog(FilterOption filter, String option) 
    {
    	ArrayList<String> query = new ArrayList<>();
        String sql = "SELECT " + option + " AS total FROM CLICKLOG ";

        if (filter.id != null) {
            query.add("UID = '" + filter.id + "'");
        }
        if (filter.startDate != null) {
            query.add("DATE >= '" + fmt.format((TemporalAccessor) filter.startDate) + "'");
        }
        if (filter.endDate != null) {
            query.add("DATE <= '" + fmt.format((TemporalAccessor) filter.endDate) + "'");
        }
        sql += makeQuery(query);
        try 
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            double ret = rs.getDouble("total");
            stmt.close();
            return (ret);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


	@Override
	public Integer[] getImpressionNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getClickNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getUniqueNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getConversionNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getImpressionCost(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getClickCost(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getCTR(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getCPA(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getCPC(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getCPM(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getBounceRateByPages(int interval, int pageViewed)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getBounceRateByTime(int interval, int timeSpent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getBounceNumberByPages(int interval, int pageViewd)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getBounceNumberByTime(int interval, int timeSpent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] getClickCostDistribution(float costInterval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getHistogramClicks()
	{
		// TODO Auto-generated method stub
		return null;
	}
}























