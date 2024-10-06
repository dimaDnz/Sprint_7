import io.restassured.RestAssured;

public class Url {
    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
}
