package backend.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Token {

    private String token;
    private String login;

    public Token(){
    }

    public Token(String token, String login){
        this.token = token;
        this.login = login;
    }
}
