package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDto {

    private Long id;
    @NotBlank(message = "Campo nome obrigatório")
    private String firstName;
    private String lastName;
    @Email(message = "Entre com um email válido")
    private String email;

    private Set<RoleDto> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        user.getRoles().forEach(role -> roles.add(new RoleDto(role)));
       /* for (Role role : user.getRoles()) {
            roles.add(new RoleDto(role));
        }*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
