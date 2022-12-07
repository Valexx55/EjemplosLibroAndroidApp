package edu.val.ejemploslibroandroidapp.tema10;

public class Cancion {

    private long trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String artworkUrl100;//imagen de la canción
    private String previewUrl;//mp3

    public Cancion(long trackId, String artistName, String collectionName, String trackName, String artworkUrl100, String previewUrl) {
        this.trackId = trackId;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.artworkUrl100 = artworkUrl100;
        this.previewUrl = previewUrl;
    }

    public Cancion() {
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "trackId=" + trackId +
                ", artistName='" + artistName + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", trackName='" + trackName + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
