import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    @Test
    public void input() {
        //Задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfications");

        //Определяем браузер в котором хотим работать
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/inputs");

        //Находим поле ввода и сохраняем в переменную
        var value = driver.findElement(By.tagName("input")); //создал значение, чтобы каждый раз не писать при sendKeys

        //попытка ввести текст в поле ввода number
        value.sendKeys("tast");
        String actualText = value.getAttribute("value"); //Переменная, записывающая то, что возвращается из HTML value
        softAssert.assertEquals(actualText, "", "Поле приняло текстовый ввод, хотя должно принимать только числа");
        //Есть проблема, при вводе разрешенной e, она не записывается почему-то в value, gettext тоже не понял как вернуть это значение
        value.clear(); //очистка переменной value от значения e

        //попытка ввести цифры в поле ввода number
        value.sendKeys("10");
        String actualNumber = value.getAttribute("value");
        softAssert.assertEquals(actualNumber, "10", "Цифровое значение не вводится в поле");
        System.out.println("Значение в поле, после того произошел ввод числа 10: " + actualNumber);

        //попытка увеличить значение в поле с помощью стрелок
        value.sendKeys(Keys.ARROW_UP);
        String valueAfterUp = value.getAttribute("value");
        softAssert.assertEquals(valueAfterUp, "11", "Значение не увеличилось до 11 после нажатия на стрелку");
        System.out.println("Значение в поле, после увеличения через стрелку: " + valueAfterUp);

        //попытка уменьшить значение в поле с помощью стрелок
        value.sendKeys(Keys.ARROW_DOWN);
        value.sendKeys(Keys.ARROW_DOWN);
        String valueAfterDown = value.getAttribute("value");
        softAssert.assertEquals(valueAfterDown, "9", "Значение не уменьшилось до 9 после двух стрелок вниз");
        System.out.println("Значение в поле, после увеличения через стрелку: " + valueAfterDown);

        driver.quit();

        softAssert.assertAll(); // Выводим все накопленные ошибки
    }
}
