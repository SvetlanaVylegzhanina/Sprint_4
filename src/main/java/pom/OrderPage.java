package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    public WebDriver driver;

    //1 форма
    //локаторы полей
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // локатор кнопки "Далее"
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //2 форма
    //локаторы полей
    private final By beginDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-control");
    private final By colorIsBlackScooterField = By.id("black");
    private final By colorIsGreyScooterField = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор заголовка формы "Про аренду"
    private final By titleForm = By.xpath( "//div[@class='Order_Header__BZXOb']");
    //локатор кнопки "Заказать"
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");


    //локатор кнопки "Да" окна подтверждения
    private final By orderAcceptButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    //локатор окна "Заказ оформлен"
    private final By ModalHeaderText = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //заполнение полей 1 формы
    public void setCustomerFields(String firstName, String lastName, String address, String subwayStation, String phoneNumber) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(subwayStationField).click();

        //выбрать станцию метро
        By subwayFromList = By.xpath(String.format("//div[text()='%s']", subwayStation));
        WebElement subwayStationToSelect = driver.findElement(subwayFromList);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", subwayStationToSelect);
        subwayStationToSelect.click();

        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    //заполнение полей 2 формы
    public void setOrderDetailsFields(String beginDate, String rentalPeriod, boolean isBlack, String comment) {
        driver.findElement(beginDateField).sendKeys(beginDate);
        driver.findElement(titleForm).click();
        driver.findElement(rentalPeriodField).click();
        // выбрать срок аренды
        By dataRentalFromList = By.xpath(String.format("//div[@class='Dropdown-menu']/div[text()='%s']", rentalPeriod));
        WebElement dataRentalToSelect = driver.findElement(dataRentalFromList);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dataRentalToSelect);
        dataRentalToSelect.click();
        // если не черный цвет, то подразумевается, что выбран серый цвет
        if(isBlack){
            driver.findElement(colorIsBlackScooterField).click();
        } else {
            driver.findElement(colorIsGreyScooterField).click();
        }

        driver.findElement(commentField).sendKeys(comment);
    }

    //нажать кнопку "Заказать" на 2 форме
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //принять куки
    public void clickAcceptButton() {
        driver.findElement(orderAcceptButton).click();
    }

    //получить текст заголовка "Заказ оформлен"
    public String getOrderModalHeaderText() {
        return driver.findElement(ModalHeaderText).getText();
    }
}

