package lk.stitchline.service;

import lk.stitchline.dto.UserDTO;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/5/2021
 **/
public interface UserService {

    void save(UserDTO dto);

    boolean searchByUsername(String username);

    UserDTO findUserByUsernameAndPassword(String username, String password);

}
