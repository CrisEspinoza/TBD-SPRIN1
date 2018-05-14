package Taller1db.TBD.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Statistics")
public class Statistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Long id;

    @Column(nullable = false)
    private String name_statics;

    @Column(nullable = false)
    private int positive_value;

    @Column(nullable = false)
    private int negative_value;

    @Column(nullable = false)

    private int neutro_value;

    @Column(nullable = false)
    private Club name;

   // @Column(nullable = false)
    //private String classification;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="club_id_statics")
    private Club club;

    @ManyToMany(mappedBy = "statistics")
    @JsonIgnore
    private Set<Commune> communes = new HashSet<Commune>();

    public Statistics() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName_statics(String name_statics) {
        this.name_statics = name_statics;
    }

    public void setPositive_value(int positive_value) {
        this.positive_value = positive_value;
    }

    public void setNegative_value(int negative_value) {
        this.negative_value = negative_value;
    }

    public void setNeutro_value(int neutro_value) {
        this.neutro_value = neutro_value;
    }

    //public void setClassification(String classification) {this.classification = classification;}

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Club getName() {
        return name;
    }

    public Set<Commune> getCommunes() {
        return communes;
    }

    public Club getClub() {
        return club;
    }

    public void setName(Club name) {
        this.name = name;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    public Long getId() {
        return id;
    }

    public String getName_statics() {
        return name_statics;
    }

    public int getPositive_value() {
        return positive_value;
    }

    public int getNegative_value() {
        return negative_value;
    }

    public int getNeutro_value() {
        return neutro_value;
    }

   //public String getClassification() {return classification;}

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}
