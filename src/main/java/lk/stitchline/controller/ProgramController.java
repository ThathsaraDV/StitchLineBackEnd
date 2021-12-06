package lk.stitchline.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lk.stitchline.dto.ProgramDTO;
import lk.stitchline.dto.UserDTO;
import lk.stitchline.exception.NotFoundException;
import lk.stitchline.exception.ValidateException;
import lk.stitchline.service.ProgramService;
import lk.stitchline.service.UserService;
import lk.stitchline.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/

@RestController
@RequestMapping("/content/programs")
@CrossOrigin
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @PostMapping(path = {"/create"}, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerProgram(@RequestBody ProgramDTO dto, @RequestHeader("Authorization") String token){
        if (dto.getPid().isEmpty()){
            throw new ValidateException("Required fields are empty");
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Stitch Line")
                    .build();

            DecodedJWT decodedToken = verifier.verify(token);
            String username = String.valueOf(decodedToken.getClaims().get("username"));

            if (userService.searchByUsername(username)){

                programService.save(dto);

                return new ResponseEntity(new StandardResponse("200","Success",dto,null),HttpStatus.CREATED);
            }else {
                return new ResponseEntity(new StandardResponse("400","Unauthorized access",dto,null),HttpStatus.CREATED);
            }


        } catch (JWTVerificationException ex) {
            return new ResponseEntity(new StandardResponse("500",ex.getMessage(),dto,null),HttpStatus.CREATED);
        }


    }

    @PostMapping(path = {"/update"}, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProgram(@RequestBody ProgramDTO dto, @RequestHeader("Authorization") String token){
        if (dto.getPid().isEmpty()){
            throw new NotFoundException("No Program by this ID");
        }

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Stitch Line")
                    .build();

            DecodedJWT decodedToken = verifier.verify(token);
            String username = String.valueOf(decodedToken.getClaims().get("username"));

            if (userService.searchByUsername(username)){

                programService.update(dto);

                return new ResponseEntity(new StandardResponse("200","Success",dto,null),HttpStatus.CREATED);
            }else {
                return new ResponseEntity(new StandardResponse("400","Unauthorized access",dto,null),HttpStatus.CREATED);
            }


        } catch (JWTVerificationException ex) {
            return new ResponseEntity(new StandardResponse("500",ex.getMessage(),dto,null),HttpStatus.CREATED);
        }

    }

}
