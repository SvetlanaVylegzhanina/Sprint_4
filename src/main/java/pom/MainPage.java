package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    public WebDriver driver;
    //локаторы вопросов
    private final By howMuchCost = By.id("accordion__heading-0");
    private final By severalScooters = By.id("accordion__heading-1");
    private final By rentalTime = By.id("accordion__heading-2");
    private final By orderToday = By.id("accordion__heading-3");
    private final By extendOrder = By.id("accordion__heading-4");
    private final By bringCharging = By.id("accordion__heading-5");
    private final By cancelOrder = By.id("accordion__heading-6");
    private final By beyondMKAD = By.id("accordion__heading-7");

    //локаторы ответов
    private final By howMuchCostAnswer = By.xpath(".//div[(@id='accordion__panel-0' and not(@hidden))]/p");
    private final By severalScootersAnswer = By.xpath(".//div[(@id='accordion__panel-1' and not(@hidden))]/p");
    private final By rentalTimeAnswer = By.xpath(".//div[(@id='accordion__panel-2' and not(@hidden))]/p");
    private final By orderTodayAnswer = By.xpath(".//div[(@id='accordion__panel-3' and not(@hidden))]/p");
    private final By extendOrderAnswer = By.xpath(".//div[(@id='accordion__panel-4' and not(@hidden))]/p");
    private final By bringChargingAnswer = By.xpath(".//div[(@id='accordion__panel-5' and not(@hidden))]/p");
    private final By cancelOrderAnswer = By.xpath(".//div[(@id='accordion__panel-6' and not(@hidden))]/p");
    private final By beyondMKADAnswer = By.xpath(".//div[(@id='accordion__panel-7' and not(@hidden))]/p");

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
    private By getQuestionLocatorById(int questionId){
        if (questionId == 1){
            return howMuchCost;
        }
        if (questionId == 2){
            return severalScooters;
        }
        if (questionId == 3){
            return rentalTime;
        }
        if (questionId == 4){
            return orderToday;
        }
        if (questionId == 5){
            return extendOrder;
        }
        if (questionId == 6){
            return bringCharging;
        }
        if (questionId == 7){
            return cancelOrder;
        }
        if (questionId == 8){
            return beyondMKAD;
        }
        //если по указанному id не найден локатор
        return null;
    }

    // Найти локатор ответа по номеру вопроса
    private By getAnswerLocatorById(int answerId){
        if (answerId == 1){
            return howMuchCostAnswer;
        }
        if (answerId == 2){
            return severalScootersAnswer;
        }
        if (answerId == 3){
            return rentalTimeAnswer;
        }
        if (answerId == 4){
            return orderTodayAnswer;
        }
        if (answerId == 5){
            return extendOrderAnswer;
        }
        if (answerId == 6){
            return bringChargingAnswer;
        }
        if (answerId == 7){
            return cancelOrderAnswer;
        }
        if (answerId == 8){
            return beyondMKADAnswer;
        }
        //если по указанному id не найден локатор
        return null;
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
