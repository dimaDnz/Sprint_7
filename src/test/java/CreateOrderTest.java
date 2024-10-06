import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    Order order;

    public CreateOrderTest(Order order) {
        this.order = order;
    }

    @Before
    public void setUp(){
        requestForAnOrder.setUp();
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {new Order("Имя","Фамилия","Адрес, 1","Черкизовская","79999999999",2,"2024-05-12","Комментарий",new String[]{"BLACK"})},
                {new Order("Имя","Фамилия","Адрес, 2","Сокольники","79666666666",3,"2024-05-12","Комментарий",new String[]{"GREY"})},
                {new Order("Имя","Фамилия","Адрес, 3","Лубянка","795555555555",4,"2024-05-12","Комментарий",new String[]{"BLACK","GREY"})},
                {new Order("Имя","Фамилия","Адрес, 4","Чистые пруды","79444444444",5,"2024-05-12","Комментарий",new String[]{})}
        };
    }

    RequestForAnOrder requestForAnOrder = new RequestForAnOrder();

    @Test
    @DisplayName("Создать заказ") //позитивный тест
    @Description("Проверка создания заказа")
    public void checkCreateOrder(){
        requestForAnOrder.setOrder(order);
        requestForAnOrder.createOrder()
                .then().statusCode(201)
                .and()
                .assertThat().body("track", notNullValue());
    }
}