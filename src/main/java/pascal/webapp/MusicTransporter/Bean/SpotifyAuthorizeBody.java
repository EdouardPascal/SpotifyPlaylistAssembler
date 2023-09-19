package pascal.webapp.MusicTransporter.Bean;

public class SpotifyAuthorizeBody {

    @feign.form.FormProperty("grant_type")
    String grant_type = "authorization_code";

    @feign.form.FormProperty("code")
    String code;

    @feign.form.FormProperty("redirect_uri")
    String redirect_uri;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
}
