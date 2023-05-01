package com.musicmate.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/songs")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.findSongs();
    }

    @GetMapping(path = "{title}")
    public List<Song> getByTitle(@PathVariable("title") String title) {
        return songService.findByTitle(title);
    }
}
