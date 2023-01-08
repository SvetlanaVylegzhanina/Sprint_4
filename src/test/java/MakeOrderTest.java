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
    private final boolean isFromHeader;
    public MakeOrderTest(String firstName, String lastName, String address, String subwayStation, String phoneNumber,
                         String beginDate, String rentalPeriod, boolean isBlack, String comment, boolean isFromHeader)
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
        this.isFromHeader = isFromHeader;
    }

    // Тестовые данные
    @Parameterized.Parameters()
    public static Object[] getData(){
        return new Object[][]{
                {"Иван","Иванов","ул Ленина 1","Площадь Революции","89991236547","20.12.2022","трое суток",true,"Позвонить за час", true},
                {"Петр", "Сидоров","ул Дзержинского 25","Смоленская","89197851036","01.02.2023","сутки",false,"Не звонить", false},
        };
    }

    //Проверка оформления заказа
   @Test
    public void makeOrderPositiveResult(){
        //нажать на кнопку "Заказать"
        MainPage objMainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objMainPage.clickAcceptCookieButton();

        //если передан флаг isFromHeader = true, то нажать кнопку в заголовке страницы, иначе в середине страницы
        if(isFromHeader){
            objMainPage.clickOrderHeaderButton();
        } else {
            objMainPage.clickOrderMiddleButton();
        }

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
