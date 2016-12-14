package backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private String login;
    private String email;
    private String telephone;
    private String firstName;
    private String lastName;

    public Contact() {
        super();
    }
    
    public Contact(String login, String email, String telephone, String firstName, String lastName) {
        this.login = login;
        this.email = email;
        this.telephone = telephone;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
