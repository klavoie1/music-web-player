package com.player.music.dto;

import com.player.music.document.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongListResponse {

    private Boolean success;

    private List<Song> songList;
}
