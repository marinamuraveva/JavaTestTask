package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserTest {
    private static final String URL = "https://petstore.swagger.io/v2/user";
    private static final String username = "MuravevaTest";

    @Test
    public void createUser() throws JsonProcessingException {
        Specifications.installSpecification(Specifications.requestSpecification(URL),
                Specifications.responseSpecification200());

        UserData userData = new UserData();
        userData.setId(1);
        userData.setUsername(username);
        userData.setFirstName("test");
        userData.setLastName("test");
        userData.setEmail("test@test.com");
        userData.setPassword("qwerty");
        userData.setPhone("89990000000");
        userData.setUserStatus(0);
        String jsonUserData = new ObjectMapper().writeValueAsString(userData);
        given()
                .body(jsonUserData)
                .when()
                .post("")
                .then().log().all()
                .body("message", notNullValue());
    }

    @Test
    public void updateUser() throws JsonProcessingException {
        Specifications.installSpecification(Specifications.requestSpecification(URL),
                Specifications.responseSpecification200());

        Map<String, String> userUpd = new HashMap<>();
        userUpd.put("lastName", "testov");
        userUpd.put("email", "mail@test.ru");
        userUpd.put("password", "my_password");
        given()
                .body(userUpd)
                .when()
                .put("/" + username)
                .then().log().all()
                .body("message", notNullValue());
    }

    @Test
    public void getUserByName() throws JsonProcessingException {
        Specifications.installSpecification(Specifications.requestSpecification(URL),
                Specifications.responseSpecification200());
        given()
                .when()
                .get("/" + username)
                .then().log().all()
                .body("username", equalTo(username))
                .extract().response();
    }

    @Test
    public void deleteUserByName() throws JsonProcessingException {
        Specifications.installSpecification(Specifications.requestSpecification(URL),
                Specifications.responseSpecification200());
        given()
                .when()
                .delete("/" + username)
                .then().log().all()
                .body("message", equalTo(username))
                .extract().response();
    }
}