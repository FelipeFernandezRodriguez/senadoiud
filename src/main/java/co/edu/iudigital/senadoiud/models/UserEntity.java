package co.edu.iudigital.senadoiud.models;

import co.edu.iudigital.senadoiud.enums.DepartmentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "email", unique = true, nullable = false, updatable = false)
    String email;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "last_name", nullable = false, length = 100)
    String lastName;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name="department", nullable = false)
    @Enumerated(EnumType.STRING)
    DepartmentType departmentType;

    @ManyToOne
    @JoinColumn(name = "political_parties_id", nullable = false)
    PoliticalPartie politicalPartie;

    @Column(name = "enabled", nullable = false)
    Boolean enabled;

    @Column
    String image;

    @ManyToOne
    @JoinColumn(name = "roles_id", nullable = false)
    Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Vote> votes;
}
