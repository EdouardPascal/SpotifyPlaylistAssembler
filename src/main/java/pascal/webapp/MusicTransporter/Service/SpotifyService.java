package pascal.webapp.MusicTransporter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pascal.webapp.MusicTransporter.Bean.SpotifyObjects.SpotifyPlaylistDTO;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;
import pascal.webapp.MusicTransporter.DTO.SpotifyDTO;

@Component
public class SpotifyService {

    @Autowired
    private SpotifyDTO spotifyDTO;

    private final RestTemplate rest = new RestTemplate();

    private String spotify_url = "https://api.spotify.com/v1";


    public SpotifyPlaylistDTO findSongs(String provided_id) {
        SpotifyTokens spotifyTokens = spotifyDTO.getSpotifyTokens();
        String autorization = "Bearer " + spotifyTokens.getAccess_token();
        String url = spotify_url + "/playlists/" + provided_id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", autorization);
        HttpEntity<String> httpEntity = new HttpEntity<>("{}", headers);

        ResponseEntity<SpotifyPlaylistDTO> response =
                rest.exchange(url, HttpMethod.GET, httpEntity, SpotifyPlaylistDTO.class);
        return response.getBody();
    }

}
