package com.example.aluraSongs.repository;

import com.example.aluraSongs.model.Singer;
import com.example.aluraSongs.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SingerRepository extends JpaRepository<Singer,Long> {

   Optional<List<Singer>>findByNameContainsIgnoreCase(String query);


   Optional<Singer> findFirstByNameContainsIgnoreCase(String query);

   @Query("SELECT songs FROM Singer singer JOIN singer.listOfSongs songs WHERE singer= :querySinger")
   Optional<List<Songs>> getAllSingerSongs(Singer querySinger);
}
