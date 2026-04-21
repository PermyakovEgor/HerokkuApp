import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class TyposTest {

    @Test
    public void typos() {
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
        driver.get("https://the-internet.herokuapp.com/typos");

        String text = "Sometimes you'll see a typo, other times you won,t.";
        int x = 0;
        int y = 0;
        while (x < 10) {
            try {
                String text1 = driver.findElement(By.xpath("(//p)[2]")).getText();
                if (text1.equals(text)) {
                    y++;
                    softAssert.fail("Обнаружена опечатка на итерации " + x + ": '" + text1 + "'");
                }
            } catch (NoSuchElementException e) {
                softAssert.fail("Элемент не найден на итерации " + x);
            }
            x++;
            driver.navigate().refresh();
        }

        System.out.println("Кол-во выводов страницы с неправильным текстом: " + y + ". Страница была перезагружена: " + x + " раз");

        //Закрывает браузер
        driver.quit();

        softAssert.assertAll(); // Выводим все накопленные ошибки
    }
}
