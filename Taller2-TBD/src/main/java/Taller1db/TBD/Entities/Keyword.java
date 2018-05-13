package Taller1db.TBD.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="Keyword")
public class Keyword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(nullable = false)
    private String name_keyword;

    @ManyToOne
    @JoinColumn(name="club_id_keyword")
    private Club club;

    public Keyword() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName_keyword() {
        return name_keyword;
    }

    public Club getClub() {
        return club;
    }

    public void setName_keyword(String name_keyword) {
        this.name_keyword = name_keyword;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
