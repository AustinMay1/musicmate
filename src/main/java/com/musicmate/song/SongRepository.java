package com.musicmate.song;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findByTitle(String title);
    Song findById(long id);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Song s SET s.fileId = ?2 WHERE s.id = ?1")
    int updateSongFileId(Integer songId, String fileId);
}
