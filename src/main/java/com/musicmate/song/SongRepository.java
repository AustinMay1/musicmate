package com.musicmate.song;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByTitle(String title);

    Song findById(long id);
}
