package ucll.be.website.domain.db;

import ucll.be.website.domain.model.Lied;

import java.util.ArrayList;

public class LiedDB {
    private int sequence = 0;
    private ArrayList<Lied> liedjes;

    // constructor
    public LiedDB(){
        this.liedjes = new ArrayList<>();
        this.voegLiedToe(new Lied(0, "Stay Beautiful", "Taylor Swift", "Country", 5, false, 1));
        this.voegLiedToe(new Lied(0, "I Wonder (Departure)", "ABBA", "Pop", 8, true, 1));
        this.voegLiedToe(new Lied(0, "The Pot", "Tool", "Metal", 6, false, 1));
    }

    // getter
    public ArrayList<Lied> getLiedjes(){return this.liedjes;}

    // methodes
    public void voegLiedToe(Lied lied){
        if(lied == null) throw new IllegalArgumentException("Lied mag niet leeg zijn");
        boolean toegevoegd = false;
        for(Lied l: this.liedjes){
            if(l.getTitel().equalsIgnoreCase(lied.getTitel()) && l.getArtist().equalsIgnoreCase(lied.getArtist())) {
                l.setRating(lied.getRating());
                l.setFavoriet(lied.isFavoriet());
                l.verhoogAantalKeerBeluisterd();
                toegevoegd = true;
            }
        }
        if(!toegevoegd) {
            this.sequence++;
            lied.setId(sequence);
            this.liedjes.add(lied);
        }
    }

    public Lied vindLied(String titel, String artist){
        if(titel == null || titel.isEmpty() || artist == null || artist.isEmpty()) throw new IllegalArgumentException();
        for(Lied l: this.liedjes){
            if(l.getTitel().equalsIgnoreCase(titel) && l.getArtist().equalsIgnoreCase(artist)) return l;
        }
        return null;
    }

    public ArrayList<Lied> vindUitLijst(String s){
        ArrayList<Lied> res = new ArrayList<>();
        for(Lied l: this.liedjes){
            if(l.getTitel().toLowerCase().contains(s) || l.getTitel().contains(s)||
                    l.getArtist().toLowerCase().contains(s) || l.getArtist().contains(s) ||
                    l.getGenre().toLowerCase().contains(s) || l.getGenre().contains(s)){
                res.add(l);
            }
        }
        return res;
    }

    public void verwijderLied(Lied lied){
        if(lied == null) throw new IllegalArgumentException("Lied mag niet leeg zijn");
        Lied l = vindLied(lied.getTitel(), lied.getArtist());
        for(Lied a: this.liedjes){
            if(a.getId() >= l.getId()) a.setId(a.getId() - 1);
        }
        this.liedjes.remove(l);
        sequence--;
    }

    public Lied hoogsteRating(){
        Lied res = null;
        for(Lied l: this.liedjes){
            if(res == null) res = l;
            if(l.getRating() > res.getRating()) res = l;
        }
        return res;
    }

    public ArrayList<Lied> vijfRecente(){
        if(this.liedjes.size() < 5) return this.liedjes;
        ArrayList<Lied> res = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            res.add(this.liedjes.get(this.liedjes.size() - i));
        }
        return res;
    }

    public int gemiddeldeRating(){
        if(this.liedjes.size() == 0) return 0;
        int totaal = 0;
        for(Lied l: this.liedjes){
            totaal += l.getRating();
        }
        return totaal/this.liedjes.size();
    }

    public int totaalAantalFavorieten(){
        int totaal = 0;
        for(Lied l: this.liedjes){
            if(l.isFavoriet()) totaal++;
        }
        return totaal;
    }

    public Lied vindId(int id){
        Lied lied = null;
        for(Lied l: this.liedjes){
            if(l.getId() == id) lied = l;
        }
        return lied;
    }

    public void veranderLied(Lied lied){
        for(Lied l: this.liedjes){
            if(l.getId() == lied.getId()) {
                l.setTitel(lied.getTitel());
                l.setArtist(lied.getArtist());
                l.setGenre(lied.getGenre());
                l.setRating(lied.getRating());
                l.setFavoriet(lied.isFavoriet());
            }
        }
    }
}
