package com.Xing.Access;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelRead;

public class LoginXing {
	
WebDriver driver;
	
@Test(dataProvider="Datadriven")
public void xing(String username, String password) throws InterruptedException {

	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.get("https://login.xing.com/");
	driver.manage().window().maximize();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='consent-accept-button']/div/span")).click();
	driver.findElement(By.name("username")).sendKeys(username);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//*[@id=\"javascript-content\"]/div[2]/form/button/div")).click();
	Thread.sleep(5000);

	Assert.assertTrue(driver.getTitle().matches("XING"), "Invalid credentials");
	System.out.println("Login successful");
	driver.findElement(By.xpath("//*[@id=\"personalBarProfilePicture\"]")).click();
	driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div/div/div[1]/header/div/div[3]/div/div/a[7]")).click();
	System.out.println("Logout successful");
	driver.quit();
}

@DataProvider(name="Datadriven")
public Object[][] datadrivenexcel(){
	ExcelRead getcreds = new ExcelRead("D:\\Eclipse-workspace\\Access\\src\\main\\java\\testData\\Credentials.xlsx");
	int rows = getcreds.getRowCount(0);
	Object[][]login_details = new Object[rows][2];

	for(int i=0;i<rows;i++)
	{
		login_details[i][0] = getcreds.getDataExcel(0, i, 0);
		login_details[i][1] = getcreds.getDataExcel(0, i, 1);
	}
return login_details;
}
}
