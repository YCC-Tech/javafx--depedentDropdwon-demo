package application;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestUtil {
	
	private Connection connection;
	//private PreparedStatement pStmt;
	private Statement stmt;
	private ResultSet rs;
	
	//get university list from db to university combo box
	public ObservableList<String> getUniList(String sql) throws SQLException{
		ObservableList<String> uniList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			uniList.add(rs.getString("uniName")
					);
		}
		System.out.println(uniList);
		return uniList;
		
	}
	//get academic year list from db to year combo box filtered by univeristy
	public ObservableList<String> getYearList(String sql) throws SQLException{
		ObservableList<String> yearList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			yearList.add(rs.getString("yearName")
					);
		}
		System.out.println(yearList);
		return yearList;
		
	}
	//get major list from db to major combo box filtered by university and academic year
	public ObservableList<String> getMajorList(String sql) throws SQLException{
		ObservableList<String> majorList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			majorList.add(rs.getString("majorName")
					);
		}
		System.out.println(majorList);
		return majorList;
		
	}
	
	//get university id from db according to university name
	public int getUniId(String uniName) throws SQLException {
		int uniId= 0;
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery("select uniId from university where uniName='"+uniName+"';");
		while(rs.next()) {
			uniId=rs.getInt("uniId");
			
		}
		return uniId;
	}
	
	//get region list from db to region combo box used in Address
	public ObservableList<String> getRegionNameList(String sql) throws SQLException{
		ObservableList<String> regionNameList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			regionNameList.add(rs.getString("regionName")
					);
		}
		System.out.println(regionNameList);
		return regionNameList;
		
	}
	//get township full list from db to township combo box used in address
	public ObservableList<String> getTownshipLongList(String sql) throws SQLException{
		ObservableList<String> townshipLongList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			townshipLongList.add(rs.getString("townshipName")
					);
		}
		System.out.println(townshipLongList);
		return townshipLongList;
		
	}
	//get region code list from db to region code combo box used in nrc 
	public ObservableList<String> getRegionCodeList(String sql) throws SQLException{
		ObservableList<String> regionCodeList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			regionCodeList.add(rs.getString("regionId")
					);
		}
		System.out.println(regionCodeList);
		return regionCodeList;
		
	}
	//get township short name list from db to township combo box used in nrc
	public ObservableList<String> getTownshipShortList(String sql) throws SQLException{
		ObservableList<String> townshipShortList = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			townshipShortList.add(rs.getString("townshipShortName")
					);
		}
		System.out.println(townshipShortList);
		return townshipShortList;
		
	}
	//get region Id from db according to region's name
	public int getRegionId(String regionName) throws SQLException {
		int regionId= 0;
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery("select regionId from region where regionName='"+regionName+"';");
		while(rs.next()) {
			regionId=rs.getInt("regionId");
			
		}
		return regionId;
	}
	
}
