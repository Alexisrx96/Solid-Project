package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String accountName;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private int status;

}
