package backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserTest {

    @Test
    public void UserToJSON() throws JsonProcessingException {
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setFirstName("Michał");
        user.setLastName("Mocek");
        user.setLogin("Pekin");
        user.setPassword("xxxxx");
        user.setTelephone("123456789");
        user.setType("normal");
        user.setToken("randomtoken");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        assertThat(json, is("{\"login\":\"Pekin\",\"token\":\"randomtoken\",\"password\":\"xxxxx\",\"email\":\"email@gmail.com\",\"telephone\":\"123456789\",\"firstName\":\"Michał\",\"lastName\":\"Mocek\",\"type\":\"normal\"}"));
    }
}
