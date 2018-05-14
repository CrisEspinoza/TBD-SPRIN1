package Taller1db.TBD.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="Club")
public class Club implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int trophies;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @OneToMany(mappedBy="club")
    @JsonIgnore
    private Set<Statistics> statistics;

    @OneToMany(mappedBy="club")
    @JsonIgnore
    private Set<Keyword> keywords;

    public Club() {
    }

    public Set<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(Set<Statistics> statistics) {
        this.statistics = statistics;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getTrophies() {
        return trophies;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}
