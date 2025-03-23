package com.Datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginLogout_saucedemosite {

	public static void main(String[] args) throws InterruptedException {
		
		Excelsheet_getdata edata = new Excelsheet_getdata();
				
				
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.saucedemo.com/");
				
		for(int i=1; i<=6; i++)
		{
		String username = edata.getadatafromSheet("Sheet1",i,0);
		driver.findElement(By.id("user-name")).sendKeys(username);
		
		String password = edata.getadatafromSheet("Sheet1",i,1);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.name("login-button")).click();
		
		Thread.sleep(3000);
		
		//Check login successful or nor by title of next page
		
		String actualtitle = driver.getCurrentUrl();
		String expectedtitle = "https://www.saucedemo.com/inventory.html";
		if(actualtitle.equals(expectedtitle))
		{
		System.out.println("The login is successful for Username :" +username);	
		
		//First click on open menu link then click on logout link
		driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
		Thread.sleep(2000);
		
		}
		else
		{
			System.out.println("Login is failed for username :" +username);
		}
		driver.navigate().refresh(); //refresh page and enter another credentials
		}
		driver.close();
		}
 }
