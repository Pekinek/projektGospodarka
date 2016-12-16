package backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Offer {

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy="favouriteOffers")
    private Set<User> userFavourite = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private Set<Comment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Column (length = 100)
    private String title;
    @NotNull
    @Column (length = 500)
    private String description;
    @NotNull
    private String type;
    @NotNull
    private String place;
    @NotNull
    private String purpose;
    @NotNull
    private String price;
    private Long date;
    private Boolean archived;
    
    @Column( length = 1000000000 )
    private byte[] pictures;

}
