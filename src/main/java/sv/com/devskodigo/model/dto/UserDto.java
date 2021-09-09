package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    public UserDto(int userId, String userFirstName, String userLastName, String userAccountName, String userPassword, int userStatus){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAccountName = userAccountName;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
    }

    public UserDto(){}

    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userAccountName;
    private String userPassword;
    private int userStatus;

}
