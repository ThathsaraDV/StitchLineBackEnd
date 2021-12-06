package lk.stitchline.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lk.stitchline.dto.UserDTO;
import lk.stitchline.exception.ValidateException;
import lk.stitchline.service.UserService;
import lk.stitchline.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/5/2021
 **/

@RestController
@RequestMapping("/service")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @PostMapping(path = {"/register"}, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserDTO dto){
        if (dto.getUsername().isEmpty()){
            throw new ValidateException("Required fields are empty");
        }
        System.out.println(dto);
        userService.save(dto);

//        Date dt = new Date();
//        LocalDateTime.from(dt.toInstant()).plusDays(1);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withIssuer("Stitch Line")
                .withClaim("username", dto.getUsername())
                .withClaim("password", dto.getPassword())
                .withExpiresAt(dt)
                .sign(algorithm);

        return new ResponseEntity(new StandardResponse("200","Success",dto,token),HttpStatus.CREATED);
    }

    @PostMapping(path = {"/login"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserDTO dto){
        if (dto.getUsername().isEmpty() && dto.getPassword().isEmpty()){
            throw new ValidateException("required fields are empty");
        }
        UserDTO userDTO = userService.findUserByUsernameAndPassword(dto.getUsername(),dto.getPassword());

        if (userDTO != null){
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            dt = c.getTime();

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("Stitch Line")
                    .withClaim("username", dto.getUsername())
                    .withClaim("password", dto.getPassword())
                    .withExpiresAt(dt)
                    .sign(algorithm);
            return new ResponseEntity(new StandardResponse("200","Success",userDTO,token),HttpStatus.OK);
        }

        return new ResponseEntity(new StandardResponse("200","Success",userDTO,null),HttpStatus.OK);
    }

}
