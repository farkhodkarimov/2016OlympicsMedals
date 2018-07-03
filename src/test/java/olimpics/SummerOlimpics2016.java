package olimpics;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SummerOlimpics2016 {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void sortTexst() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		
		List<WebElement> ls = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		for (int i = 0; i < ls.size()-1; i++) {
			int actual = Integer.parseInt(ls.get(i).getText());
			//System.out.println(actual + " " + (i+1));
			assertEquals(actual, i+1);
		}
		driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//th[.='NOC']")).click();
		
		List<String> lsCountry = new ArrayList<>();
		List<WebElement> ls2 = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		for (WebElement i : ls2) {
			lsCountry.add(i.getText());
		}
		
		for (int i = 0; i < ls2.size(); i++) {
			String actual = ls2.get(i).getText();
			assertEquals(actual, lsCountry.get(i));
		}
		List<WebElement> lsNotSorted = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		for (int i = 0; i < lsNotSorted.size(); i++) {
			int actual = Integer.parseInt(lsNotSorted.get(i).getText());
			assertNotEquals(actual, i+1);
		}
	}
	
	@Test
	public void theMost() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(goldMedals());
		System.out.println(silverMedals());
		System.out.println(bronzeMedals());
		System.out.println(totalMedals());
	}
	
	public String goldMedals() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> lsGold = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));
		
		int max = Integer.parseInt(lsGold.get(0).getText());
		int countryNum = 0;
		for(int i = 1; i < lsGold.size()-1; i++) {
			if (Integer.parseInt(lsGold.get(i).getText()) > max) {
				max = Integer.parseInt(lsGold.get(i).getText());
				countryNum = i;
			}
		}
		return lsCountries.get(countryNum).getText().trim();
	}
	
	public String silverMedals() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> lsSilver = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		
		int max = Integer.parseInt(lsSilver.get(0).getText());
		int countryNum = 0;
		for(int i = 1; i < lsSilver.size()-1; i++) {
			if (Integer.parseInt(lsSilver.get(i).getText()) > max) {
				max = Integer.parseInt(lsSilver.get(i).getText());
				countryNum = i;
			}
		}
		return lsCountries.get(countryNum).getText().trim();
	}
	public String bronzeMedals() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> bronzeMedals = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		
		int max = Integer.parseInt(bronzeMedals.get(0).getText());
		int countryNum = 0;
		for(int i = 1; i < bronzeMedals.size()-1; i++) {
			if (Integer.parseInt(bronzeMedals.get(i).getText()) > max) {
				max = Integer.parseInt(bronzeMedals.get(i).getText());
				countryNum = i;
			}
		}
		return lsCountries.get(countryNum).getText().trim();
	}
	public String totalMedals() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> totalMedals = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
		
		int max = Integer.parseInt(totalMedals.get(0).getText());
		int countryNum = 0;
		for(int i = 1; i < totalMedals.size()-1; i++) {
			if (Integer.parseInt(totalMedals.get(i).getText()) > max) {
				max = Integer.parseInt(totalMedals.get(i).getText());
				countryNum = i;
			}
		}
		return lsCountries.get(countryNum).getText().trim();
	}
	
	@Test
	public void countryByMedal() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		
		System.out.println(silver18());
	}
	public List<String> silver18() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> lsSilver = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		List<String> silver18 = new ArrayList<>();
		for(int i = 0; i < lsSilver.size(); i++) {
			if (Integer.parseInt(lsSilver.get(i).getText()) == 18) {
				silver18.add(lsCountries.get(i).getText().trim());
			}
		}
		return silver18;
	}
	
	@Test
	public void getIndex() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(rowCol("Japan"));
		// output 6 2
	}
	public String rowCol(String country) {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		String rowCol = "";
		for(int i = 0; i < lsCountries.size(); i++) {
			if (country.equalsIgnoreCase(lsCountries.get(i).getText().trim().substring(0, lsCountries.get(i).getText().trim().indexOf(" ")))) {
				rowCol = "" + (i+1) + " 2";
			}
		}
		return rowCol;
	}
	
	@Test
	public void getSum() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(bronze18());
		
	}
	public List<String> bronze18() {
		List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> lsBronze = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<String> bronze18 = new ArrayList<>();
		for(int i = 0; i < lsBronze.size(); i++) {
			int num1 = Integer.parseInt(lsBronze.get(i).getText());
			for (int j = i+1; j < lsBronze.size(); j++) {
				int num2 = Integer.parseInt(lsBronze.get(j).getText());
				if(num1 + num2 == 18) {
					bronze18.add(lsCountries.get(i).getText().trim());
					bronze18.add(lsCountries.get(j).getText().trim());
				}
			}
		}
		return bronze18;
	}

}