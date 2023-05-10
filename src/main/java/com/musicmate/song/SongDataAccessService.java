package com.musicmate.song;

import org.springframework.stereotype.Repository;

@Repository("jpa")
public class SongDataAccessService implements SongDAO{
    private final SongRepository repository;

    public SongDataAccessService(SongRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertSong(Song song) {
        repository.save(song);
    }
}
