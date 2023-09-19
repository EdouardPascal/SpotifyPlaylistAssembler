package pascal.webapp.MusicTransporter.Bean.SpotifyObjects;

//object inside the TrackPlaylistObject that contains information about the track
public class TrackObject {

    String name; //name of track
    String id; //spotify;
    ArtistObject[] artists;
    String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArtistObject[] getArtists() {
        return artists;
    }

    public void setArtists(ArtistObject[] artists) {
        this.artists = artists;
    }
}
