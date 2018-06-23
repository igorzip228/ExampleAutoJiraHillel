package hillelauto;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

// родительская структура для наших тестов, общие данные
// сюда можно добавлять новые данные для любых файлов с тестами, как JiraTests
public class WebDriverTestBase {
    protected WebDriver browser;

    // перепишем public void setUP(){
    //              System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver")
    // static нужен для паралельных браузеров, статический блок, который выполнится один раз при первом обращении к классу
    static {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

    @BeforeTest(alwaysRun = true) //применим для всех сайтов
    public void setUp() {
        browser = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized", "--incognito")); // запускаем chrome с параметрами
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Tools.setDriver(browser); // запомни браузер и все последующие операции делай в нем
    }

    @AfterTest(alwaysRun = true) //применим для всех сайтов
    public void finish() {
        browser.close();
    }
}