package pascal.webapp.MusicTransporter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pascal.webapp.MusicTransporter.Bean.Playlist;
import pascal.webapp.MusicTransporter.Bean.SimplifiedSpotifyPlaylistObject;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;
import pascal.webapp.MusicTransporter.Service.PlaylistService;
import pascal.webapp.MusicTransporter.proxy.SpotifyProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class PlaylistController {

    @Autowired
    SpotifyTokens spotifyTokens;

    @Autowired
    SpotifyProxy spotifyProxy;

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/playlist")
    public String getPlaylist(Model model) {
        String authorization = "Bearer " + spotifyTokens.getAccess_token();
        // Playlist playlist = spotifyProxy.allPlaylists(authorization);
        Playlist playlist = playlistService.getPlaylists();
        List<SimplifiedSpotifyPlaylistObject> playlists = Arrays.asList(playlist.getItems());

        model.addAttribute("playlists", playlist.getItems());
        return "playlist.html";
    }


    @PostMapping("/playlist")
    public String result(Model model, @RequestParam String playlist, @RequestParam
    String name) {
        //recupperate the playlists_ids so we can ask spotify to get the songs
        //and also create the apple music playlists.
        ArrayList<String> songs = new ArrayList<>();
        HashMap<String, String> code_map = playlistService.getPlaylist_codes();
        for (String codes : playlist.split(","))
            songs.add(code_map.get(codes));
        model.addAttribute("name", name);
        model.addAttribute("songs", songs);
        playlistService.transferPlaylist(playlist, name);
        return "result.html";
    }
}
