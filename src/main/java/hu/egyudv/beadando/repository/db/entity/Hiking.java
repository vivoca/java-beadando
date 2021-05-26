package hu.egyudv.beadando.repository.db.entity;

import hu.egyudv.beadando.model.Difficulty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Hiking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "length")
    private Double length;

    @Column(name = "birth_date")
    private String difficulty;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Difficulty getDifficulty() {
        return Difficulty.getByLabel(difficulty);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty.label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
