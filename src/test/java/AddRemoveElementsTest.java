import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveElementsTest {

    @Test
    public void checkAddRemoveElement() {
        //Задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfication");

        //Определяем браузер в котором хотим работать
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Добавляем 2 элемента
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();

        //Удаляем 1 элемент
        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        int size = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        //выводим кол-во элементов
        System.out.println("Кол-во элементов: " + size);

        //Закрывает браузер
        driver.quit();
    }
}
