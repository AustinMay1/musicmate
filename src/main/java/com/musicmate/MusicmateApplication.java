package com.musicmate;

import com.musicmate.song.Song;
import com.musicmate.song.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicmateApplication {
    private static final Logger log = LoggerFactory.getLogger(MusicmateApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MusicmateApplication.class, args);
    }

    /**
     * @param SongRepository
     *
     * @return creates a few songs upon application start and logs them
     *
     * */
    @Bean
    public CommandLineRunner songs(SongRepository repo) {
        return (args) -> {
            repo.save(new Song("Savior", "Appeal To Reason",2008, "Rise Against"));
            repo.save(new Song("God's Plan", "Scorpion", 2018, "Drake"));
            repo.save(new Song("Superman", "The Eminem Show", 2002, "Eminem"));

            log.info("Songs found:");
            log.info("------------");

            for(Song song : repo.findAll()) {
                log.info(song.toString());
            }
        };
    }
}
