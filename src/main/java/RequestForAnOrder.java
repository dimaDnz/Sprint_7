import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RequestForAnOrder extends Url {
    private Order order;
    private final static String GET_LIST_OF_ORDERS = "/api/v1/orders"; //апи для получения списка заказов

    public void setOrder(Order order) {
        this.order = order;
    }

    @Step("Создание заказа")
    public Response createOrder (){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(order)
                        .when()
                        .post(GET_LIST_OF_ORDERS);
        return response;
    }

    @Step("Получение списка заказов")
    public Response getOrders (){
        Response response =
                given()
                        .get(GET_LIST_OF_ORDERS);
        return  response;
    }
}
