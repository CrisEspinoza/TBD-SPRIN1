package Taller1db.TBD.Service;

import Taller1db.TBD.Entities.Club;
import Taller1db.TBD.Respository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {

    @Autowired
    private ClubRepository clubRepository ;

    @Override
    public Club findClubById(Long id) {
        return clubRepository.findClubById(id);
    }
}
