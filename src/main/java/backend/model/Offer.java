package backend.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Size(min=1, max=100)
    private String title;
    @NotNull
    @Column (length = 500)
    @Size(min=1, max=500)
    private String description;
    @NotNull
    private String type;
    @NotNull
    @Size(min=1, max=50)
    private String place;
    @NotNull
    @Size(min=1, max=50)
    private String purpose;
    @NotNull
    @Min(0)
    private Double price;
    private Long date;
    private Boolean archived;
    
    @Column( length = 1000000000 )
    private byte[] pictures;

    public Contact getContact(){
        return user.toContact();
    }
}
