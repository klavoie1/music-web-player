package com.player.music.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @Id
    @JsonProperty("_id")
    private String songId;

    private String songTitle;

    private String songDesc;

    private String songAlbum;

    private String songDuration;

    private String songImage;

    private String songFile;
}
