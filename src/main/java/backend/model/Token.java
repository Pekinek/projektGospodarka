package backend.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Token {

    private String token;

    public Token(){
    }

    public Token(String token){
        this.token = token;
    }
}
