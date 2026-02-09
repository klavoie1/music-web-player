package com.player.music.repository;

import com.player.music.document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
}
