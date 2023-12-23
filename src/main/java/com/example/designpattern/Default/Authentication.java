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

        // Add fields specific to the given userClass
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
        	if(field.getName() == "username" ||field.getName() == "password") {
        		continue;
        	}
            createTableStatement.append(field.getName()).append(" ")
                    .append(javaTypeToSqlType(field.getType())).append(",");
        }

        createTableStatement.deleteCharAt(createTableStatement.length() - 1);
        createTableStatement.append(");");
        
        //create foreign table
        createTableStatement.append("CREATE TABLE ResetPasswordTable (id INT  PRIMARY KEY, question VARCHAR(255), answer VARCHAR(255));\r\n"
        		+ "ALTER TABLE ResetPasswordTable\r\n"
        		+ "ADD CONSTRAINT FK_RPIUsers\r\n"
        		+ "FOREIGN KEY (id) REFERENCES users(id);");
        
        System.out.println(createTableStatement);

//        stmt.execute(createTableStatement.toString());
	}
	public void createResetPasswordTable(String UserTableName, String primarykeyName) {
		
		
	}
	public void createTableToDatabaseWithTableName(String tableName,Class<? extends User> userClass) throws SQLException {
		this.tableName = tableName;

		Statement  stmt = null;
		try {
			 stmt = this.conn.connection.createStatement();
			 StringBuilder createTableStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
				createTableStatement.append(tableName).append(" ( ");


		        // Add common fields
		        createTableStatement.append("id INT AUTO_INCREMENT PRIMARY KEY,");
		        createTableStatement.append("username VARCHAR(255) NOT NULL,");
		        createTableStatement.append("password VARCHAR(255) NOT NULL,");
		        createTableStatement.append("active Boolean,");

		        // Add fields specific to the given userClass
		        Field[] fields = userClass.getDeclaredFields();
		        for (Field field : fields) {
		        	if(field.getName() == "username" ||field.getName() == "password") {
		        		continue;
		        	}
		        	System.out.println(field.getType().toString());
		            System.out.println(javaTypeToSqlType(field.getType()));

		            createTableStatement.append(field.getName()).append(" ")
		                    .append(javaTypeToSqlType(field.getType())).append(",");
		        }

		        // Remove the trailing comma
		        createTableStatement.deleteCharAt(createTableStatement.length() - 1);

		        // Close the CREATE TABLE statement
		        createTableStatement.append(");");

		        System.out.println(createTableStatement);
		        stmt.execute(createTableStatement.toString());
		} catch (SQLException e) {
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
	 public boolean createAccount(User user) {
	        if (userClass != null && userClass.isInstance(user)) {
	        	String sql = "INSERT INTO " + this.tableName + " (username, password) VALUES" + user.getSQLAddClause();
	   		 System.out.println(sql);
	        	Statement st;
	    		try {
	    			st = this.conn.connection.createStatement();
	    	        int rs = st.executeUpdate(sql);
	    	        System.out.println("createAccount: "+ rs);

	    	        // nhớ check lại
	    	        if(rs != 0) return true;
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
			return false;
	 }
	 public boolean checkSignIn(String username, String password) {
		 String sql = "SELECT * FROM "+this.tableName+ " Where username = '"+ username + "' and password = '"+ password +"' ";
		 System.out.println(sql);
			Statement st;
			try {
				st = this.conn.connection.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        if(rs.next()) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	 }
	public boolean createAccount(String username, String password) {
        	String sql = "INSERT INTO " + this.tableName + " (username, password) VALUES ('" + username +"','"+ password+"' )" ;
   		 System.out.println(sql);
        	Statement st;
    		try {
    			st = this.conn.connection.createStatement();
    	        int rs = st.executeUpdate(sql);
    	        // nhớ check lại
    	        if(rs != 0) return true;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	        
			return false;
	}
}
