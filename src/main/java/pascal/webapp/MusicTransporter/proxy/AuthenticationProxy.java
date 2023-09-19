package pascal.webapp.MusicTransporter.proxy;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;
import pascal.webapp.MusicTransporter.config.SpotifyAuthenticationConfig;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "authentication", url = "https://accounts.spotify.com/api/token",
        configuration = SpotifyAuthenticationConfig.class)
public interface AuthenticationProxy {

    @PostMapping(value = "", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public SpotifyTokens getAccessToken
            (@RequestBody Map<String, ?> body,
             @RequestHeader(name = "Authorization") String authorization);


}

