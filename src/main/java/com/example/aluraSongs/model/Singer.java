package com.example.aluraSongs.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "info_singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String name;
    private String musicalGroup;
    private Integer yearStarterSing;

    @OneToMany(mappedBy = "singer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Songs> listOfSongs;

    public Singer() { }

    public Singer(DataSinger dataSinger) {
        this.name = dataSinger.name();
        this.musicalGroup = dataSinger.musicalGroup();
        this.yearStarterSing = dataSinger.yearStarterSing();
    }

    public Singer(String name, String musicalGroup, Integer yearStarterSing) {
        this.name = name;
        this.musicalGroup = musicalGroup;
        this.yearStarterSing = yearStarterSing;
    }

    @Override
    public String toString() {
        return "Cantante: " + name + " Grupo Musical: " + musicalGroup + " Año de Inicio: " + yearStarterSing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicalGroup() {
        return musicalGroup;
    }

    public void setMusicalGroup(String musicalGroup) {
        this.musicalGroup = musicalGroup;
    }

    public Integer getYearStarterSing() {
        return yearStarterSing;
    }

    public void setYearStarterSing(Integer yearStarterSing) {
        this.yearStarterSing = yearStarterSing;
    }

    public List<Songs> getListOfSongs() {
        return listOfSongs;
    }

    public void setListOfSongs(List<Songs> listOfSongs) {
        listOfSongs.forEach(songs -> songs.setSinger(this));
        this.listOfSongs = listOfSongs;
    }
}
