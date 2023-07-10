package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.*;
import model.Login;
import model.Product;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Login login=new Login();
		LoginDAO logindao=new LoginDAO();
		Product product = new Product();
		productDAO productdao=new productDAO();
		
        int option;
        do 
        {
         	System.out.println("1.Admin");
         	System.out.println("2.Agent");
         	System.out.println("3.Exit");
         	System.out.println("---------------------------------------------------------");
         	option=Integer.parseInt(br.readLine());
         	
         	switch(option)
         	{
         	case 1:
         	System.out.println("----------------------------------------------------");
         	System.out.println("Enter username");
         	String username=br.readLine();
         	System.out.println("Enter password");
         	String password=br.readLine();
         	login.setUSERNAME(username);
         	login.setPASSWORD(password);
         	boolean result=logindao.validate(login);
         	if(result==true)
         	{
         		System.out.println("Login successful");
         		System.out.println("--------------------------------------------------");
         		do
         		{
         			System.out.println("1.Add Product");
                 	System.out.println("2.Display Inventory Details");
                 	System.out.println("3.Logout");
                 	System.out.println("---------------------------------------------------------");
                 	option=Integer.parseInt(br.readLine());	
					switch(option)
                 	{
                 	case 1:System.out.println("Enter Product name");
                 	String ProductName=br.readLine();
                 	System.out.println("Enter Product id");
                 	int ProductId=Integer.parseInt(br.readLine());
                 	System.out.println("Enter the min selling quantity");
                 	int minsell=Integer.parseInt(br.readLine());
                 	System.out.println("Enter the price of the product");
                 	int Price=Integer.parseInt(br.readLine());
                 	System.out.println("Enter the quantity");
                 	int quantity=Integer.parseInt(br.readLine());
					
                 	 product.setPRODUCTNAME(ProductName);
                 	 product.setPRODUCTID(ProductId);
                 	 product.setMINSELL(minsell);
                 	 product.setQUANTITY(quantity);
                 	 product.setPRICE(Price);
				     productdao.addProduct(product);
				     System.out.println("---------------------------------------------------");
				     break;
                 	case 2:productdao.display();break;
                 	case 3:break;
                 	}
         		}
         		while(option!=3);
         	}
         	else
         	{
         		System.out.println("username & password is incorrect");
         	}
         	break;
         	case 2:System.out.println("----------------------------------------------------");
         	System.out.println("Enter username");
         	String agentusername=br.readLine();
         	System.out.println("Enter password");
         	String agentpassword=br.readLine();
         	login.setUSERNAME(agentusername);
         	login.setPASSWORD(agentpassword);
            result=logindao.validate(login);
            if(result== true)
            {
            	System.out.println("Login successful");
         		System.out.println("--------------------------------------------------");
         		do
         		{
         			System.out.println("1.Buy/Sell");
                 	System.out.println("2.Show History");
                 	System.out.println("3.Logout");
                 	System.out.println("---------------------------------------------------------");
                 	option=Integer.parseInt(br.readLine());	
                 	switch(option)
                 	{
                 	case 1:System.out.println("Buy/Sell");
                 	String agent=br.readLine();
                 	if(agent.equalsIgnoreCase("buy"))
                 	{
                 		System.out.println("Enter Product name");
                     	String ProductName=br.readLine();
                     	System.out.println("Enter Product id");
                     	int ProductId=Integer.parseInt(br.readLine());
                     	System.out.println("Enter the min selling quantity");
                     	int minsell=Integer.parseInt(br.readLine());
                     	System.out.println("Enter the price of the product");
                     	int Price=Integer.parseInt(br.readLine());
                     	System.out.println("Enter the quantity");
                     	int quantity=Integer.parseInt(br.readLine());
    					
                     	 product.setPRODUCTNAME(ProductName);
                     	 product.setPRODUCTID(ProductId);
                     	 product.setMINSELL(minsell);
                     	 product.setQUANTITY(quantity);
                     	 product.setPRICE(Price);
    				     productdao.addProduct(product);
    				     System.out.println("---------------------------------------------------");
                 	}
                 	
                 	else if(agent.equalsIgnoreCase("sell"))
                 	{
                 		System.out.println("Enter Product id");
                     	int ProductId=Integer.parseInt(br.readLine());
                     	System.out.println("Enter the quantity");
                     	int quantity=Integer.parseInt(br.readLine());
                     	if(productdao.quantityAvailable(ProductId, quantity))
                     	{
                     		int total=productdao.totalcost(ProductId,quantity);
                     		 System.out.println("---------------------------------------------------");
                     		 System.out.println("Total cost is "+total);
                     		 System.out.println("---------------------------------------------------");
                     		 System.out.println("Confirm Booking(Yes/No)");
                     		 //String booking=br.readLine();
                     		 System.out.println("---------------------------------------------------");
                     	}
                     		
    					
                     		
                 	}
                 	}
         		}while(option!=3);
            }
         	
        	else
         	{
         		System.out.println("username & password is incorrect");
         }
           break;
         	//case 3:System.exit(0);
        
    }
        
        } while(option!=3);
	

	
		
	}

}
