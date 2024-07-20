package org.abtest;

import org.DesignPatternDevOps.PageObjects.TravelHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class DemoTest extends BaseTest{
    WebDriver driver;
    By sectionElement = By.id("flightSearchContainer");
    TravelHomePage travelHomepage;

   // @Parameters({ "browser" })
    @BeforeTest
    public void setUp( ) throws MalformedURLException {
        driver = intializeDriver();
        travelHomepage = new TravelHomePage(driver);
    }

    //TO RUN IN PARALLEL THREAD:
    // @Parameters({ "browser" })
   /* @BeforeTest
    public void setUp(String browser) throws MalformedURLException {

        driver = intializeDriver(browser);
        travelHomepage = new TravelHomePage(driver);
    }*/

    @Test(dataProvider = "getData")
    public void flightTest(HashMap<String, String> reservationDetails) throws InterruptedException {
       // System.setProperty("webdriver.chrome.driver","D:/Arpita/ABtesting_softwares/Google_Chrome.exe");
       // driver=new ChromeDriver();

        travelHomepage.goTo();
        System.out.println(travelHomepage.getFooterNav().getFlightAttribute());
        System.out.println(travelHomepage.getNavigationBar().getFlightAttribute());
        System.out.println(travelHomepage.getFooterNav().getLinkCount());
        travelHomepage.getTitle();

        System.out.println(travelHomepage.getNavigationBar().getLinkCount());
     //For strategy design pattern:  // travelHomepage.setBookingStrategy(new MultiTrip(driver, sectionElement));
        travelHomepage.setBookingStrategy("Multitrip");

        travelHomepage.checkAvail(reservationDetails);
        System.out.println("Jenkins auto trigger test");
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //Below commented rows when we have no json file for parameterization
        /*HashMap<String, String> reservationDetails = new HashMap<String, String>();
        reservationDetails.put("origin","MAA");
        reservationDetails.put("destination","HYD");
        reservationDetails.put("destination2","DEL");
        HashMap<String, String> reservationDetails1 = new HashMap<String, String>();
        reservationDetails1.put("origin","DEL");
        reservationDetails1.put("destination","HYD");
        reservationDetails1.put("destination2","MAA");
        List<HashMap<String, String>> l= new ArrayList();
        l.add(reservationDetails);
        l.add(reservationDetails1);*/
        List<HashMap<String, String>> l= getJsonData(System.getProperty("user.dir")+"//src//test//java//org//abtest//DataLoads//reservationDetails.json");
        return new Object[][]
                {
                        {l.get(0)}, {l.get(1)}
                };
    }

}
