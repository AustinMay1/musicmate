import { useState, useEffect } from "react";
import {SongDetails} from "../interfaces/Interface.ts";

import './MusicPlayer.css';

// @ts-ignore
function MusicPlayer({ songId }) {
    const [song, setSong] = useState<string>();
    const [songDetails, setSongDetails] = useState<SongDetails>();

    useEffect(() => {
        async function getSong() {
            const file = await fetch(`/api/songs/${songId}/play`, {
                headers: {
                    'Access-Control-Allow-Origin': '*',
                }
            }).then((res) => res.blob());

            setSong(URL.createObjectURL(file));
        }
        getSong();
    }, [songId]);

    useEffect(() => {
        async function songDetails() {
            const details = await fetch(`/api/songs/${songId}/details`).then((res) => res.json())

            console.log(details)
            setSongDetails(details)
        }

        songDetails();
    }, [songId])

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