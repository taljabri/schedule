package hello;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by student on 3/21/17.
 */
public class Crawler {




        public static void runCrawler() throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            WebDriver driver = new ChromeDriver();

            driver.get("http://my.sc.edu");

            WebElement element = driver.findElement(By.partialLinkText("Sign in to"));
            element.click();

            WebElement element1 = driver.findElement(By.name("username"));
            element1.sendKeys("00306157");

            WebElement element2 = driver.findElement(By.id("vipid-password"));
            element2.sendKeys("Q!w2e3r4");

            WebElement button = driver.findElement(By.name("submit"));
            button.click();

            WebElement stuMenu = driver.findElement(By.id("bmenu--P_StuMainMnu___UID1"));
            stuMenu.click();

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            WebElement regMenu = driver.findElement(By.id("bmenu--P_RegMnu___UID1"));
            regMenu.click();

            WebElement lookMenu = driver.findElement(By.id("contentItem12"));
            lookMenu.click();

            WebElement semMenu = driver.findElement(By.name("p_term"));
            Select semester= new Select(semMenu);
            semester.selectByVisibleText("Spring 2017");

            WebElement button2 = driver.findElement(By.id("id____UID7"));
            button2.click();

            WebElement advance = driver.findElement(By.id("id____UID6"));
            advance.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            WebElement camMenu = driver.findElement(By.id("camp_id"));
            Select campus= new Select(camMenu);
            campus.selectByVisibleText("USC Columbia");

            WebElement subMenu = driver.findElement(By.id("subj_id"));
            Select subject= new Select(subMenu);
            subject.selectByVisibleText("CSCE - Comp Sci & Comp Engr");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            WebElement search = driver.findElement(By.id("id____UID5"));
            search.click();

            File scheudulerDir = new File(System.getProperty("user.dir")+"/scheduleCollection");

            if(!scheudulerDir.exists()) {
                try {
                    scheudulerDir.mkdir();
                } catch(SecurityException sec) {
                    sec.printStackTrace();
                }
            }

            String filename = scheudulerDir + "/spring2017.csv";

            File file = new File(filename);
            FileWriter fileWriter = null;

            try {

                if(file.exists()) {
                    fileWriter = new FileWriter(filename);
                } else {

                    file.createNewFile();
                    fileWriter = new FileWriter(filename);
                }

            WebElement content= driver.findElement(By.className("dddefault"));
                String[] rowTxt;
                String locator = "//form/table/tbody";
                String source = "<table>" + driver.findElement(By.xpath(locator)).getAttribute("innerHTML") + "<table>";
                org.jsoup.nodes.Document doc = Jsoup.parse(source, "UTF-8");
                for (org.jsoup.nodes.Element rowElmt : doc.getElementsByTag("tr")) {
                    Elements cols = rowElmt.getElementsByTag("th");
                    if (cols.size() == 0)
                        cols = rowElmt.getElementsByTag("td");
                    rowTxt = new String[cols.size()];
                    for (int i = 0; i < rowTxt.length; i++) {
                        fileWriter.append(cols.get(i).text());
                        fileWriter.append(',');
                }
                fileWriter.append('\n');
            }


        } catch (Exception e1) {
        e1.printStackTrace();
    } finally {
        try {

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {

            System.out.println("Error while flushing/closing fileWriter !!!");
            e.printStackTrace();
        }
    }



}

    }



