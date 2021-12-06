package lk.stitchline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramDTO {

    private String pid;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;

}
