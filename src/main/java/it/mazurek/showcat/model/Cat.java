package it.mazurek.showcat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Map;
import java.util.Set;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false)
    private String owner;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonIgnore
    private Breed breed;
    @ElementCollection
    @Column(nullable = false)
    @JsonIgnore
    private Set<Long> tickets;

    @ElementCollection
    @CollectionTable(name = "judge_points_mapping",
            joinColumns = {@JoinColumn(name = "cat_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "judgeId")
    @Column(name = "points", nullable = false)
    @JsonIgnore
    private Map<Long, Integer> judgePointsMap;


    public Cat() {
    }

    @JsonIgnore
    public int getTicketsCount() {
        return tickets.size();
    }

    @JsonIgnore
    public int getPointsFromJudges() {
        return getJudgePointsMap().values().stream().mapToInt(Integer::intValue).sum();
    }

    @JsonIgnore
    public String getFullName() {
        return name + " * " + owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Set<Long> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Long> tickets) {
        this.tickets = tickets;
    }

    public Map<Long, Integer> getJudgePointsMap() {
        return judgePointsMap;
    }

    public void setJudgePointsMap(Map<Long, Integer> judgePointsMap) {
        this.judgePointsMap = judgePointsMap;
    }

}
