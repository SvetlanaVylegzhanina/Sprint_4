package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    public WebDriver driver;

    // локатор кнопки "Заказать" в шапке страницы
    private final By orderHeaderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    // локатор кнопки "Заказать" в середине страницы
    private final By orderMiddleButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //локатор кнопки "да все привыкли"
    private final By acceptCookieButton = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Получить текст ответа по номеру вопроса
    public String getTextAnswerByQuestionId(int questionId){
        //Найти вопрос и нажать
        By questionLocator = getQuestionLocatorById(questionId);
        WebElement questionElement = driver.findElement(questionLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        questionElement.click();

        //Получить текст ответа
        By answerLocator = getAnswerLocatorById(questionId);
        return driver.findElement(answerLocator).getText();
    }
    // Найти локатор вопроса по номеру вопроса
    private By getQuestionLocatorById(int questionNumber){
        String questionId = String.format("accordion__heading-%s", questionNumber - 1);
        return By.id(questionId);
    }

    // Найти локатор ответа по номеру вопроса
    private By getAnswerLocatorById(int questionNumber){
        String xpath = String.format(".//div[(@id='accordion__panel-%s' and not(@hidden))]/p", questionNumber - 1);
        return By.xpath(xpath);
    }

    //Нажать на кнопку "Заказать" в шапке страницы
    public void clickOrderHeaderButton(){
        driver.findElement(orderHeaderButton).click();
    }

    //Нажать на кнопку "Заказать" в середине страницы
    public void clickOrderMiddleButton(){
        WebElement buttonElement = driver.findElement(orderMiddleButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttonElement);
        buttonElement.click();
    }

    // нажать кнопку "да все привыкли"
    public void clickAcceptCookieButton(){
        driver.findElement(acceptCookieButton).click();
    }

}
