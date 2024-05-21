package com.example.aluraSongs.main;

import com.example.aluraSongs.model.Singer;
import com.example.aluraSongs.model.Songs;
import com.example.aluraSongs.repository.SingerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AppMain {

    private final Scanner keyboard = new Scanner(System.in);
    private final SingerRepository repository;

    public AppMain(SingerRepository repository) {
        this.repository = repository;
    }

    public void mainLoop() {
        Boolean continueLoop;
        System.out.println(Messages.APP_WELCOME);

        do {
            continueLoop = menuPrincipal();
        }
        while (continueLoop);

        System.out.println(Messages.APP_CLOSE);

    }
    public Boolean menuPrincipal(){
        boolean continueLoop = true;
        System.out.println(Messages.MENU_OPTIONS);
        var option = keyboard.nextInt();
        keyboard.nextLine();

        switch (option){
            case 1:
                addSinger();
                break;
            case 2:
                showAllSingers();
                break;
            case 3:
                try {
                    addSong();
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                try {
                    getSingerSongs();
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }

                break;
            case 0:
                continueLoop = false;
                break;
            default:

        }
        return continueLoop;
    }

    private void addSinger(){
        System.out.println("Agregue el nombre del cantante");
        var nameSinger = keyboard.nextLine();
        System.out.println("Agregue el nombre de la banda musical");
        var musicalGroup = keyboard.nextLine();
        System.out.println("Agregue el año de inicio");
        var yearStarting = keyboard.nextInt();
        keyboard.nextLine();
        repository.save(new Singer(nameSinger,musicalGroup,yearStarting));
        System.out.println("-------------\n");
    }

    private List<Singer> getAllSingers(){
        return repository.findAll();
    }

    private void showAllSingers() {
        var allSingers = getAllSingers();
        allSingers.forEach(System.out::println);
        System.out.println("-------------\n");
    }


    private void addSong(){
        Singer singer = selectSinger();
        keyboard.nextLine();
        System.out.println("Agregue el nombre de la cancion");
        var nameSong = keyboard.nextLine();
        System.out.println("Agregue el nombre del album");
        var nameAlbum = keyboard.nextLine();
        System.out.println("Agregue el año que salio la cancion");
        var yearStarting = keyboard.nextInt();
        keyboard.nextLine();
        Songs newSong = new Songs(nameSong,nameAlbum,yearStarting);
        var dataSongs = singer.getListOfSongs();
        dataSongs.add(newSong);
        singer.setListOfSongs(dataSongs);
        repository.save(singer);
        System.out.println("-------------\n");
    }

    private Singer showOptionSingerList (List<Singer> allSingersData){

        if(allSingersData.isEmpty()){
            throw new IllegalArgumentException("No se encontro a un cantante con ese nombre");
        }
        AtomicInteger optionNumber = new AtomicInteger(1);
        System.out.println("Seleccione un numero de cantante");

        allSingersData.forEach(singer -> {
            System.out.println(optionNumber.getAndIncrement() + " - " + singer.getName());
        });

        var selection = keyboard.nextInt();
        try{
            return allSingersData.get(selection-1);
        } catch (Exception e){
            throw new IllegalArgumentException("Se eligio una opcion Incorrecta");
        }

    }

    private Singer selectSinger() {
        System.out.println(Messages.SINGER_OPTIONS);
        Singer singer;
        var selection = keyboard.nextInt();
        keyboard.nextLine();
        switch (selection){
            case 1:
                singer = showOptionSingerList(getAllSingers());
                break;
            case 2:
                singer = searchAllNameSinger();
                break;
            case 3:
                    singer = searchOnlySinger();
                break;
            default:
                throw new IllegalArgumentException("Se eligio una opcion Incorrecta");
        }

        return singer;
    }

    private Optional<List<Singer>> searchNameSinger(){
        System.out.println("Tecle el nombre del artista");
        var query = keyboard.nextLine();
        return repository.findByNameContainsIgnoreCase(query);
    }

    private Singer searchAllNameSinger(){
        var listSearch = searchNameSinger();
        if(listSearch.isEmpty()){throw new  IllegalArgumentException();}
        return showOptionSingerList(listSearch.get());
    }

    private Singer searchOnlySinger() {
        System.out.println("Tecle el nombre del artista");
        var query = keyboard.nextLine();
        var results = repository.findFirstByNameContainsIgnoreCase(query);
        if(results.isEmpty()){
            System.out.println("No se encontro al artista");
            throw new IllegalArgumentException();
        }
        System.out.println( "El artista encontrado es: " + results.get().getName());
        System.out.println(Messages.CORRECT_OPTIONS);
        if(keyboard.nextInt() != 1 ) { throw new IllegalArgumentException();}

        return results.get();
    }

    private void getSingerSongs(){
        Singer singer = selectSinger();
        Optional<List<Songs>> listSongs = repository.getAllSingerSongs(singer);
        System.out.println(singer.getName());
        System.out.println("--------------");
        if (listSongs.get().isEmpty() ) {
            System.out.println("No hay canciones registradas\n");
            return;
        }

        listSongs.get().forEach(System.out::println);
    }
}
