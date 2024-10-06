import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierRequest extends Url {
    private Courier courier;
    private final static String URI_CREATE_COURIER = "/api/v1/courier"; //апи для создания курьера
    private final static String URI_LOGIN_COURIER ="/api/v1/courier/login"; //апи для логина курьера в системе

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    @Step("Создание курьера")
    public Response createCourier(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(URI_CREATE_COURIER);
        return response;
    }

    @Step("Авторизация курьера")
    public Response loginCourier(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(URI_LOGIN_COURIER);
        return response;
    }

    @Step("Удаление курьера")
    public void deleteCourier(){
        Integer id =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(URI_LOGIN_COURIER)
                        .then().extract().body().path("id");
        if (id != null) {
            given()
                    .delete(URI_CREATE_COURIER + "/{id}", id.toString());}
    }
}