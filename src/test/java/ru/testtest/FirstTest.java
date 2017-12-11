package ru.testtest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://igorakintev.ru/admin/");
    }

    @Test
    public void userLogin() {
        //авторизация
        WebElement loginField = driver.findElement(By.id("id_username"));
        loginField.sendKeys("silenium");

        WebElement passwordField = driver.findElement(By.id("id_password"));
        passwordField.sendKeys("super_password");

        WebElement loginButton = driver.findElement(By.className("submit-row"));
        loginButton.click();

    //проверка наличия заголовка
     boolean present;
        try {
             driver.findElement(By.className("dashboard-title"));
            present = true;
         } catch (org.openqa.selenium.NoSuchElementException e) {
             present = false;
         }

          WebElement addButton = driver.findElement(By.xpath("//*[@id=\"module_2\"]/div/ul[1]/li[1]/ul/li[1]/a/span"));
        addButton.click();

        //добавление записи
        Random random = new Random();
        int num = random.nextInt(999999999);
        String randomString = Integer.toString(num);


        WebElement titleField = driver.findElement(By.id("id_title"));
        titleField.sendKeys( "Title"+ randomString);

        int num2 = random.nextInt(999999999);
        randomString = Integer.toString(num2);

        WebElement slugField = driver.findElement(By.id("id_slug"));
        slugField.sendKeys("Slug"+randomString);

        WebElement textMarkdownField = driver.findElement(By.id("id_text_markdown"));
        textMarkdownField.sendKeys("Slug"+randomString);

        WebElement textField = driver.findElement(By.id("id_text"));
        textField.sendKeys("Slug"+randomString);

        WebElement addTextButton = driver.findElement(By.xpath("//*[@id=\"entry_form\"]/div/div/input[1]"));
        addTextButton.click();

        //проверка добавленной записи
        driver.get("http://igorakintev.ru/blog/");

        boolean present2;
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"entries\"]/h2[1]/a")).getText().contains("Title"));
            present2 = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            present2 = false;
        }

        //удаление записи

        driver.get("http://igorakintev.ru/admin/blog/entry/");

        WebElement selectCheckbox = driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/td[1]/input"));
        selectCheckbox.click();

        WebElement selectCombobox = driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[2]/label/select/option[2]"));
        selectCombobox.click();

        WebElement selectDelete = driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[2]/button"));
        selectDelete.click();

        WebElement selectDeleteOk = driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[4]"));
        selectDeleteOk.click();
    }

        @AfterClass
        //сохранение и выход драйвера
    public static void tearDown() {
  // WebElement selectSafe = driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/p/input"));
  //        selectSafe.click();
   //     driver.quit();
    }

}
