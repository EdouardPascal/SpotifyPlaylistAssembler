package pascal.webapp.MusicTransporter.Bean;

public class Playlist {

    //id of the playlist
    String href;// a link to web api returning full result of request
    int limit; //maximum number of items in response
    String next; //url to the next page of items(null if none)
    int offset; //the offset of numbers returned
    String previous;// url to the previous page of items(null if none)
    int total; //total numbers of items available to return

    SimplifiedSpotifyPlaylistObject[] items;
   /*
    String name; //name of the playlist;
    String cover; //link to image cover of playlist;
    int tracks; //number of tracks in the playlist;
    int duration; //duration of the album

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    */


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public SimplifiedSpotifyPlaylistObject[] getItems() {
        return items;
    }

    public void setItems(SimplifiedSpotifyPlaylistObject[] items) {
        this.items = items;
    }
}
