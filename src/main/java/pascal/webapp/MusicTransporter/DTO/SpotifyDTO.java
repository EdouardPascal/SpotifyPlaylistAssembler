package pascal.webapp.MusicTransporter.DTO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;

@Component
@SessionScope
public class SpotifyDTO {

    private SpotifyTokens spotifyTokens;

    
    public SpotifyTokens getSpotifyTokens() {
        return spotifyTokens;
    }

    public void setSpotifyTokens(SpotifyTokens spotifyTokens) {
        this.spotifyTokens = spotifyTokens;
    }
}
