import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.MainPage;
import pom.OrderPage;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class MakeOrderTest extends TestBase
{
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String beginDate;
    private final String rentalPeriod;
    private final boolean isBlack;
    private final String comment;
    public MakeOrderTest(String firstName, String lastName, String address, String subwayStation, String phoneNumber,
                         String beginDate, String rentalPeriod, boolean isBlack, String comment)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.beginDate = beginDate;
        this.rentalPeriod = rentalPeriod;
        this.isBlack = isBlack;
        this.comment = comment;
    }

    // Тестовые данные
    @Parameterized.Parameters()
    public static Object[] getData(){
        return new Object[][]{
                {"Иван","Иванов","ул Ленина 1","Площадь Революции","89991236547","20.12.2022","трое суток",true,"Позвонить за час"},
                {"Петр", "Сидоров","ул Дзержинского 25","Смоленская","89197851036","01.02.2023","сутки",false,"Не звонить"},
        };
    }

    //Проверка оформления заказа через кнопку "Заказать" в шапке страницы
   @Test
    public void makeOrderByHeaderButton(){
        //нажать на кнопку в заголовке
        MainPage objMainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objMainPage.clickAcceptCookieButton();
        objMainPage.clickOrderHeaderButton();

        //заполнить поля первой формы
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setCustomerFields(firstName, lastName, address, subwayStation, phoneNumber);

        //перейти на вторую форму
        objOrderPage.clickNextButton();

        //заполнить поля второй формы
        objOrderPage.setOrderDetailsFields(beginDate, rentalPeriod, isBlack, comment);

        //нажать "Заказать" на второй форме
        objOrderPage.clickOrderButton();

        //нажать "Да" в окне подтверждения
        objOrderPage.clickAcceptButton();

        //сравнить ожидаемый текст заголовка "Заказ офомрлен" с фактическим
        MatcherAssert.assertThat(objOrderPage.getOrderModalHeaderText(), containsString("Заказ оформлен"));
    }

    //Проверка оформления заказа через кнопку "Заказать" в середине страницы
    @Test
    public void makeOrderByMiddleButton(){
        //нажать на кнопку в середине
        MainPage objMainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objMainPage.clickAcceptCookieButton();
        objMainPage.clickOrderMiddleButton();

        //заполнить поля первой формы
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setCustomerFields(firstName, lastName, address, subwayStation, phoneNumber);

        //перейти на вторую форму
        objOrderPage.clickNextButton();

        //заполнить поля второй формы
        objOrderPage.setOrderDetailsFields(beginDate, rentalPeriod, isBlack, comment);

        //нажать "Заказать" на второй форме
        objOrderPage.clickOrderButton();

        //нажать "Да" в окне подтверждения
        objOrderPage.clickAcceptButton();

        //сравнить ожидаемый текст заголовка "Заказ офомрлен" с фактическим
        MatcherAssert.assertThat(objOrderPage.getOrderModalHeaderText(), containsString("Заказ оформлен"));
    }
}
