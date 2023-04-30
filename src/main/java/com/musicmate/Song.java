package com.musicmate;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Song {
    @Id
    @SequenceGenerator(
            name = "song_id_sequence",
            sequenceName = "song_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "song_id_sequence"
    )
    private Integer id;
    private String title;
    private String album;
    private Integer release_date;
    private String artist;

    public Song(Integer id, String title, String album, Integer release_date, String artist) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.release_date = release_date;
        this.artist = artist;
    }

    public Song() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(album, song.album) && Objects.equals(release_date, song.release_date) && Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, album, release_date, artist);
    }
}
