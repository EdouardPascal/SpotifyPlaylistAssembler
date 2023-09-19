package pascal.webapp.MusicTransporter.Bean.SpotifyObjects;

import pascal.webapp.MusicTransporter.Bean.ImageObject;

public class SpotifyUserProfile {
    String country;
    String display_name;
    String email;
    String id;

    ImageObject[] images;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageObject[] getImages() {
        return images;
    }

    public void setImages(ImageObject[] images) {
        this.images = images;
    }
}
