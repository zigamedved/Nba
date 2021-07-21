package day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SeleniumMaven {


    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        name=name.toLowerCase();

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Ziga\\Desktop\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        driver.get("https://www.nba.com/players");

        /////////////////////////////////////////////////////////////////

        WebElement searchElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[3]/section/div/div[1]/div/input"));

        searchElement.sendKeys(name);

        TimeUnit.SECONDS.sleep(1);

        WebElement profile = driver.findElement(By.xpath("//table/tbody/tr/td/a"));
        String link = profile.getAttribute("href");

        String[] array = link.split("/");
        String a="https://www.nba.com/stats/player/";
        link=a+array[4]+"/"+"?Season=2020-21&SeasonType=Regular%20Season&PerMode=Per40";
        driver.get(link);

        //seznam 3PA, odigranih sezon
        List<WebElement> pa = driver.findElements(By.xpath("/html/body/main/div/div/div/div[4]/div/div/div/div/nba-stat-table[1]/div[2]/div[1]/table/tbody/tr/td[10]"));
        String[] array1 = new String[pa.size()+1];
        String[] array2 = new String[pa.size()+1];

        WebElement points;
        WebElement demo;
        for (int i = 1; i<=pa.size(); i++){
            points=(driver.findElement(By.xpath("html/body/main/div/div/div/div[4]/div/div/div/div/nba-stat-table[1]/div[2]/div[1]/table/tbody/tr["+i+"]/td[10]")));
            demo=(driver.findElement(By.xpath("/html/body/main/div/div/div/div[4]/div/div/div/div/nba-stat-table[1]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]")));
            array2[i]=(demo.getAttribute("innerHTML")).trim();
            array1[i]=(points.getText());
        }
        //

        //koncni izpis
        for(int i = 1 ; i<=pa.size(); i++){
            System.out.println(array2[i]+" "+array1[i]);
        }
        //

        TimeUnit.SECONDS.sleep(10);
        driver.quit();
    }
}
