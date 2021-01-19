package fhict.rest.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @NotEmpty(message="Name cannot be null")
    @Size(min=2, message="Name must be atleast 2 characters")
    @Column(name = "Username")
    private String username;

    @NotEmpty
    @Size(min=2, message="Password must be atleast 2 characters")
    @Column(name="Password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
