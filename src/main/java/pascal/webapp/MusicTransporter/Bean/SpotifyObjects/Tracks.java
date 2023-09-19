package pascal.webapp.MusicTransporter.Bean.SpotifyObjects;

public class Tracks {

    String href;
    int limit;
    int offset;
    String previous;
    int total;
    String next; //link to the next page of items
    PlaylistTrackObject[] items; //arrays of playlist track object


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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public PlaylistTrackObject[] getItems() {
        return items;
    }

    public void setItems(PlaylistTrackObject[] items) {
        this.items = items;
    }
}
