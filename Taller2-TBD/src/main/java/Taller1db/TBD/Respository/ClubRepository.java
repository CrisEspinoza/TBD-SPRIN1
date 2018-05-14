package Taller1db.TBD.Respository;

import Taller1db.TBD.Entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Club findClubById(Long id);
}

