package backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class OfferTest {

    @Test
    public void offerToJSON() throws JsonProcessingException {
        Offer offer = new Offer();
        offer.setUser(new User());
        offer.setType("type1");
        offer.setDate("today");
        offer.setDescription("opisopis");
        offer.setPictures(new byte[]{2, 33, 11, 54, 122, 76, 44});
        offer.setPlace("wrocław");
        offer.setPrice("123");
        offer.setPurpose("wynajem");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(offer);
        System.out.println(json);
    }
}
