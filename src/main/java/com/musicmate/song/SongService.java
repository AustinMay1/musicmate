package com.musicmate.song;

import com.musicmate.s3.S3Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SongService {
    private final SongRepository repository;
    private final S3Service S3;
    private final SongDAO SONGDAO;

    public SongService(SongRepository repository, S3Service S3, @Qualifier("jpa") SongDAO SONGDAO) {
        this.repository = repository;
        this.S3 = S3;
        this.SONGDAO = SONGDAO;
    }

    List<Song> findSongs() {
        List<Song> songs = new ArrayList<>();

        for (Song song : repository.findAll()) {
            songs.add(song);
        }

        return songs;
    }

    Song findById(Integer id) {
        return repository.findById(id);
    }

    List<Song> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void uploadSong(Integer songId, MultipartFile file) {
        // @TODO check if songId exists

        String fileId = UUID.randomUUID().toString();

        try {
            S3.uploadObject("musicmate-songs", "song-%s/%s".formatted(songId, fileId), file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload song", e);
        }

        repository.updateSongFileId(songId, fileId);
    }

    public byte[] downloadSong(Integer songId) {
        var song = repository.findById(songId);

        return S3.downloadObject("musicmate-songs",
                "song-%d/%s".formatted(songId, song.getFileId()));
    }

    public void addSong(SongUploadRequest songUploadRequest) {
        // @TODO: Check if song already exists by id? title? combination?

        Song song = new Song(
                songUploadRequest.title(),
                songUploadRequest.album(),
                songUploadRequest.release_date(),
                songUploadRequest.artist()
        );

        SONGDAO.insertSong(song);
    }
}
