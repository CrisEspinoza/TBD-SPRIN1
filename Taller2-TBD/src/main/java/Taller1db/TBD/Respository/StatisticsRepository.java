package Taller1db.TBD.Respository;

import Taller1db.TBD.Entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics,Long> {

    Statistics findStatisticsById (Long id);
    //List<Statistics> findByOrOrderByLastUpdateDesc();
//    @Query("select s from Statistics s order by s.last_update desc")
//    List<Statistics> findBydate();
}
