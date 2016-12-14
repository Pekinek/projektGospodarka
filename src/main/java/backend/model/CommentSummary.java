package backend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentSummary {

    private String login;
    private Integer offerId;
    private Long date;
    private String message;

    public CommentSummary(String login, Integer offerId, Long date, String message) {
        this.login = login;
        this.offerId = offerId;
        this.date = date;
        this.message = message;
    }
}
