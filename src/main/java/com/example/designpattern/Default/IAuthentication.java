package com.example.designpattern.Default;

import java.lang.reflect.Field;
import com.example.designpattern.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public interface IAuthentication {
	DatabaseConnection conn = DatabaseConnection.getInstance();
	 String userstableName = "users";
	 String resetPasswordTableName = "ResetPassword";
	 String usernameColumnName="username";
	 String passwordColumnName = "password";
	 String primaryKeyName = "id";
	 String questionResetPasswordColumnName = "question";
	String answerResetPasswordColumnName = "answer";
	 String primaryKeyDatatype = "int";
	public void setResetPasswordTable(String resetPasswordTable);
	public void setTableName(String tableName);
	public void setPrimaryKeyDatatype(String datatype);
	public void setUsernameColumnName(String usernameColumnName);
	public void setPasswordColumnName(String passwordColumnName);
	public void setPrimaryKeyName(String primaryKeyName);
	public void createDefaultUserTableToDatabase(Class<? extends User> userClass) throws SQLException;
	public boolean columnExists( String tableName, String columnName) throws SQLException;
	public void createResetPasswordTable();
	public boolean checkUsernameExist (String username);
	public String getAccountId (String username);
	 public boolean checkSignIn(String username, String password);
	public boolean createAccount(String username, String password, String question, String answer);
	public String loadQuestionResetPassword(String username);
	public boolean checkResetPasswordAnswerCorrect(String username, String answer);
	public boolean resetPassword(String username, String password);
	public String SqlTypetoJavaType(String string);
}
