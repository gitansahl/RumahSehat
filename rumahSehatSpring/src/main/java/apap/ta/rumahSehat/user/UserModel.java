package apap.ta.rumahSehat.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserModel {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Column(name = "nama")
    private String nama;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Lob
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;
}
