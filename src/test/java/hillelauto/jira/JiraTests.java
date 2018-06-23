package hillelauto.jira;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hillelauto.WebDriverTestBase;

// класс нужен для превращения наших операций в тесты для testNG,
// сдесь не используем Selenium WEBDriver
// maven запускает TestNG, а TestNG знает статические у нас методы или нет, если не статические, то он сначала создаст объект этого класса,
// потом у этого объекта будет запускать эти тесты
// уникальные свойства для тестов
public class JiraTests extends WebDriverTestBase { // наши тесты наследуются от WebDriverTestBase
    private LoginPage loginPage;
    private IssuePage issuePage;

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        /*
        browser наследуется из WebDriverTestBase.
        PageFactory импортируется из Selenium и имеет всего один метод .initElements(), который вернет
        объект (browser) заданного класса (LoginPage.class, IssuePage.class)
        initElements() позволяет выполнить все селекторы в LoginPage и IssuePage для заданного браузера browser
        создаем объекти типа LoginPage.class, IssuePage.class и автоматически вшиваем из в браузер
        */
        loginPage = PageFactory.initElements(browser, LoginPage.class); // инициализируем loginPage вернет
        // LoginPage потому что loginPage это LoginPage, объявлено выше. PageFactory.initElements нужно для инициализации аннотации @fFindBy
        issuePage = PageFactory.initElements(browser, IssuePage.class);
        System.out.println("Jira Pages Initialized");
    }

    @Test(description = "1. Invalid Login", priority = -1)
    public void failureLogin() {
        loginPage.failureLogin();
    }

    @Test(description = "2. Valid Login", groups = { "Sanity" })
    public void successfulLogin() {
        loginPage.successfulLogin();
    }

    @Test(description = "3. Create issue", dependsOnMethods = { "successfulLogin" }, groups = { "Sanity", "Issues" })
    public void createIssue() {
        issuePage.createIssue();
    }

    @Test(description = "4. Open issue", dependsOnMethods = { "createIssue" }, groups = { "Sanity", "Issues" })
    public void openIssue() {
        issuePage.openIssue();
    }

    @Test(description = "5. Uplaod Attachment", dependsOnMethods = { "openIssue" }, groups = { "Issues.Attachments" })
    public void uploadAttachment() {
        issuePage.uploadAttachment();
    }

    @Test(description = "Download Attachment", dependsOnMethods = { "uploadAttachment" }, groups = {
            "Issues.Attachments", "disabled" })
    public void downloadAttachment() {
        // issuePage.downloadAttachment();
    }
}
