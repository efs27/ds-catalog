package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Role;
import com.efsaplicativos.dscatalog.entities.User;
import com.efsaplicativos.dscatalog.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDto extends UserDto {

    private String password;

    public UserInsertDto() {
    }

    public UserInsertDto(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = password;
    }

    public UserInsertDto(User user) {
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
