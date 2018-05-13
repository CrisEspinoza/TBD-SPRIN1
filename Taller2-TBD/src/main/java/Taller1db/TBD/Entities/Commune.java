package Taller1db.TBD.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Commune")
public class Commune implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commune_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long length;

    @ManyToOne
    @JoinColumn(name="region_id")
    private Region region;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(name="statistics_commune",
            joinColumns={@JoinColumn(name="statistics_id")},
            inverseJoinColumns={@JoinColumn(name="commune_id")})
    private Set<Statistics> statistics =new HashSet<Statistics>();

    public Commune() {
    }

    public Region getRegion() {
        return region;
    }

    public Set<Statistics> getStatistics() {
        return statistics;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setStatistics(Set<Statistics> statistics) {
        this.statistics = statistics;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLength() {
        return length;
    }

}
