package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String accountName;
    private String password;
    private int status;

}
