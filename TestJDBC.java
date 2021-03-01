import java.text.SimpleDateFormat;  
import java.util.Date;
import java.sql.*;
import java.util.*;

class A { 

	public String reqDate() 
	{
	Date date = new Date();  
    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yy");  
    	String strDate= "D" + formatter.format(date); 
	return strDate;
	 }
	
	public void getAttendance(String strDXX_XX,Statement st0)throws Exception
	{
	Statement st = st0;
	System.out.println("-------------------");
	System.out.println("ATTENDANCE(IOT): "+ strDXX_XX);
	System.out.println("-------------------");
	
	String sql = "ALTER TABLE IOT ADD "+strDXX_XX+" int;";
	st.executeUpdate(sql);

	Scanner s=new Scanner(System.in);
	boolean valid = true;
	while(valid)
	{
		System.out.println("Please Enter the Roll No: ");
		String temp = s.nextLine();
		try {
		int temp2 = Integer.parseInt(temp);
		String sql2= "UPDATE IOT SET "+strDXX_XX+"=1 WHERE Roll="+temp2+";" ;
		st.executeUpdate(sql2);
		} catch (NumberFormatException nfe) 
		{
		String sql3 = "SELECT "+strDXX_XX+", COUNT(*) AS presenties FROM IOT WHERE "+strDXX_XX+"=1;";
		ResultSet rs = st.executeQuery(sql3);
		rs.next();
		int count = rs.getInt("presenties");
		rs.close();
		System.out.println("\nTotal No.of Present People: " + count);
		break;
    		}
	}
	}
	
	public void getPercentage(int id,Statement st0)throws Exception
	{
	Statement st = st0;
	String sql="SELECT * FROM IOT;";
	ResultSet rs = st.executeQuery(sql);
	ResultSetMetaData md= rs.getMetaData();
	int col_count = md.getColumnCount();
	String final_string = "SELECT ";
	for (int i=3; i<=col_count; i++) {
 	String col_name = md.getColumnName(i);
 	if(i==col_count) { final_string = final_string + "COALESCE("+col_name+",0) "; }
	else { final_string = final_string + "COALESCE("+col_name+",0) + "; }
	}
	final_string = final_string + "AS Total FROM IOT WHERE Roll="+id+";";
	//System.out.println(final_string);
	ResultSet rs2 = st.executeQuery(final_string);
	rs2.next();
	int ind_count = rs2.getInt("Total");
	rs2.close();
	Double d1 = new Double(ind_count);
	Double d2 = new Double(col_count-2);
	Double per = (d1/d2)*100;
	String final_per = String.valueOf(per);
	if ( final_per.length() > 5 ) {
	final_per = final_per.substring(0,5);
	System.out.println("Percentage of Roll "+id+": "+final_per);}
	else { System.out.println("Percentage of Roll "+id+": "+final_per); }
	}

	public void getDebarlist(Statement st0)throws Exception
	{
	Statement st = st0;
	String sql="SELECT * FROM IOT;";
	ResultSet rs = st.executeQuery(sql);
	ResultSetMetaData md= rs.getMetaData();
	int col_count = md.getColumnCount();
	String final_string = "SELECT Roll, Name, ";
	for (int i=3; i<=col_count; i++) {
 	String col_name = md.getColumnName(i);
 	if(i==col_count) { final_string = final_string + "COALESCE("+col_name+",0) "; }
	else { final_string = final_string + "COALESCE("+col_name+",0) + "; }
	}
	final_string = final_string + "AS Total FROM IOT;";
	//System.out.println(final_string);

	ResultSet rs2 = st.executeQuery(final_string);
	ResultSetMetaData rsmd = rs2.getMetaData();
	int columnsNumber = rsmd.getColumnCount();
	while (rs2.next()) 
	{
	        int id= rs2.getInt("Roll");
	        String ind_name = rs2.getString("Name");
	        int ind_count = rs2.getInt("Total");
	        Double d1 = new Double(ind_count);
	        Double d2 = new Double(col_count-2);
	        Double per = (d1/d2)*100;
	        if (per < 75) 
		{
	        		String final_per = String.valueOf(per);
			if ( final_per.length() > 5 ) {
			final_per = final_per.substring(0,5);
			System.out.println(id + ". " + ind_name+" - "+final_per);}
			else { System.out.println(id + ". " + ind_name+" - "+final_per); }
	        		//System.out.println();
	        		//System.out.println("Total No.of classes attended: " + ind_count);
		}
	}
	}
	
	public void updateAttendance(Statement st0)throws Exception
	{
	Statement st = st0;
	System.out.println("-------------------");
	System.out.println("UPDATE ATTENDANCE(IOT)");
	System.out.println("-------------------\n");

	Scanner s=new Scanner(System.in);
	System.out.println("Please Enter the Roll No: ");
	String temp = s.nextLine();
	System.out.println("Please Enter the Date(Ddd_mm_yy): ");
	String strDXX_XX = s.nextLine();
	int temp2 = Integer.parseInt(temp);
	String sql2= "UPDATE IOT SET "+strDXX_XX+"=1 WHERE Roll="+temp2+";" ;
	st.executeUpdate(sql2);
	}
	
	public void updateDetails(Statement st0)throws Exception
	{
	Statement st = st0;
	System.out.println("-------------------");
	System.out.println("ADD A STUDENT DETAILS (IOT)");
	System.out.println("-------------------\n");
	
	Scanner s=new Scanner(System.in);
	System.out.println("Please Enter the Roll No: ");
	String temp = s.nextLine();
	System.out.println("Please Enter the Name: ");
	String str = s.nextLine();
	int temp2 = Integer.parseInt(temp);
	String sql2= "INSERT INTO IOT(Roll,Name) VALUES("+temp2+", '"+ str +"');";
	st.executeUpdate(sql2);
	}
	
	public void deleteDetails(Statement st0)throws Exception
	{
	Statement st = st0;
	System.out.println("-------------------");
	System.out.println("DELETE A STUDENT DETAILS (IOT)");
	System.out.println("-------------------\n");
	
	Scanner s=new Scanner(System.in);
	System.out.println("Please Enter the Roll No: ");
	String temp = s.nextLine();
	int temp2 = Integer.parseInt(temp);
	String sql2= "DELETE FROM IOT WHERE Roll="+temp2+";";
	st.executeUpdate(sql2);
	}
	
}

class TestJDBC {

	public static void main(String args[])throws Exception
	{
    	//1. Collect Database and Driver Info
	String driverClassName = "com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost/jdbc";
	String user="root";
	String pwd= "1234";//root
	
	//2. Load JDBC Driver / Register a Driver
	Class.forName(driverClassName).newInstance();

	//3. Open a Connection
	Connection con = DriverManager.getConnection(url,user,pwd);
	//System.out.println("con---->"+con+"\n");
	Statement st = con.createStatement();

	//Menu Options
	A objA = new A();
	String tempdate = objA.reqDate();
	System.out.println("\n---------- Welcome to IOT Attendance Software ----------");
	System.out.println("--------------- Today's Date: " + tempdate + " ----------------");
	//System.out.println("Class Teacher: Sourabh Jain\nClass:IOT Sem IV\nCourse:OOPS using Java\n");
	boolean valid = true;
	while(valid)
	{
	System.out.println("\n1. Take Attendance    2. Update Attendance\n3. Debar List         4. Check Percentage\n5. Exit");
	System.out.println("6. Update Details     7. Drop Datails\n");
	try { 
	Scanner s=new Scanner(System.in);
	System.out.println("Please Enter Your Choice:");
	int choice = s.nextInt();
	switch (choice)
	{
		case 1: objA.getAttendance(tempdate,st); break;
		case 2: objA.updateAttendance(st);break;
		case 3: objA.getDebarlist(st); break;
		case 4:  System.out.println("Enter the roll no: ");
		              int perchoice = s.nextInt();
		              objA.getPercentage(perchoice,st); break;
		case 5: valid=false; break;
		case 6: objA.updateDetails(st); break;
		case 7: objA.deleteDetails(st); break;
		default: System.out.println("Enter Valid Menu Option !!!");
	}
	}catch (InputMismatchException e)
	{
		System.out.println("ENTER A VALID NUMBER !!!");}

	}
	
	//5. close st and con : use finally block
	st.close();
	con.close();
	//System.out.println("---SQL executed successfully---");
	}
}