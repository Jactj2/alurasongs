package com.example.aluraSongs;

import com.example.aluraSongs.main.AppMain;
import com.example.aluraSongs.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraSongsApplication implements CommandLineRunner {
	@Autowired
	private SingerRepository singerRepository;

	public static void main(String[] args) {SpringApplication.run(AluraSongsApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
		AppMain app = new AppMain(singerRepository);
		app.mainLoop();
	}
}
