package backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class UserTest {

    @Test
    public void UserToJSON() throws JsonProcessingException {
        User user = new User();
        user.setEmail("pekinhere@gmail.com");
        user.setFirstName("Michał");
        user.setLastName("Mocek");
        user.setLogin("Pekin");
        user.setPassword("xxxxx");
        user.setTelephone("505028626");
        user.setType("normal");
        user.setToken("randomtoken");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(user));
    }
}
