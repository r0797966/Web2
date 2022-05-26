package ucll.be.website.domain.model;

public class Lied {
    private int id, rating, aantalKeerBeluisterd;
    private String titel, artist, genre;
    private boolean isFavoriet;

    // constructor
    public Lied(int id, String titel, String artist, String genre, int rating, boolean isFavoriet, int aantalKeerBeluisterd){
        setId(id);
        setTitel(titel);
        setArtist(artist);
        setGenre(genre);
        setRating(rating);
        setFavoriet(isFavoriet);
        setAantalKeerBeluisterd(aantalKeerBeluisterd);
    }

    public Lied(){}

    // setters
    public void setId(int id){
        if(id < 0) throw new IllegalArgumentException("Id moet positief zijn");
        this.id = id;
    }

    public void setTitel(String titel){
        if(titel == null || titel.trim().isEmpty()) throw new IllegalArgumentException("Titel mag niet leeg zijn");
        this.titel = titel;
    }

    public void setArtist(String artist){
        if(artist == null || artist.trim().isEmpty()) throw new IllegalArgumentException("Artist mag niet leeg zijn");
        this.artist = artist;
    }

    public void setGenre(String genre){
        if(genre == null || genre.trim().isEmpty()) throw new IllegalArgumentException("Genre mag niet leeg zijn");
        this.genre = genre;
    }

    public void setRating(int rating){
        if(rating < 0 || rating > 10) throw new IllegalArgumentException("Rating moet tussen 0 en 10 zijn");
        this.rating = rating;
    }

    public void setFavoriet(boolean favoriet){this.isFavoriet = favoriet;}

    public void setAantalKeerBeluisterd(int aantal){
        if(aantal < 0) throw new IllegalArgumentException("Aantal keer beluisterd moet meer dan 0 zijn");
        this.aantalKeerBeluisterd = aantal;
    }

    // getters
    public int getId(){return this.id;}

    public String getTitel(){return this.titel;}

    public String getArtist(){return this.artist;}

    public String getGenre(){return this.genre;}

    public int getRating(){return this.rating;}

    public boolean isFavoriet(){return this.isFavoriet;}

    public int getAantalKeerBeluisterd(){return this.aantalKeerBeluisterd;}

    // methodes
    public String favorietToString(){
        return this.isFavoriet ? "ja" : "nee";
    }

    public void verhoogAantalKeerBeluisterd(){this.aantalKeerBeluisterd += 1;}
}
