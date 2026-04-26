package HomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {
    
    @Test
    public void loadFile() {
        //Задаем опции для нашего драйвера
        SoftAssert softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfication");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/upload");
        
        File file = new File("src/test/resources/1.txt");
        driver.findElement(By.xpath("//input[@type='file']" )).
                sendKeys("C:/Users/Perffi/IdeaProjects/HerokkuApp/src/test/resources/1.txt" );
        driver.findElement(By.cssSelector("#file-submit")).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
        softAssert.assertEquals(element.getText(), "1.txt", "Имя файла не соответствует загруженному");

        driver.quit();

        softAssert.assertAll();
    }
}
