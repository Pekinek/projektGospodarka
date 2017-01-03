package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(length = 250)
	private String title;
	
	@NotNull
	@Column(length = 250)
	private String user;
	
	@NotNull
	private String email;
	
	@NotNull
	private Long date;
	
	@NotNull
	private String message;

}
