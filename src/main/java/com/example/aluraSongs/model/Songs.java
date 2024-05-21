package com.example.aluraSongs.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String nameOfSong;
    private String albumName;
    private Integer yearOfSong;

    @ManyToOne
    private Singer singer;

    public Songs() {
    }

    public Songs(String nameOfSong, String albumName, Integer yearOfSong) {
        this.nameOfSong = nameOfSong;
        this.albumName = albumName;
        this.yearOfSong = yearOfSong;
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        this.nameOfSong = nameOfSong;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getYearOfSong() {
        return yearOfSong;
    }

    public void setYearOfSong(Integer yearOfSong) {
        this.yearOfSong = yearOfSong;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return nameOfSong + " del Album: " + albumName + " lanzada en el año: " + yearOfSong;
    }
}
