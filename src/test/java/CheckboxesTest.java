import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckboxesTest {

    @Test
    public void checkBoxes() {
        //Задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfication");

        //Определяем браузер в котором хотим работать
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement firstCheckbox = driver.findElements(By.cssSelector("[type=checkbox]")).get(0);
        // Проверяем исходное состояние (отмечен ли)
        boolean checkbox1 = firstCheckbox.isSelected();
        Assert.assertFalse(checkbox1, "Первый чекбокс должен быть не отмечен");
        // Кликаем, чтобы изменить состояние
        firstCheckbox.click();
        // Проверяем, что состояние изменилось
        Assert.assertTrue(firstCheckbox.isSelected(), "После клика чекбокс должен быть отмечен");

        WebElement secondCheckbox = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);
        boolean checkbox2 = secondCheckbox.isSelected();
        Assert.assertTrue(checkbox2, "Второй чекбокс должен быть отмечен");
        // Кликаем, чтобы изменить состояние
        secondCheckbox.click();
        // Проверяем, что состояние изменилось
        Assert.assertFalse(secondCheckbox.isSelected(), "После клика чекбокс должен быть не отмечен");

        //driver.quit();
    }
}
