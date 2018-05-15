package Taller1db.TBD.Rest;

import Taller1db.TBD.Entities.Club;
import Taller1db.TBD.Entities.Statistics;
import Taller1db.TBD.Respository.ClubRepository;
import Taller1db.TBD.Respository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/club")
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

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
        Long clubId = id.longValue();
        return clubRepository.findClubById(clubId);
    }

    /*
     */

}
