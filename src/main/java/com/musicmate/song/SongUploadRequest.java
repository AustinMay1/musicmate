package com.musicmate.song;

public record SongUploadRequest(
        String title,
        String album,
        Integer release_date,
        String artist
) { }
