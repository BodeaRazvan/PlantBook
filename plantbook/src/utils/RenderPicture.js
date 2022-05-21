import React from "react";

export function renderPicture(picture) {
    if (picture.url === "undefined") {
        return null
    } else if(picture.url.includes("mp4") ||
        picture.url.includes("webm") ||
        picture.url.includes("ogg")  ||
        picture.url.includes("mov") ||
        picture.url.includes("youtube") ||
        picture.url.includes("youtu.be")) {
        return (
            <div>
                <iframe className="videoAuto" id={picture.id} src={picture.url}/>
            </div>
        )
    }
    {
        return (
            <div className="profilePicture">
                <img className="pictureAuto" src={picture.url} />
            </div>
        )
    }
}
