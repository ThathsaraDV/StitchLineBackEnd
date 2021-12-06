package lk.stitchline.repo;

import lk.stitchline.models.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/
public interface ProgramRepo extends MongoRepository<Program,String> {

}
