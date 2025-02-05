package org.abtest;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class ParellalTest {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public Capabilities cap;

    public WebDriver getDriver() {

        return driver.get();
    }


    @Parameters({ "browser" })
    @Test
    public void doLogin(String browser) throws MalformedURLException, InterruptedException {

        //java -jar selenium-server-4.1.0.jar node --detect-drivers true --publish-events tcp://192.168.1.16:4442 --subscribe-events tcp://192.168.1.24:4443


        if (browser.equals("firefox")) {

            cap = new FirefoxOptions();

        } else if (browser.equals("chrome")) {

            cap = new ChromeOptions();
        }

        driver.set(new RemoteWebDriver(new URL("http://13.126.60.135:4444"), cap));

        getDriver().get("http://google.com");
        getDriver().findElement(By.name("q")).sendKeys("Hello Grid !!!"+browser);
        System.out.println(getDriver().getTitle()+"---on browser---"+browser);
        Thread.sleep(10000);
        getDriver().quit();
    }

}
