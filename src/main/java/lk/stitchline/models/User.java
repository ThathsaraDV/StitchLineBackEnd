package lk.stitchline.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/4/2021
 **/

@Document("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private String username;
    private String password;
    private int mobile_number;
    private Date date_of_birth;
    private String gender;
    private String language;


}
