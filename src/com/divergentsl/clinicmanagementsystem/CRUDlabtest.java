package com.divergentsl.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.divergentsl.clinicmanagementsystem.dao.LabtestDao;

public class CRUDlabtest {
	public static void labtestDao()throws Exception
	{

		  Scanner sc= new Scanner (System.in);
		  Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/clinicmanagement",
					"root","root");
			Statement st = connection.createStatement();
			PreparedStatement statement;
	
	System.out.println("Enter your choice");
	System.out.println("press 1 : to add lab test details");
	System.out.println("press 2 : to see all lab test list");
	System.out.println("press 3 : to update test");
	int lchoice=sc.nextInt() ;
		
		switch(lchoice)
		{
		case 1: //labtest
				System.out.println("enter labtest name");
				String name = sc.next();
				System.out.println("enter labtest id");
				String drug_id= sc.next();
				System.out.println("enter test price");
				String drug_price=sc.next();
						
				LabtestDao labtestDao= new LabtestDao(new DatabaseManager());
				int rowinsert= labtestDao.insertLabtest(name, drug_id, drug_price);
						
				
				if(rowinsert>0)
				{
					System.out.println("new tabtest information added");
				}
				break;
		
		
		case 2: //retrive labtest
				labtestDao= new LabtestDao(new DatabaseManager());
				ResultSet rsretrive =labtestDao.showLabtest();
				while(rsretrive.next())
				{
					System.out.println(rsretrive.getString(1)+" "+rsretrive.getString(2)+" "+rsretrive.getString(3));
				}
				break;
				
		
		case 3: //update price
				System.out.println("enter labtest Id to update details");
				String id=sc.next();
				System.out.println("enter choice");
				System.out.println("press 1 : to update name");
				System.out.println("press 2 : to update labtest price");
				
				int n=sc.nextInt();
				String sqlupdate=" ";
					switch(n)
					{
					case 1:
						
						System.out.println("enter updated name");
						String labname=sc.next();
						labtestDao= new LabtestDao(new DatabaseManager());
						int rowupdatename=labtestDao.updateName(labname,id);
						if(rowupdatename>0) System.out.print("information updated");
						break;
					
					case 2:
						System.out.println("enter updated drug price");
						String price=sc.next();
						labtestDao= new LabtestDao(new DatabaseManager());
						int rowupdatespec=labtestDao.updatePrice(price, id);
						if(rowupdatespec>0) System.out.print("information updated");
						break;
					
					default:
						System.out.println("Enter Valid choice");
					}	
			}
	}
}