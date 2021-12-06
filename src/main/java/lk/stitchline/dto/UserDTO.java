package lk.stitchline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/4/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String name;
    private String username;
    private String password;
    private int mobile_number;
    private Date date_of_birth;
    private String gender;
    private String language;

}
