package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.data.TestData;
import ru.yandex.praktikum.pages.OrderPage;

import static org.junit.Assert.assertTrue;

@Story("Тесты заказа самоката")
@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;
    private final String orderButtonPosition;

    public OrderTest(String name, String surname, String address, String metro, String phone,
                     String date, String color, String comment, String orderButtonPosition) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
        this.orderButtonPosition = orderButtonPosition;
    }

    @Parameterized.Parameters(name = "Заказ через {8} кнопку, данные: {0} {1}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {TestData.ORDER_DATA[0][0], TestData.ORDER_DATA[0][1], TestData.ORDER_DATA[0][2],
                        TestData.ORDER_DATA[0][3], TestData.ORDER_DATA[0][4], TestData.ORDER_DATA[0][5],
                        TestData.ORDER_DATA[0][6], TestData.ORDER_DATA[0][7], "верхнюю"},
                {TestData.ORDER_DATA[1][0], TestData.ORDER_DATA[1][1], TestData.ORDER_DATA[1][2],
                        TestData.ORDER_DATA[1][3], TestData.ORDER_DATA[1][4], TestData.ORDER_DATA[1][5],
                        TestData.ORDER_DATA[1][6], TestData.ORDER_DATA[1][7], "нижнюю"}
        };
    }

    @Test
    @Description("Позитивный тест заказа самоката")
    public void createOrderTest() throws Exception {   // ← ЕДИНСТВЕННОЕ ИЗМЕНЕНИЕ ЗДЕСЬ!
        if (orderButtonPosition.equals("верхнюю")) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, comment, color);
        orderPage.clickOrderButton();
        orderPage.confirmOrder();

        assertTrue("Заказ не был оформлен", orderPage.isOrderSuccess());
    }
}