package pascal.webapp.MusicTransporter.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pascal.webapp.MusicTransporter.Bean.Playlist;
import pascal.webapp.MusicTransporter.Bean.SimplifiedSpotifyPlaylistObject;
import pascal.webapp.MusicTransporter.Bean.SpotifyObjects.*;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;
import pascal.webapp.MusicTransporter.DTO.SpotifyDTO;
import pascal.webapp.MusicTransporter.proxy.SpotifyProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private SpotifyDTO spotifyDTO;

    Playlist playlists;

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private SpotifyProxy spotifyProxy;

    private HashMap<String, String> playlist_codes = new HashMap<>();

    public HashMap<String, String> getPlaylist_codes() {
        return playlist_codes;
    }

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
        for (SimplifiedSpotifyPlaylistObject object : playlists.getItems()) {
            playlist_codes.put(object.getId(), object.getName());
        }
    }

    public List<SimplifiedSpotifyPlaylistObject> allPlaylist() {
        return Arrays.asList(playlists.getItems());
    }

    public String transferPlaylist(
            String chosen_playlists_text, String created_playlist_name) {

        //split the list of playlist into different chosen playlists;
        String[] chosen_playlists = chosen_playlists_text.split(",");

        SpotifyTokens spotifyTokens = spotifyDTO.getSpotifyTokens();
        String authorization = "Bearer " + spotifyTokens.getAccess_token();

        //find user details such as user id that will be userful for us.
        SpotifyUserProfile userProfile = spotifyProxy.getUserProfile(authorization);
        String user_id = userProfile.getId();

        //create JSON Object of playlist we will create
        JSONObject created_playlist_object = new JSONObject();
        created_playlist_object.put("name", created_playlist_name);
        created_playlist_object.put("public", true);
        created_playlist_object.put("collaborative", false);
        created_playlist_object.put
                ("description", "Created from MusicTransporterApp by merging: " + chosen_playlists_text);

        //create the playlist by calling function from spotifyproxy
        SpotifyPlaylistDTO playlist_created_DTO =
                spotifyProxy.createPlaylist(authorization, user_id, created_playlist_object.toString());

        String created_playlist_id = playlist_created_DTO.getId();

        JSONObject songs_Body = new JSONObject();
        songs_Body.put("position", 0);//add songs on top

        String snapshot = "";
        for (String chosen_playlist : chosen_playlists) {
            //grab a playlist object


            String playlist_name = this.getPlaylist_codes().get(chosen_playlist);


            String next;
            int offset = 0;
            do {
                Tracks list_tracks = spotifyProxy.findSongs(chosen_playlist, offset, authorization);
                PlaylistTrackObject[] tracksItems = list_tracks.getItems();

                ArrayList<String> tracks_uri = new ArrayList<>();
                for (PlaylistTrackObject trackItem : tracksItems) {
                    TrackObject trackObject = trackItem.getTrack();
                    tracks_uri.add(trackObject.getUri());

                }
                songs_Body.remove("uris");
                songs_Body.put("uris", tracks_uri);
                snapshot = spotifyProxy.addSongs(created_playlist_id, authorization, songs_Body.toString());
                offset = list_tracks.getOffset() + list_tracks.getItems().length;
                next = list_tracks.getNext();

            } while (next != null);


        }


        return snapshot;


    }
}
