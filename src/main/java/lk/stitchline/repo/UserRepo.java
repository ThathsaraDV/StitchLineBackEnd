package lk.stitchline.repo;

import lk.stitchline.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/4/2021
 **/
public interface UserRepo extends MongoRepository<User,String> {

    boolean existsUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

}
