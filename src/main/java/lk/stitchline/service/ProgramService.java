package lk.stitchline.service;

import lk.stitchline.dto.ProgramDTO;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/
public interface ProgramService {

    void save(ProgramDTO dto);

    void update(ProgramDTO dto);

}
