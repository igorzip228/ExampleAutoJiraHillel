package hillelauto.jira;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import hillelauto.Tools;

// класс отвечает за реализацию операций на странице логина: успешный логин и неуспешный логин
public class LoginPage {
    private final By inputUsername = By.cssSelector("input#login-form-username"); // создаем переменную типа By
    private final By inputPassword = By.cssSelector("input#login-form-password");
    private final WebDriver browser;

    @FindBy(css = "a#header-details-user-fullname") //ищет элемент тип селектора и сам селектор
    private WebElement buttonProfile;
    /* указывается пннотируемая переменная, которая заполнится селектором выше.
     Сдесь будет лежать элемент, найденый по селектору
     Можно было бы написать:
            privete WebElement elem = browser.findElement(By.cssSelector("a#header-details-user-fullname");
     но этот код выполнится в момент создания объекта.
     Если страница не успеет открыться, то елемент не будет найден
     И поэтому код нужно писать внутри теста - но это тупо
     тут наш селектор превращается в скомпилированный вид когда создается объект и потом, когда они будут использоваться,
      они будут работать быстрее
      где полем воспользуемся в тот момент поиск и произойдет

      плюс такой записи в том, что сначала мы можем обьявить селекторы в начале,
      а использовать их будем во время обращения программы
     */
    @FindBy(css = "div#usernameerror")
    private List<WebElement> messageError; // будет искать с помощью findElements, List нужен для того чтоб не выкинуло Exeption

    public LoginPage(WebDriver browser) {
        this.browser = browser;
    } //конструктор, принимает браузер и записывает в локальную переменную

    public void successfulLogin() {
        login(true);
        Assert.assertEquals(JiraVars.username, buttonProfile.getAttribute("data-username"));
    }

    public void failureLogin() {
        login(false);
        Assert.assertTrue(messageError.size() != 0); // ищем все элементы заданного селектора, объявленного выше и если...
    }


    /*
    функция login открывает браузер и вводит данные в поля имя и пароль, подтверждает, которые мы нашли через
    селекторы выше
    */
    void login(boolean successful) { // private - доступен только в этом классе
        browser.get(JiraVars.baseURL);

        Tools.clearAndFill(inputUsername, JiraVars.username);
        Tools.clearAndFill(inputPassword, successful ? JiraVars.password : "badPassword").submit();// if(i != О ? true : false}
    }
}