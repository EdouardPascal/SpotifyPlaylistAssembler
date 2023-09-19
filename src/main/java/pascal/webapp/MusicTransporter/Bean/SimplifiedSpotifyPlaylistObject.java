package pascal.webapp.MusicTransporter.Bean;

public class SimplifiedSpotifyPlaylistObject {
    boolean collaborative; //true if owner allows other to modify it
    String description;//description of playlist;

    String href;
    String id; //id of playlist
    ImageObject[] images; //array containing the cover images of the  playlist
    String name; //name of the playlist
    String uri;
    TracksSpotify tracks;  //object containing ifo about number of tracks

    public boolean isCollaborative() {
        return collaborative;
    }

    public void setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageObject[] getImages() {
        return images;
    }

    public void setImages(ImageObject[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public TracksSpotify getTracks() {
        return tracks;
    }

    public void setTracks(TracksSpotify tracks) {
        this.tracks = tracks;
    }
}
