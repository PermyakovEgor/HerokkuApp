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

import java.time.Duration;
import java.util.List;

public class DynamicControlsTest {

    @Test
    public void useWait() {
        //Задаем опции для нашего драйвера
        SoftAssert softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfication");

        //Определяем браузер в котором хотим работать
        WebDriver driver = new ChromeDriver(options);

        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //Проверка активности чекбокса
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#checkbox-example > button")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//*[@id='message']"),
                "It's gone!"
        ));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        List<WebElement> checkboxes = driver.findElements(By.id("checkbox"));
        softAssert.assertTrue(checkboxes.isEmpty(), "Чекбокс не исчез со страницы");

        //Проверка активности поля ввода
        WebElement inputElement = driver.findElement(By.cssSelector("#input-example input"));
        boolean isDisabled = inputElement.getAttribute("disabled") != null;
        softAssert.assertTrue(isDisabled, "Input должен быть disabled!");
        driver.findElement(By.cssSelector("#input-example > button")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//*[@id='message']"),
                "It's enabled!"
        ));
        softAssert.assertTrue(inputElement.isEnabled(), "Поле инпут по прежнему disabled");

        driver.quit();

        softAssert.assertAll();
    }
}
