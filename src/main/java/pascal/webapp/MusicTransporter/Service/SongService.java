package pascal.webapp.MusicTransporter.Service;

import pascal.webapp.MusicTransporter.Song;

import java.util.ArrayList;
import java.util.List;

public class SongService {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> findAll() {
        return songs;
    }

}
