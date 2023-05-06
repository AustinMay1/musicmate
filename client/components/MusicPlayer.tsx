import { useState, useEffect } from "react";

interface SongDetails {
    id: number;
    title: string;
    artist: string;
    album: string;
    release_date: number;
    fileId: string;
}

function MusicPlayer() {
    const [song, setSong] = useState<string>();
    const [songDetails, setSongDetails] = useState<SongDetails>();

    useEffect(() => {
        async function getSong() {
            const file = await fetch("/api/songs/4/play", {
                headers: {
                    'Access-Control-Allow-Origin': '*',
                }
            }).then((res) => res.blob());

            setSong(URL.createObjectURL(file));
        }
        getSong();
    }, []);

    useEffect(() => {
        async function songDetails() {
            const details = await fetch("/api/songs/4/details").then((res) => res.json())

            console.log(details)
            setSongDetails(details)
        }

        songDetails();
    }, [])

    return (
        <div>
            {songDetails && (
                <div className={"card"}>
                    <p>Title: {songDetails.title}</p>
                    <p>Artist: {songDetails.artist}</p>
                    <p>Release Date: {songDetails.release_date}</p>
                </div>
            )}

            {song && (
            <audio controls>
                <source src={song} type={"audio/mp3"} />
            </audio>
            )}
        </div>
    )
}

export default MusicPlayer;