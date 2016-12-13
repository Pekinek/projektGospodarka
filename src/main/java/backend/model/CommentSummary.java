package backend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentSummary {

    private String login;
    private Integer offerId;
    private String date;
    private String message;

    public CommentSummary(String login, Integer offerId, String date, String message) {
        this.login = login;
        this.offerId = offerId;
        this.date = date;
        this.message = message;
    }
}
