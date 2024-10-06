import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CreateCourierTest {
    Courier courier = new Courier("maga", "1234", "magomed");
    CourierRequest courierRequest = new CourierRequest();

    @Before
    public void setUp(){
        courierRequest.setUp();
    }

    @Test
    @DisplayName("Создать курьера") //позитивный тест
    @Description("Тест на метод создания курьера")
    public void checkCreateCourier(){
        courierRequest.setCourier(courier);
        courierRequest.createCourier()
                .then().assertThat().body("ok", is(true))
                .and()
                .statusCode(201);
    }

    @Test
    @DisplayName("Создать уже существующего курьера") //негативный тест
    @Description("Проверка на создание дубликата курьера")
    public void checkCreateDuplicateCourier(){
        courierRequest.setCourier(courier);
        courierRequest.createCourier();
        courierRequest.createCourier()
                .then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    @DisplayName("Создать курьера с пустым логином") //негативный тест
    @Description("Проверка создания курьера с пустым логином")
    public void checkCreateCourierWithoutLogin(){
        courierRequest.setCourier(new Courier("","1234","magomed"));
        courierRequest.createCourier()
                .then().statusCode(400)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создать курьера с пустым паролем") //негативный тест
    @Description("Проверка создания курьера с пустым паролем")
    public void checkCreateCourierWithoutPassword(){
        courierRequest.setCourier(new Courier("maga","","magomed"));
        courierRequest.createCourier()
                .then().statusCode(400)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создать курьера с пустым  именем") //позитивный тест
    @Description("Проверка создания курьера с пустым именем")
    public void checkCreateCourierWithoutName() {
        courierRequest.setCourier(new Courier("maga", "1234", ""));
        courierRequest.createCourier()
                .then().assertThat().body("ok", is(true))
                .and()
                .statusCode(201);
    }

    @After
    public void cleanData(){
        courierRequest.deleteCourier();
    }
}