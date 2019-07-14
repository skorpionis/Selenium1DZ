import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Ariec on 13.07.2019.
 */
public class MyFirstTest {
    WebDriver driver;
    @Test
    public void test() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rgs.ru");
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return window.stop");

        WebElement vipadaiushayaBtn = driver.findElement(By.xpath("//div[contains(@id," +
                " 'main-navbar-collapse')]//li[contains(@class, " +
                "'dropdown')]/a[contains(text(), 'Страхование')]"));
        vipadaiushayaBtn.click();

        WebElement dobrMedStrahBtn = driver.findElement(By.xpath("//a[contains(text(), " +
                "'ДМС') and not (contains(text(), 'Полис'))]"));
        dobrMedStrahBtn.click();

        WebElement sendIssuranceBTN = driver.findElement(By.xpath("//a[contains(text()," +
                " 'Отправить заявку')]"));


        sendIssuranceBTN.click();
        zapolnenieName("Фамилия", "Абырвалг");
        zapolnenieName("Имя", "Морж");
        zapolnenieName("Отчество", "Сергеевич");
        zapolnenieName("Телефон", "8005553535");
        zapolnenieName("Эл. почта", "abc@mai.ru");
        zapolnenieName("Предпочитаемая дата контакта", "01.01.2013");
        zapolnenieName("Комментарии", "fdfdsafsfsdfs");

        By selxP = By.xpath("//*[@name='Region']");
        WebElement element = driver.findElement(selxP);

        WebElement element1=driver.findElement(By.xpath("//*[contains(text(),'Я согласен')]" +
                "/preceding::input[1]"));
        element1.click();

        Select select = new Select(element);
        select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        Arrays.asList(element.getText().split("\n"));
        By selxpNumber = By.xpath("");
        driver.findElement(By.xpath("//*[@id='button-m']")).click();
        Assert.assertEquals("Введите email", driver.findElement(By.xpath("//*[text()='Эл. почта']")).getText());

        driver.close();
        driver.quit();
    }
    public void zapolnenieName(String name, String textForZapoln){
        String tem = "//*[text()='%s']";
        String xpath = String .format(tem, name);
        driver.findElement(By.xpath(xpath)).sendKeys(textForZapoln);
    }

}
