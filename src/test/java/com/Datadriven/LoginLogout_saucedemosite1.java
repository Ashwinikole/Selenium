package com.Datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginLogout_saucedemosite1 {

    public static void main(String[] args) throws InterruptedException {
        
        Excelsheet_getdata edata = new Excelsheet_getdata(); // Data from Excel
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://www.saucedemo.com/");
        
        for (int i = 1; i <= 6; i++) {
            // Fetch username and password from Excel
            String username = edata.getadatafromSheet("Sheet1", i, 0);
            String password = edata.getadatafromSheet("Sheet1", i, 1);

            // Enter credentials
            driver.findElement(By.id("user-name")).clear();
            driver.findElement(By.id("user-name")).sendKeys(username);

            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);

            // Click login
            driver.findElement(By.name("login-button")).click();
            Thread.sleep(3000);

            // Check if login is successful
            if (driver.getCurrentUrl().contains("inventory.html")) { // Successful login
                System.out.println("Login successful for: " + username);

                // Click menu and logout
                driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
                Thread.sleep(2000);
            } else { // Login failed
                System.out.println("Login failed for: " + username);
                
                // Refresh page to retry next credentials
                driver.navigate().refresh();
                Thread.sleep(2000);
            }
        }
        
        driver.quit(); // Close browser after test execution
    }
}