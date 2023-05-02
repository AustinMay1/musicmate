package com.musicmate;

import com.musicmate.s3.S3Service;
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

    @Bean
    public CommandLineRunner songs(SongRepository repo, S3Service s3) {
        return (args) -> {
            s3test(s3);

            songtest(repo);
        };
    }

    private static void songtest(SongRepository repo) {
        repo.save(new Song("Savior", "Appeal To Reason", 2008, "Rise Against"));
        repo.save(new Song("God's Plan", "Scorpion", 2018, "Drake"));
        repo.save(new Song("Superman", "The Eminem Show", 2002, "Eminem"));

        log.info("Songs found:");
        log.info("------------");

        for (Song song : repo.findAll()) {
            log.info(song.toString());
        }
    }

    private static void s3test(S3Service s3) {
        s3.uploadObject("musicmate-songs", "foo", "hello world".getBytes());

        byte[] obj = s3.downloadObject("musicmate-songs", "foo");

        log.info(new String(obj));
    }
}
