package HomeWork2;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DownloadFileTest {

    @Test
    public void downloadFileTest() throws InterruptedException {
        //Задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "C:\\Users\\Perffi\\Downloads");
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notfications");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/download");

        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > div > a:nth-child(4)")));
        downloadLink.click();

        // Папка для проверки
        File downloadFolder = new File("C:\\Users\\Perffi\\Downloads");

        Thread.sleep(3000);
        //Поиск файлов в папке
        File[] listOfFiles = downloadFolder.listFiles();
        File f = null;
        File downloadedFile = null; //переменная для хранения файла, вместе с расположением
        //Вывод названий всех файлов в папке + сравнение с необходимым
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.equals(downloadLink.getText())) {
                    downloadedFile = listOfFile;
                    f = new File(fileName);
                }
            }
        }
        Assert.assertEquals(f.toString(), downloadLink.getText(), "Не нашло скачанного файла");
        downloadedFile.deleteOnExit();
        driver.quit();
    }
}
