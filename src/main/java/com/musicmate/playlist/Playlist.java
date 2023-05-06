package com.musicmate.playlist;

import com.musicmate.song.Song;
import com.musicmate.user.User;

public class Playlist <T> {
    private class SongNode<T> {
        private T song;
        private SongNode<T> next;
        private SongNode<T> prev;

        public SongNode(T song, SongNode<T> next, SongNode<T> prev) {
            this.song = song;
            this.next = next;
            this.prev = prev;
        }

        public T getCurrentSong() {
            return this.song;
        }

        public SongNode<T> getNextSong() {
            return this.next;
        }

        public SongNode<T> getPrevSong() {
            return this.prev;
        }

        public void setNext(SongNode<T> next) {
            this.next = next;
        }

        public void setPrev(SongNode<T> prev) {
            this.prev = prev;
        }
    } // end SongNode class

    private String name;
    private User userId;
    private SongNode<T> header;
    private SongNode<T> trailer;
    private Integer size = 0;

    public Playlist(String name, User userId) {
        this.name = name;
        this.userId = userId;
        this.header = new SongNode<>(null, null, null); // header sentinel
        this.trailer = new SongNode<>(null, null, header);    // trailer sentinel
        header.setNext(trailer);
    }

    private void add(T song, SongNode<T> nextSong, SongNode<T> prevSong) {
        SongNode<T> newSong = new SongNode<>(song, nextSong, prevSong);

        prevSong.setNext(newSong);
        nextSong.setPrev(newSong);

        size++;
    }

    public void addSong(T song) {
        add(song, header.getNextSong(), header);
    }

    // @TODO: add functionality for next/prev song
}
