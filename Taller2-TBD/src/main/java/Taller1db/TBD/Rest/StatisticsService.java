//package Taller1db.TBD.Rest;
//
//import Taller1db.TBD.Entities.Statistics;
//import Taller1db.TBD.Respository.StatisticsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/statics")
//public class StatisticsService {
//
//    @Autowired
//    private StatisticsRepository statisticsRepository;
//
//    // retorna todos los club de la base de datos club, se llama con la ruta /club
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public Iterable<Statistics> getAllStatitics() {
//        return statisticsRepository.findAll();
//    }
//
//    // retorna un determinado club segun la id entregada, se llama con la ruta /club/id
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Statistics getStatic(@PathVariable Integer id) {
//        Long staticId = id.longValue();
//        return statisticsRepository.findStatisticsById(staticId);
//    }
//}
