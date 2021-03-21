package moneyhelper.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User implements org.springframework.security.core.userdetails.UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email не может быть пустым!")
    @Size(min = 10, message = "Email должен быть больше 10 символов!")
    @Email(message = "Должен иметь шаблон почты!")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым!")
    @Size(min = 6, message = "Пароль должен быть больше 6 символов!")
    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "t_user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private UserDetails details;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private UserFinance finance;


    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                ", details=" + details +
                ", finance=" + finance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && status == user.status && Objects.equals(details, user.details) && Objects.equals(finance, user.finance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, roles, status, details, finance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public UserFinance getFinance() {
        return finance;
    }

    public void setFinance(UserFinance finance) {
        this.finance = finance;
    }


}