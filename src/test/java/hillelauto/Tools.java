package hillelauto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/* простой класс, который используют все, кому он нуженю Сюда можн одобавлять все нужные функции
это статический класс, чтоб без создания объекта мы могли использовать его методы

 */
public class Tools {
    private static WebDriver browser;

    /*
    записывает браузер как статический, чтоб можно было clearAndFill, которому нужен браузер, работал
    Как бы приказываем: "Все последующие операции выполняй в этом браузере"
     */
    public static void setDriver(WebDriver browser) {
        Tools.browser = browser;
    }

    public static WebElement clearAndFill(By selector, String data) {
        WebElement element = browser.findElement(selector);
        element.clear();
        element.sendKeys(data);

        return element;
    }

    //стандартный метод - есть дата, форматируем по заданному шаблону и возвращаем
    public static String timestamp() {
        return new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date());
    }
}