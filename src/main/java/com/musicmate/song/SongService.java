package com.musicmate.song;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    List<Song> findSongs() {
        List<Song> songs = new ArrayList<>();

        for(Song song : repository.findAll()) {
            songs.add(song);
        }

        return songs;
    }

    List<Song> findByTitle(String title) {
        return repository.findByTitle(title);
    }
}
