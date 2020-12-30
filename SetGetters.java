import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

public class SetGetters {
	
	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG09";
	private String config= "?useUnicode=true&characterEncoding=utf8";
	private String url = server + database + config;
	private String username = "TG09";
	private String password = "u73e9x";
	private Connection conn = null;
	
	private int highScore ;
	private int money ;
	private int maskAmount ;
	private int alcoholAmount ;
	private int clothesAmount ;
	private int tzuyu ;
	private int clock ;
	private int trump ;
	private String userAccount ;
	private String userPassword ;

	public SetGetters(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public SetGetters() {}
	
	public int getMaskAmount() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Mask_Amount FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.maskAmount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return maskAmount; 
	}
	
	public int getAlcoholAmount() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Alcohol_Amount FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.alcoholAmount = rs.getInt(1);
			}	  
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return alcoholAmount; 
	}
	
	public int getClothesAmount() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Clothes_Amount FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.clothesAmount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();	  
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return clothesAmount; 
	}

	public void setMasks(int amount) throws SQLException{
		Statement stat = null;
		maskAmount = amount;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET Mask_Amount = '"+maskAmount+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);
		} catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		}finally{
		    //finally block used to close resources
		    try{
		    	if(stat!=null)
		    		stat.close();
		    }catch(SQLException se){}// do nothing
		    try{
		        if(conn!=null)
		        	conn.close();
		    }catch(SQLException se){
		    	se.printStackTrace();
		    }//end finally try
		}
	}
	public void setAlcohol(int amount)throws SQLException{
		Statement stat = null;
		alcoholAmount = amount;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET Alcohol_Amount = '"+alcoholAmount+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);			
		} catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		}finally{
		    //finally block used to close resources
		    try{
		    	if(stat!=null) {
		    		stat.close();
		    	}
		    }catch(SQLException se){}// do nothing
		    try{
		        if(conn!=null) {
		            conn.close();
		        }
		    }catch(SQLException se){
		    	se.printStackTrace();
		    }//end finally try
		}
	}
	
	public void setClothes(int amount) throws SQLException{
		Statement stat = null;
		clothesAmount = amount;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET Clothes_Amount = '"+clothesAmount+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);	
		} catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		}finally{
		    //finally block used to close resources
		    try{
		    	if(stat!=null)
		    		stat.close();
		    }catch(SQLException se){}// do nothing
		    try{
		        if(conn!=null)
		            conn.close();
		    }catch(SQLException se){
		    	se.printStackTrace();
		    }//end finally try
		}
	}
	
	public void buyTrump() throws SQLException{
		Statement stat = null;
		trump = 1;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET Trump = '"+trump+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);	
		} catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
				if(stat!=null) {
					conn.close();
				}
		    }catch(SQLException se){}
		    try{
		    	if(conn!=null) {
		            conn.close();
		    	}
		    }catch(SQLException se){
		         se.printStackTrace();
		    }
		}
	}
	
	public int getTrump() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Trump FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.trump = rs.getInt(1);
			}
		}catch(Exception e) {
			
		}finally {
			if (conn != null) {
			     conn.close();
			}
		}
		return trump; 
	}
	
	public void buyTzuyu() throws SQLException{
		Statement stat = null;
		tzuyu = 1;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET Tzuyu = '"+tzuyu+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
				if(stat!=null) {
		            conn.close();
				}
		    }catch(SQLException se){}
		    try{
		        if(conn!=null) {
		            conn.close();
		        }
		    }catch(SQLException se){
		        se.printStackTrace();
		    }
		}
	}
	public int getTzuyu() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Tzuyu FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.tzuyu = rs.getInt(1);
			}
		}catch(Exception e) {
				  
		}finally {
			 if (conn != null) {
			     conn.close();
			 }
		}
		return tzuyu; 
	}
	
	public void buyClock() throws SQLException{
		Statement stat = null;
		clock = 1;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET clock = '"+clock+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);
		} catch(Exception e){
		    e.printStackTrace();
		} finally{
			try{
				if(stat!=null) {
		            conn.close();
				}
		    }catch(SQLException se){}
		    try{
		        if(conn!=null) {
		            conn.close();
		        }
		    }catch(SQLException se){
		        se.printStackTrace();
		    }
		}
	}
	public int getClock() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Clock FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.clock = rs.getInt(1);
			}
		}catch(Exception e) {
			
		}finally {
			if (conn != null) {
			    conn.close();
			}
		}
		return clock; 
	}	
	
	public void setMoney(int amount) throws SQLException{
		Statement stat = null;
		money = amount;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET money = '"+money+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);
			
		}catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		}finally{
		    //finally block used to close resources
		    try{
		    	if(stat!=null) {
		    		stat.close();
		    	}
		    }catch(SQLException se){}
		    try{
		        if(conn!=null) {
		            conn.close();
		        }
		    }catch(SQLException se){}
		}
	}
	
	public int getMoney() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT Money FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			
			while (rs.next()){
				this.money = rs.getInt(1);
			}
			  
		}catch(Exception e) {}
			finally {
				if (conn != null) {
					conn.close();
				}
			}
		return money; 
	}
	
	public void setHighScore(int score) throws SQLException{
		Statement stat = null;
		this.highScore = score;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE Watch_Out_Virus SET High_Score = '"+highScore+"'  WHERE User_Account = '"+userAccount+"' ";
			stat.executeUpdate(query);
		} catch(Exception e){
		    e.printStackTrace();
		}finally{
		    try{
		    	if(stat!=null) {
		    		stat.close();
		    	}
		    }catch(SQLException se){}
		    try{
		        if(conn!=null) {
		            conn.close();
		        }
		    }catch(SQLException se){
		        se.printStackTrace();
		    }
		}
	}
	public int getHighScore() throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT High_Score FROM Watch_Out_Virus WHERE User_Account = '"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			rs=ps.executeQuery();
			if (rs.next()){
				this.highScore = rs.getInt(1);
			}
		}catch(Exception e) {
				  
		}finally {
			if (conn != null) {
			    conn.close();
			}
		}
		return highScore; 
	}
	
	public String getUserPassWord(String account) throws SQLException{
		userAccount = account;
		ResultSet result = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT * FROM Watch_Out_Virus WHERE User_Account='"+userAccount+"'";
			ps=conn.prepareStatement(query); 
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				userPassword = rs.getString("User_Passcode");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
		return userPassword; 
	}
	
	public String getAccount() {
		return this.userAccount;
	}
	public void setAccount() throws Exception {
		FileReader fr = new FileReader("account.txt");
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			
			this.userAccount = br.readLine();
		}
		fr.close();
	}
	public void register(String setAccount, String setPassword) throws SQLException{
		Statement stat = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "INSERT INTO Watch_Out_Virus VALUES ('"+setAccount+"', '"+setPassword+"', 0, 0, 0, 0, 0, 0, 0, 0)";
			stat.executeUpdate(query);			
		}catch(Exception e){
		    //Handle errors for Class.forName
			e.printStackTrace();
		}finally{
		    //finally block used to close resources
		    try{
		    	if(stat!=null) {
		    		stat.close();
		    	}
		    }catch(SQLException se){}// do nothing
		    try{
		    	if(conn!=null) {
		            conn.close();
		    	}
		    }catch(SQLException se){}//end finally try
		}
	}
	
	




}
