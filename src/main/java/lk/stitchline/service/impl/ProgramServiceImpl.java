package lk.stitchline.service.impl;

import lk.stitchline.dto.ProgramDTO;
import lk.stitchline.exception.NotFoundException;
import lk.stitchline.exception.ValidateException;
import lk.stitchline.models.Program;
import lk.stitchline.repo.ProgramRepo;
import lk.stitchline.service.ProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 12/6/2021
 **/

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void save(ProgramDTO dto) {
        if (programRepo.existsById(dto.getPid())){
            throw new ValidateException("ID already exist");
        }
        programRepo.save(mapper.map(dto,Program.class));
    }

    @Override
    public void update(ProgramDTO dto) {
        if (programRepo.existsById(dto.getPid())){
            programRepo.save(mapper.map(dto,Program.class));
        }else {
            throw new NotFoundException("ID does not exist");
        }

    }
}
