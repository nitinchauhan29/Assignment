package assign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class Assignment {
	WebDriver driver;
	
	//Login on the page 
	public void LogIn() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://sakshingp.github.io/assignment/login.html");
		driver.findElement(By.id("username")).sendKeys("test");
		driver.findElement(By.id("password")).sendKeys("test");
		driver.findElement(By.id("log-in")).click();
		
		if(driver.findElement(By.id("logged-user-name")).getText().equals("John Doe"))
           System.out.println("Log In Successful");
	}
	
	
	
	//function that will check array is sorted or not
	public boolean CheckSorted(ArrayList<Double> amt) {
		
		for(int i=0;i<amt.size()-1;i++)
			if(amt.get(i)>amt.get(i+1))
				return false;
		return true;
	}
	
	//Check for amount data
	public void amount() {
		//sort amount column
		driver.findElement(By.id("amount")).click();
		
		//List that will store the Float value of amount
		ArrayList<Double> amt = new ArrayList<>();
		
		//Extracting Amount data from table
		WebElement table= driver.findElement(By.id("transactionsTable"));
		List<WebElement> allrows = table.findElements(By.tagName("tr"));
		for(WebElement row : allrows)
		{
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(WebElement cell : cells ) {
				//Extracting data for amount column only
				if(cell.getAttribute("class").matches("text-right bolder nowrap")) {
					
					//converting Amount String into Double
					String number = cell.getText();
					String Splitstr[] = number.split("\\s+");
					double i = Double.parseDouble(Splitstr[1].replaceAll(",", ""));
					if(Splitstr[0].equals("-"))
						i = i*-1;
					amt.add(i);
				
				}
			}
		}
		
		//Checking the amount list is correctly sorted or not 
		if(CheckSorted(amt))
			System.out.println("Amount Data Is Sorted");
		else
			System.out.println("Amount Data Is Not Sorted");
		
	}
	
	public static void main(String[] args) {
		Assignment Obj = new Assignment();
		Obj.LogIn();
		Obj.amount();
	}
	

}
