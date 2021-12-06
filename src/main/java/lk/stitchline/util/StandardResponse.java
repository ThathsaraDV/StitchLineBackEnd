package lk.stitchline.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/4/2021
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {

    private String code;
    private String message;
    private Object data;
    private String token;

}
