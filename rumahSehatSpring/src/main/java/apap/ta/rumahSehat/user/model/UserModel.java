package apap.ta.rumahSehat.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonIgnore
    private String id;

    @NotNull
    @Column(name = "nama")
    private String nama;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private RoleEnum role;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;
}
