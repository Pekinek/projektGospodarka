package backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
    private String login;
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String token;
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String telephone;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String type;
    @NotNull
    private Boolean enabled;

    public Contact toContact(){
        return new Contact(login, email, telephone, firstName, lastName, enabled);
    }
}
