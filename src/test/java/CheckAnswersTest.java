import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckAnswersTest extends TestBase{
    private final int questionId;
    private final String expectedAnswer;

    public CheckAnswersTest(int questionId, String expectedAnswer) {
        this.questionId = questionId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters // Тестовые данные
    public static Object[][] getQuestionData() {
        return new Object[][] {
                { 1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { 4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { 5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { 6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { 7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { 8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };

    }

    // Проверка соответствия вопросов и ответов
    @Test
    public void checkAnswerText() {
        // Объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objMainPage.clickAcceptCookieButton();
        String actualTextAnswer = objMainPage.getTextAnswerByQuestionId(questionId);
        assertEquals("Текст ответа " + questionId + " не совпадает с ожидаемым.", expectedAnswer, actualTextAnswer);
    }

}