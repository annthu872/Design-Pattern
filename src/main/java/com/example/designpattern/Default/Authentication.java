package com.example.designpattern.Default;

import java.lang.reflect.Field;
import com.example.designpattern.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class Authentication {
	private static Authentication instance;
	DatabaseConnection conn = DatabaseConnection.getInstance();
    public static synchronized Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }
    public void printInfor() {
    	System.out.println("userstableName "+userstableName);
    	System.out.println("resetPasswordTableName "+resetPasswordTableName);
    	System.out.println("usernameColumnName "+usernameColumnName);
    	System.out.println("passwordColumnName "+passwordColumnName);
    	System.out.println("primaryKeyName "+primaryKeyName);
    	System.out.println("primaryKeyDatatype "+primaryKeyDatatype);    	
    }
	public void setResetPasswordTable(String resetPasswordTable) {
		this.resetPasswordTableName = resetPasswordTable;
	}
	private String userstableName = "users";

	private String resetPasswordTableName = "ResetPassword";
	private String usernameColumnName="username";
	private String passwordColumnName = "password";
//	private String activeColummnName = "active";
	private String primaryKeyName = "id";
	private String questionResetPasswordColumnName = "question";
	private String answerResetPasswordColumnName = "answer";
	private String primaryKeyDatatype = "int";
	
	public void setTableName(String tableName) {
		this.userstableName = tableName;
	}
	public void setPrimaryKeyDatatype(String datatype) {
		this.primaryKeyDatatype = datatype;
	}
	public void setUsernameColumnName(String usernameColumnName) {
		this.usernameColumnName = usernameColumnName;
	}
	public void setPasswordColumnName(String passwordColumnName) {
		this.passwordColumnName = passwordColumnName;
	}
//	public void setActiveColummnName(String activeColummnName) {
//		this.activeColummnName = activeColummnName;
//	}
	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}
//	public void setQuestionResetPasswordColumnName(String questionResetPasswordColumnName) {
//		this.questionResetPasswordColumnName = questionResetPasswordColumnName;
//	}
//	public void setAnswerResetPasswordColumnName(String answerResetPasswordColumnName) {
//		this.answerResetPasswordColumnName = answerResetPasswordColumnName;
//	}
//	
	
    public boolean checkifUserTableNameExisted() {
    	List<String> tableList = this.conn.getTableList(SharedVariableHolder.database);
    		
    	for( String str : tableList) {
    		if(this.userstableName.equalsIgnoreCase(str)) {
        		return true;
        	}
    	}
    	
    	return false;
    }
    public boolean checkifUserTableNameExisted(String name) {
    	List<String> tableList = this.conn.getTableList(SharedVariableHolder.database);
    		
    	for( String str : tableList) {
    		if(name.equalsIgnoreCase(str)) {
        		return true;
        	}
    	}
    	
    	return false;
    }
    public boolean checkifResetPassTableNameExisted() {
    	List<String> tableList = this.conn.getTableList(SharedVariableHolder.database);
    		System.out.println("this.resetPasswordTableName"+ this.resetPasswordTableName);
    	for( String str : tableList) {
    		if(this.resetPasswordTableName.equalsIgnoreCase(str)) {
        		return true;
        	}
    	}
    	return false;
    }
    public boolean checkifResetPassTableNameExisted(String name) {
    	List<String> tableList = this.conn.getTableList(SharedVariableHolder.database);
    		
    	for( String str : tableList) {
    		if(name.equalsIgnoreCase(str)) {
        		return true;
        	}
    	}
    	return false;
    }
	public void createDefaultUserTableToDatabase(Class<? extends User> userClass) throws SQLException {
		
		Statement  stmt = this.conn.connection.createStatement();
		StringBuilder createTableStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS "+this.userstableName+ " (");

        // Add common fields
        createTableStatement.append("id INT AUTO_INCREMENT PRIMARY KEY,");
        createTableStatement.append("username VARCHAR(255) NOT NULL,");
        createTableStatement.append("password VARCHAR(255) NOT NULL,");
//        createTableStatement.append("active BIT,");

        // Add fields specific to the given userClass
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
        	if(field.getName().equals("id") || field.getName().equals("username") ||field.getName().equals("password") ||field.getName().equals("active" ) ) {
        		continue;
        	}
            createTableStatement.append(field.getName()).append(" ")
                    .append(javaTypeToSqlType(field.getType())).append(",");
        }

        createTableStatement.deleteCharAt(createTableStatement.length() - 1);
        createTableStatement.append(");");
        
        System.out.println(createTableStatement);
        stmt.execute(createTableStatement.toString());
        this.createResetPasswordTable();
        System.out.println(this.userstableName);
        System.out.println(this.resetPasswordTableName);

	}
	
//	public boolean addActiveFieldtoTable() {
//		try {
//			if (!columnExists(this.userstableName, this.activeColummnName)) {
//			    // Column does not exist, add it
//				String sql = "ALTER TABLE " + this.userstableName + " ADD COLUMN " + this.activeColummnName + " BIT" ;
//			    try (Statement statement = this.conn.connection.createStatement()) {
//			        statement.executeUpdate(sql);
//			        System.out.println("Column added successfully");
//			        return true;
//			    }
//			} else {
//			    System.out.println("Column already exists");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public boolean columnExists( String tableName, String columnName) throws SQLException {
        DatabaseMetaData metadata = this.conn.connection.getMetaData();
        try (ResultSet resultSet = metadata.getColumns(null, null, tableName, columnName)) {
            return resultSet.next(); // true if the column exists, false otherwise
        }
    }
	
	public void createResetPasswordTable() {
		StringBuilder createTableStatement = new StringBuilder("");
		createTableStatement.append("CREATE TABLE IF NOT EXISTS "+this.resetPasswordTableName +" ("+ this.primaryKeyName+" "+javaTypeToSqlType(this.primaryKeyDatatype) + " PRIMARY KEY, question VARCHAR(255), answer VARCHAR(255));\r\n");
	      
	        System.out.println(createTableStatement);
	        Statement stmt;
			try {
				stmt = this.conn.connection.createStatement();
		        stmt.execute(createTableStatement.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createTableStatement = new StringBuilder("ALTER TABLE "+this.resetPasswordTableName+ "\r\n"
		    		+ "ADD CONSTRAINT FK_"+this.userstableName+this.resetPasswordTableName+"\r\n"
		    		+ "FOREIGN KEY ("+this.primaryKeyName+") REFERENCES "+this.userstableName +"("+this.primaryKeyName+");");
			
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
            return javaType.getName();
        }
    }
	public static String javaTypeToSqlType(String javaType) {
        switch (javaType) {
        case "String":
            return "VARCHAR(255)";
        case "int":
            return "INT";
        case "boolean":
            return "BIT";
        case "float":
            return "FLOAT";
        case "double":
            return "DOUBLE";
        default:
            return javaType;
        }
    }
    public static String SqlTypetoJavaType(String sqlType) {
    	if (sqlType.equalsIgnoreCase("INT") || sqlType.equalsIgnoreCase("INTEGER")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("VARCHAR")) {
            return  "String";
//        } else if (sqlType.equalsIgnoreCase("BIGINT")) {
//            return "Long";
//        } else if (sqlType.equalsIgnoreCase("BOOLEAN")) {
//            return "Boolean";
//        } else if (sqlType.equalsIgnoreCase("BIT")) {
//            return "Boolean"; 
//        } else if (sqlType.equalsIgnoreCase("FLOAT")) {
//            return "Float"; 
        } else {
            return "String";
        }
    }
	public boolean checkUsernameExist (String username) {
		String sql = "SELECT * FROM "+this.userstableName+ " Where "+this.usernameColumnName+" = '"+ username+"'";
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
	public String getAccountId (String username) {
		String sql = "SELECT * FROM "+this.userstableName+ " Where "+this.usernameColumnName+" = '"+ username+"'";
		 System.out.println(sql);
		Statement st;
		try {
			st = this.conn.connection.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        System.out.println("checkSignUpAccountExist: "+ rs);
	        if(rs.next()) {
	        	
	        	Object columnValue = rs.getObject(this.primaryKeyName);
                String columnValueAsString = columnValue.toString();
                if(this.primaryKeyDatatype.equalsIgnoreCase("String") ) {
                	return "'"+columnValueAsString+"'"; 
                }
	        	return columnValueAsString;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 public boolean checkSignIn(String username, String password) {
		 String sql = "SELECT * FROM "+this.userstableName+ " Where "+this.usernameColumnName+" = '"+ username + "' and "+this.passwordColumnName+" = '"+ password +"' ";
		 System.out.println(sql);
			Statement st;
			try {
				st = this.conn.connection.createStatement();
		        ResultSet rs = st.executeQuery(sql);
		        if(rs.next()) {
//					setActiveforSignInAccount(username);
		        	return true;
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
	 }
//	 public boolean setActiveforSignInAccount(String username) {
//		 
//		 String sql = "Update "+this.userstableName+ " set "+this.activeColummnName+" = 1  Where id = "+ getAccountId(username)+ ";";
//		 System.out.println(sql);
//			Statement st;
//			try {
//				st = this.conn.connection.createStatement();
//		        int rs = st.executeUpdate(sql);
//		        if(rs != 0 ) return true;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return false;
//	 }
	public boolean createAccount(String username, String password, String question, String answer) {
        	String sql = "INSERT INTO " + this.userstableName + " ("+this.usernameColumnName+","+this.passwordColumnName+") VALUES ('" + username +"','"+ password+"' )" ;
   		 	System.out.println(sql);
        	Statement st;
    		try {
    			st = this.conn.connection.createStatement();
    	        int rs = st.executeUpdate(sql);
    	        if(rs != 0) 
    	        {	
    	        	
    	        	String id = getAccountId(username);
    	        	if(id != null) {
        	        	sql = "INSERT INTO "+ this.resetPasswordTableName+ " ("+this.primaryKeyName+" , "+this.questionResetPasswordColumnName+" ,"+this.answerResetPasswordColumnName+" ) VALUES ( "+id+" , '" + question +"','"+ answer+"' )" ;
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
	public String loadQuestionResetPassword(String username) {
		String id = this.getAccountId(username);
		String sql = "SELECT * FROM "+  this.resetPasswordTableName+" Where "+this.primaryKeyName +" ="+ id +";";
		 System.out.println(sql);
		Statement st;
		try {
			st = this.conn.connection.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        System.out.println("checkSignUpAccountExist: "+ rs);
	        if(rs.next()) {
	        	return rs.getString("question");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean checkResetPasswordAnswerCorrect(String username, String answer) {
		String id = this.getAccountId(username);
		String sql = "SELECT * FROM ResetPasswordTable Where id ="+ id +";";
		 System.out.println(sql);
		Statement st;
		try {
			st = this.conn.connection.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        System.out.println("checkResetPasswordAnswerCorrect: "+ rs);
	        if(rs.next()) {
	        	if(rs.getString("answer").equals( answer)) {
	        		return true;
	        	}
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean resetPassword(String username, String password) {
		String sql = "Update "+this.userstableName+ " set password = '"+ password +"' Where id = "+ getAccountId(username)+ ";";
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
}
