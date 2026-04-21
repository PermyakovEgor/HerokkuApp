import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationTest {

    @Test
    public void notification() {
        // Задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        // Определяем браузер, в котором хотим работать
        WebDriver driver = new ChromeDriver(options);

        //Попробовал все равно закрыть браузер после провала теста
        try {
            // Открывает страницу по указанному URL
            driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

            // Находим и кликаем на кнопку
            WebElement clickHere = driver.findElement(By.linkText("Click here")); //linkText так как есть ссылочные теги
            clickHere.click();

            // Ждём появления уведомления (по id, по классу не понял как, когда класс с пробелом)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

            // Получаем текст уведомления и выводим
            String notificationText = notification.getText();
            System.out.println("Получили нотификацию: " + notificationText);

            // Проверяем, соответствие текста в части нотификации, через assertEquals не получилось выделить нужный текст, он там с пробелами и переносами строк
            Assert.assertTrue(notificationText.contains("Action successful"), "Текст уведомления не содержит 'Action successful'. Получено: " + notificationText);
        } finally {
            driver.quit();
        }
    }
}
