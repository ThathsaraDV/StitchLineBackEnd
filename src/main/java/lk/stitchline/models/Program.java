package lk.stitchline.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/
@Document("program")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Program {

    @Id
    private String pid;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;

}
