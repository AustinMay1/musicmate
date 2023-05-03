package com.musicmate.song;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Song {
    @Id
    @SequenceGenerator(name = "song_id_sequence", sequenceName = "song_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_id_sequence")
    private Long id;
    private String title;
    private String album;
    private Integer release_date;
    private String artist;
    private String fileId;

    protected Song() {
    }

    public Song(String title, String album, Integer release_date, String artist, String fileId) {
        this.title = title;
        this.album = album;
        this.release_date = release_date;
        this.artist = artist;
        this.fileId = fileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Integer release_date) {
        this.release_date = release_date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) && Objects.equals(title, song.title) && Objects.equals(album, song.album) && Objects.equals(release_date, song.release_date) && Objects.equals(artist, song.artist) && Objects.equals(fileId, song.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, album, release_date, artist, fileId);
    }

    @Override
    public String toString() {
        return String.format(
                "Song[id='%d', title='%s', artist='%s', album='%s', release_date='%d', fileid='%s']",
                id, title, artist, album, release_date, fileId);
    }
}
