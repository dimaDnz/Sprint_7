import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
public class OrderListTest {
    RequestForAnOrder requestForAnOrder = new RequestForAnOrder();
    @Before
    public void setUp(){
        requestForAnOrder.setUp();}
    @Test
    @DisplayName("Получить лист заказов") //позитивный тест
    @Description("Проверка получения листа заказов")
    public void checkCreateOrder(){
        Response response = requestForAnOrder.getOrders();// Получаем ответ на запрос списка заказов

        response.then().statusCode(200); // Проверяем, что статус код ответа равен 200
        response.then().body("orders", notNullValue()); // Проверяем, что тело ответа не равно null
        response.then().body("orders", instanceOf(List.class)); // Проверяем, что тип данных поля "orders" является списком
        response.then().body("orders", not(empty()));  // Проверяем, что список заказов не пустой
        List<Order> orders = response.jsonPath().getList("orders", Order.class); // Получаем список заказов
        assertThat("Список заказов не должен быть пустым!", orders.isEmpty(),is(false)); // Проверяем, что список заказов не пустой
    }
}
