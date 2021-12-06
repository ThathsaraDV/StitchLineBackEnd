package lk.stitchline.service.impl;

import lk.stitchline.dto.UserDTO;
import lk.stitchline.exception.ValidateException;
import lk.stitchline.models.User;
import lk.stitchline.repo.UserRepo;
import lk.stitchline.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/5/2021
 **/

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void save(UserDTO dto) {
        if (userRepo.existsUserByUsername(dto.getUsername())){
            throw new ValidateException("Username already exist");
        }
        userRepo.save(mapper.map(dto,User.class));
    }

    @Override
    public boolean searchByUsername(String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public UserDTO findUserByUsernameAndPassword(String username, String password) {
        User user = userRepo.findUserByUsernameAndPassword(username, password);
        return mapper.map(user,UserDTO.class);
    }

}
