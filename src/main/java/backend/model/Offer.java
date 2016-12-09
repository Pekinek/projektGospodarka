package backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Offer {

    @JsonIgnore
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String type;
    @NotNull
    private String place;
    @NotNull
    private String purpose;
    @NotNull
    private String price;
    private String date;
    
    @Column( length = 100000000 )
    private byte[] pictures;

}
