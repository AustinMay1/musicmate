package com.musicmate.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/songs")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(path = "/view/all")
    public List<Song> getAllSongs() {
        return songService.findSongs();
    }

    @GetMapping(path = "{title}/view")
    public List<Song> getByTitle(@PathVariable("title") String title) {
        return songService.findByTitle(title);
    }

    @PostMapping(
            value = "{songId}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadSongFile(@PathVariable Integer songId, @RequestParam("file")MultipartFile file) {
        songService.uploadSong(songId, file);
    }

    @GetMapping(path = "{songId}/play")
    public byte[] playSongFile(@PathVariable Integer songId) {
        return songService.downloadSong(songId);
    }
}
