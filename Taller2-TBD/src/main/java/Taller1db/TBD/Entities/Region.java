package Taller1db.TBD.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Region")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long length;

    @OneToMany(mappedBy="region")
    private Set<Commune> Commune;

    public Region() {
    }

    public Set<Taller1db.TBD.Entities.Commune> getCommune() {
        return Commune;
    }

    public void setCommune(Set<Taller1db.TBD.Entities.Commune> commune) {
        Commune = commune;
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
