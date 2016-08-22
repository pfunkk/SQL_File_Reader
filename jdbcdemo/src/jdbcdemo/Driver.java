package jdbcdemo;

import java.sql.*;
import java.util.Scanner;
import java.lang.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Driver {

	public static void main(String[] args) {
		String url ="jdbc:mysql://localhost:3306/demo?useSSL=false";
		String usr = "root";
		String password ="Speaker1$";
	    String s = new String();
	    StringBuffer sb = new StringBuffer();
	    Scanner in = new Scanner(System.in);
	    String query =in.nextLine();
	    in.close();
		
		try{
			Connection myConn = DriverManager.getConnection(url,usr,password);
			Statement myStmt = myConn.createStatement();
			
			//feed file reader full path of document
			FileReader fr = new FileReader(new File("/Users/piankhy/Documents/myWorkspace/jdbcdemo/tables.sql"));
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
 
 
            for(int i = 0; i<inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals("")){
                    myStmt.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
           ResultSet myRs = myStmt.executeQuery(query);
		   myStmt.close();
		}
		catch(Exception exc){
			exc.printStackTrace();
			
		}

	}

}
