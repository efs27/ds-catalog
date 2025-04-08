package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Role;
import com.efsaplicativos.dscatalog.entities.User;
import com.efsaplicativos.dscatalog.validations.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDto extends UserDto {

    private String password;

    public UserUpdateDto() {
    }

    public UserUpdateDto(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = password;
    }

    public UserUpdateDto(User user) {
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        for (Role role : user.getRoles()) {
            this.getRoles().add(new RoleDto(role));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
