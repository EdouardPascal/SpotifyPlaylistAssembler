package pascal.webapp.MusicTransporter.proxy;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pascal.webapp.MusicTransporter.Bean.Playlist;
import pascal.webapp.MusicTransporter.Bean.SpotifyObjects.SpotifyPlaylistDTO;
import pascal.webapp.MusicTransporter.Bean.SpotifyObjects.SpotifyUserProfile;
import pascal.webapp.MusicTransporter.Bean.SpotifyObjects.Tracks;

@FeignClient(name = "spotifycalls", url = "https://api.spotify.com/v1")
public interface SpotifyProxy {

    @GetMapping(value = "/me")
    public SpotifyUserProfile getUserProfile(@RequestHeader("Authorization") String authorization);

    @GetMapping(value = "/me/playlists")
    public Playlist allPlaylists(@RequestHeader("Authorization") String authorization);

    @GetMapping(value = "/playlists/{playlist_id}/tracks?offset={offset}")
    public Tracks findSongs(
            @PathVariable(value = "playlist_id") String playlist_id, @PathVariable(value = "offset") int offset,

            @RequestHeader("Authorization") String authorization);

    //add songs to playlist
    @PostMapping(value = "/playlists/{playlist_id}/tracks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers({"Content-Type: application/json"})
    public String addSongs(@PathVariable(value = "playlist_id") String playlist_id,
                           @RequestHeader("Authorization") String authorization,
                           @RequestBody String data);

    @PostMapping(value = "/users/{user_id}/playlists", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers({"Content-Type: application/json"})

    public SpotifyPlaylistDTO createPlaylist
            (@RequestHeader("Authorization") String authorization,
             @PathVariable(value = "user_id") String user_id,
             @RequestBody String data);
}

