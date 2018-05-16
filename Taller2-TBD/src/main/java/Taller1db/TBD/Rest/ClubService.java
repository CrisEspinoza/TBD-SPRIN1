package Taller1db.TBD.Rest;

import Taller1db.TBD.Entities.Club;
import Taller1db.TBD.Entities.Statistics;
import Taller1db.TBD.Respository.ClubRepository;
import Taller1db.TBD.Respository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    // retorna todos los club de la base de datos club, se llama con la ruta /club
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Club> getAllClub() {
        return clubRepository.findAll();
    }

    // retorna un determinado club segun la id entregada, se llama con la ruta /club/id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Club getClub(@PathVariable Integer id) {
        Long staticId = id.longValue();
        return clubRepository.findClubById(staticId);
    }
}
