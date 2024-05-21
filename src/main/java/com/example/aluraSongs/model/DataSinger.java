package com.example.aluraSongs.model;

public record DataSinger(
        String name,
        String musicalGroup,
        Integer yearStarterSing
) {

    public DataSinger() {
        this("","",0);
    }

    public DataSinger setName(String name){
        return new DataSinger(name,this.musicalGroup,this.yearStarterSing);
    }
    public DataSinger setMusicalGroup(String musicalGroup){
        return new DataSinger(this.name,musicalGroup,this.yearStarterSing);
    }
    public DataSinger setYearsStartingSing(Integer yearStarterSing){
        return new DataSinger(this.name,this.musicalGroup,yearStarterSing);
    }
}
