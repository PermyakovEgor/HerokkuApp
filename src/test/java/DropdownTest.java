import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DropdownTest {

    @Test
    public void dropdown() {
        //Задаем опции для нашего драйвера
        SoftAssert softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfication");

        //Определяем браузер в котором хотим работать
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));

        //Получение первого элемента выпадающего списка и проверка его значения
        WebElement option = select.getFirstSelectedOption();

        String currentText = option.getText();

        // Проверяем, что изначально выбрано "Please select an option"
        softAssert.assertEquals(currentText, "Please select an option",
                "Изначально выбран не 'Please select an option'");

        // Получаем первый выбранный элемент
        select.selectByVisibleText("Option 1");
        WebElement secondOption = select.getFirstSelectedOption();
        String firstText = secondOption.getText();
        // Проверяем, что теперь выбрана "Option 1"
        softAssert.assertEquals(firstText, "Option 1", "После выбора не установлена 'Option 1'");

        // Получаем новый выбранный элемент
        select.selectByIndex(2);
        WebElement thirdOption = select.getFirstSelectedOption();
        String secondText = thirdOption.getText();
        // Проверяем, что теперь выбрана "Option 2"
        softAssert.assertEquals(secondText, "Option 2", "После выбора не установлена 'Option 2'");

        driver.quit();

        softAssert.assertAll();
    }
}
