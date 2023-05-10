import {useState, useEffect} from "react";
import {SongDetails} from "../../interfaces/Interface.ts";
import MusicPlayer from "../MusicPlayer.tsx";

import './Explorer.css';

function Explorer() {
    const [songs, setSongs] = useState<SongDetails[]>([]);
    const [currentSong, setCurrentSong] = useState<number>();
    async function playSong(songId: number) {
        console.log(songId)
        return setCurrentSong(songId);
    }

    useEffect(() => {
        async function getSongs() {
            const song = await fetch('/api/songs/view/all').then((res) => res.json())

            return setSongs(song);
        }

        getSongs();
    }, [])

    return (
        <div>
            <h1>Songs</h1>
            {songs.map(song => (
                <div className={"songcard"} key={song.id}>
                    <button onClick={() => playSong(song.id)}>Play</button>
                    <h4>{song.title}</h4>
                    <p>{song.artist}</p>
                    <p>{song.release_date}</p>
                </div>
            ))}

            <MusicPlayer songId={currentSong} />
        </div>
    )
}

export default Explorer;