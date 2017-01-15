package backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Setter
@Getter
public class User {

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Offer> offers = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    private Set<Offer> favouriteOffers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @Id
    @NotNull
    @Size(min=1, max=30)
    private String login;
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String token;
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(length = 1000)
    private String password;
    @NotNull
    @Size(min=1, max=50)
    private String email;
    @NotNull
    @Size(min=6, max=15)
    private String telephone;
    @NotNull
    @Size(min=1, max=50)
    private String firstName;
    @NotNull
    @Size(min=1, max=50)
    private String lastName;
    @NotNull
    private String type;
    @NotNull
    private Boolean enabled;

    public Contact toContact(){
        return new Contact(login, email, telephone, firstName, lastName, enabled);
    }
}
