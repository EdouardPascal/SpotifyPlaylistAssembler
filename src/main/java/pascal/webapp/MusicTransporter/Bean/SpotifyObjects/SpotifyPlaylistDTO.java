package pascal.webapp.MusicTransporter.Bean.SpotifyObjects;

import java.util.ArrayList;

public class SpotifyPlaylistDTO {

    private String name;
    private ArrayList<PlaylistTrackObject> songs = new ArrayList<>();
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int total = songs.size();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PlaylistTrackObject> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<PlaylistTrackObject> songs) {
        this.songs = songs;
    }
}
