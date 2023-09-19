package pascal.webapp.MusicTransporter.Bean.SpotifyObjects;

//object returned when we fetch for the songs inside each playlist on Spotify
public class PlaylistTrackObject {

    TrackObject track;


    public TrackObject getTrack() {
        return track;
    }

    public void setTrack(TrackObject track) {
        this.track = track;
    }
}
