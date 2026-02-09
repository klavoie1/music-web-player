package com.player.music.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.player.music.document.Album;
import com.player.music.dto.AlbumListResponse;
import com.player.music.dto.AlbumRequest;
import com.player.music.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    private final Cloudinary cloudinary;

    public Album addAlbum(AlbumRequest albumRequest) throws IOException {

        Map<String, Object> imageParameter = ObjectUtils.asMap(
                "resource_type", "image"
        );

        Map<String, Object> imageUploadRequest = cloudinary.uploader().upload(albumRequest.getCoverImage().getBytes(), imageParameter);

        Album newAlbum = Album.builder()
                .albumName(albumRequest.getAlbumName())
                .description(albumRequest.getDescription())
                .bgColor(albumRequest.getBgColor())
                .imageUrl(imageUploadRequest.get("secure_url").toString())
                .build();

        return albumRepository.save(newAlbum);
    }

    @GetMapping
    public AlbumListResponse getAllAlbums() {
        return new AlbumListResponse(true, albumRepository.findAll());
    }

    public Boolean deleteAlbum(String id) {
        Album selectedAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album Id not found"));
        albumRepository.delete(selectedAlbum);
        return true;
    }
}
