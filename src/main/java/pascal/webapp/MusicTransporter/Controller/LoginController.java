package pascal.webapp.MusicTransporter.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pascal.webapp.MusicTransporter.Bean.Playlist;
import pascal.webapp.MusicTransporter.Bean.SpotifyTokens;
import pascal.webapp.MusicTransporter.DTO.AppleMusicDTO;
import pascal.webapp.MusicTransporter.DTO.SpotifyDTO;
import pascal.webapp.MusicTransporter.Service.PlaylistService;
import pascal.webapp.MusicTransporter.proxy.AuthenticationProxy;
import pascal.webapp.MusicTransporter.proxy.InternalCallsProxy;
import pascal.webapp.MusicTransporter.proxy.SpotifyProxy;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired //authentication proxy that will be use to make POST call to spotify api using feign interface
    private AuthenticationProxy authenticationProxy;

    @Autowired
    private SpotifyProxy spotifyProxy;

    @Autowired //spotify data transfer object that will contain the spotify tokens and make api calls
    private SpotifyDTO spotifyDTO;

    @Autowired
    private AppleMusicDTO appleMusicDTO;

    @Autowired
    private InternalCallsProxy internalCallsProxy;

    @Autowired
    private PlaylistService playlistService;

    @Value("${spotify_authorize_url}")
    private String spotify_authorize_url;

    @Value("${spotify.client_id}")
    private String client_id;

    @Value("${scope_spotify_read}")
    private String spotify_scope;

    @Value("${redirect_spotify_login}")
    private String redirect_spotify_login;

    @Value("${spotify.client_secret}")
    private String client_secret;


    @Value("${private_key_name}")
    private String apple_key_name;

    @Value("${apple.team.id}")
    private String team_id;

    @Value("{apple.key.id}")
    private String key_id;

    @GetMapping("/login") //first path that will redirect client to spotify connection page
    public RedirectView login() {
        //if succesfully logged spotify will redirect and send a request to our callback method
        //and will ask for other stuff
        String redirect = spotify_authorize_url + "response_type=code&" + "client_id=" +
                client_id + "&scope=" + spotify_scope + "&redirect_uri=" + redirect_spotify_login;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirect);
        return redirectView;
    }


    /*
    The callback path is the call that Sptofify does to our back-end app and provides the
    authentication code and the state
     */
    @GetMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam String code) {

        Map<String, String> body = new HashMap<>();
        body.put("code", code);
        body.put("redirect_uri", redirect_spotify_login);
        body.put("grant_type", "authorization_code");


        String clients = client_id + ":" + client_secret;

        //header that is encoded in base6
        String header = "Basic " + Base64.encodeBase64String(clients.getBytes());


        spotifyDTO.setSpotifyTokens(authenticationProxy.getAccessToken(body, header));

        SpotifyTokens tokens = spotifyDTO.getSpotifyTokens();


        Playlist playlists = spotifyProxy.allPlaylists("Bearer " + tokens.getAccess_token());

        playlistService.setPlaylists(playlists);

        return internalCallsProxy.showPlaylist();
    }


    //Start generating for apple music
    @GetMapping("/appleLogin")
    @ResponseBody
    public String loginapple() throws IOException {

        //first grab the private key


        //generate token
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", team_id); //adding the issuer id to the claim
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 30));
        claims.put("iat", new Date(System.currentTimeMillis()));
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "ES256");
        header.put("kid", key_id);
        String JWT_token = Jwts.builder().setClaims(claims)
                .setHeader(header).signWith(SignatureAlgorithm.ES256, appleMusicDTO.getApple_key()).compact();
        return JWT_token;


    }
}

