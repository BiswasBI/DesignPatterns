package org.abtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public Capabilities cap;

    public WebDriver getDriver() {

        return driver.get();
    }

    public WebDriver intializeDriver( ) throws MalformedURLException {
        //local run
    /*    WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;*/

        //GRID RUN (WITH DOCKER) - single browser
        FirefoxOptions opt = new FirefoxOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://13.127.176.29:4444/"), opt);
        return driver;



    }

    //GRID parallel run:
   /* public WebDriver intializeDriver(String browser) throws MalformedURLException {
        //local run


        //GRID RUN (WITH DOCKER) - single browser
        FirefoxOptions opt = new FirefoxOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://65.2.31.132:4444/"), opt);
        return driver;

        //GRID parallel run:
        if (browser.equals("firefox")) {

            cap = new FirefoxOptions();

        } else if (browser.equals("chrome")) {

            cap = new ChromeOptions();
        }
        else if (browser.equals("edge")) {

            cap = new EdgeOptions();
        }
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), cap));
        return getDriver();

    }*/
    //DataReader method on this base class because DemoTest class can directly access it
    public  List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        //convert json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        //convert string HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }

}
