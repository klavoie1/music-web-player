package com.player.music.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.player.music.document.Song;
import com.player.music.dto.SongRequest;
import com.player.music.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final Cloudinary cloudinary;

    public Song addSong(SongRequest songRequest) throws IOException {

        Map<String, Object> songParameter = ObjectUtils.asMap("resource_type", "video");

        Map<String, Object> imageParameter = ObjectUtils.asMap("resource_type", "image");

        Map<String, Object> audioUploadResponse = cloudinary.uploader().upload(songRequest.getAudioFile().getBytes(), songParameter);
        Map<String, Object> imageUploadResponse = cloudinary.uploader().upload(songRequest.getAudioFile().getBytes(), imageParameter);

        Double durationInSeconds = (Double)audioUploadResponse.get("duration");

        String formatedDuration = formatDuration(durationInSeconds);

        Song newSong = Song.builder()
                .songTitle(songRequest.getName())
                .songDesc(songRequest.getDescription())
                .songAlbum(songRequest.getAlbum())
                .songDuration(formatedDuration)
                .songImage(imageUploadResponse.get("secure_url").toString())
                .songFile(audioUploadResponse.get("secure_url").toString())
                .build();

        return  songRepository.save(newSong);
    }

    /**
      Takes in a double and formats it into a String with the "0:00" format.
     If the duration of the file is zero or null, returns "0:00"
     **/
    private String formatDuration(Double durationInSeconds) {
        if (durationInSeconds == null) {
            return "0:00";
        }

        int durationMinutes = (int)(durationInSeconds / 60);
        int durationSeconds = (int)(durationInSeconds % 60);

        return String.format("%d:%02d", durationMinutes, durationSeconds);
    }
}
