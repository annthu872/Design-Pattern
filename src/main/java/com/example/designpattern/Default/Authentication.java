package com.example.designpattern.Default;

import java.lang.reflect.Field;
import com.example.designpattern.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Authentication {
	private String tableName = "users";
	private Class<? extends User> userClass;
	private static Authentication instance;
	DatabaseConnection conn = DatabaseConnection.getInstance();
    public static synchronized Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }
    private Authentication() {}
    
	public void createTableToDatabase(Class<? extends User> userClass) throws SQLException {
		this.tableName = tableName;
		
		Statement  stmt = this.conn.connection.createStatement();
		StringBuilder createTableStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS users (");

        // Add common fields
        createTableStatement.append("id INT AUTO_INCREMENT PRIMARY KEY,");
        createTableStatement.append("username VARCHAR(255) NOT NULL,");
        createTableStatement.append("password VARCHAR(255) NOT NULL,");
        createTableStatement.append("active BIT,");

        // Add fields specific to the given userClass
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
        	if(field.getName() == "id" || field.getName() == "username" ||field.getName() == "password" ||field.getName() == "active" ) {
        		continue;
        	}
            createTableStatement.append(field.getName()).append(" ")
                    .append(javaTypeToSqlType(field.getType())).append(",");
        }

        createTableStatement.deleteCharAt(createTableStatement.length() - 1);
        createTableStatement.append(");");
        
        System.out.println(createTableStatement);
        stmt.execute(createTableStatement.toString());
        createResetPasswordTable("users");

	}
	public void createResetPasswordTable(String UserTableName) {
		this.tableName = tableName;
		StringBuilder createTableStatement = new StringBuilder("");
		createTableStatement.append("CREATE TABLE ResetPasswordTable (id INT PRIMARY KEY, question VARCHAR(255), answer VARCHAR(255));\r\n");
	        		
	        
	        System.out.println(createTableStatement);
	        Statement stmt;
			try {
				stmt = this.conn.connection.createStatement();
		        stmt.execute(createTableStatement.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createTableStatement = new StringBuilder("ALTER TABLE ResetPasswordTable\r\n"
		    		+ "ADD CONSTRAINT FK_RPIUsers\r\n"
		    		+ "FOREIGN KEY (id) REFERENCES users(id);");
			
			System.out.println(createTableStatement);
			try {
				stmt = this.conn.connection.createStatement();
		        stmt.execute(createTableStatement.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public static String javaTypeToSqlType(Class<?> javaType) {
        switch (javaType.getName()) {
        case "java.lang.String":
            return "VARCHAR(255)";
        case "int":
            return "INT";
        case "java.lang.Boolean":
            return "BIT";
        case "float":
            return "FLOAT";
        case "double":
            return "DOUBLE";
        default:
            return "";
        }
    }
	public boolean checkSignUpAccountExist (String username, String password) {
		String sql = "SELECT * FROM "+this.tableName+ " Where username = '"+ username+"'";
		 System.out.println(sql);
		Statement st;
		try {
			st = this.conn.connection.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        System.out.println("checkSignUpAccountExist: "+ rs);
	        if(rs.next()) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public int getAccountId (String username, String password) {
		String sql = "SELECT * FROM "+this.tableName+ " Where username = '"+ username+"'";
		 System.out.println(sql);
		Statement st;
		try {
			st = this.conn.connection.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        System.out.println("checkSignUpAccountExist: "+ rs);
	        if(rs.next()) {
	        	return rs.getInt("id");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	 public boolean checkSignIn(String username, String password) {
		 String sql = "SELECT * FROM "+this.tableName+ " Where username = '"+ username + "' and password = '"+ password +"' ";
		 System.out.println(sql);
			Statement st;
			try {
				st = this.conn.connection.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        if(rs.next()) {
					setActiveforSignInAccount(username, password);
		        	return true;
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
	 }
	 public boolean setActiveforSignInAccount(String username, String password) {
		 String sql = "Update "+this.tableName+ " set active = 1 Where id = "+ getAccountId(username,password)+ ";";
		 System.out.println(sql);
			Statement st;
			try {
				st = this.conn.connection.createStatement();
		        int rs = st.executeUpdate(sql);
		        if(rs != 0 ) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	 }
	public boolean createAccount(String username, String password, String question, String answer) {
        	String sql = "INSERT INTO " + this.tableName + " (username, password) VALUES ('" + username +"','"+ password+"' )" ;
   		 	System.out.println(sql);
        	Statement st;
    		try {
    			st = this.conn.connection.createStatement();
    	        int rs = st.executeUpdate(sql);
    	        if(rs != 0) 
    	        {	
    	        	
    	        	int id = getAccountId(username, password);
    	        	if(id != -1) {
        	        	sql = "INSERT INTO ResetPasswordTable (id , question, answer) VALUES ( "+id+" , '" + question +"','"+ answer+"' )" ;
        	        	System.out.println(sql);
        	        	st = this.conn.connection.createStatement();
            	        rs = st.executeUpdate(sql);
            	        if( rs != 0) return true;
    	        	}
    	        }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	        
			return false;
	}
}
