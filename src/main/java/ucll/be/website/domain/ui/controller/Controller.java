package ucll.be.website.domain.ui.controller;

import ucll.be.website.domain.db.LiedDB;
import ucll.be.website.domain.model.Lied;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(value = "/Controller")
public class Controller extends HttpServlet {
    private LiedDB database = new LiedDB();
    private String destination;
    private ArrayList<String> logboek;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        destination = "home";
        String command = request.getParameter("command");

        if(command == null) command = "";

        HttpSession session = request.getSession();
        logboek = (ArrayList) request.getSession().getAttribute("cmd");
        if (logboek == null){
            ArrayList<String> c = new ArrayList();
            session.setAttribute("cmd", c);
        } else {
            if(command.equals("")) {
                logboek.add("home");
            } else {
                logboek.add(command);
                session.setAttribute("cmd", logboek);
            }
        }

        switch(command){
            case "toevoegen":
                destination = toevoegen(request, response);
                break;
            case "add":
                destination = add(request, response);
                break;
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "zoek":
                destination = zoek(request, response);
                break;
            case "verwijderBevestiging":
                destination = verwijderBevestiging(request, response);
                break;
            case "verwijder":
                destination = verwijder(request, response);
                break;
            case "search":
                destination = search(request, response);
                break;
            case "update":
                destination = update(request, response);
                break;
            case "edit":
                destination = edit(request, response);
                break;
            case "logboek":
                destination = logboek(request, response);
                break;
            case "reset":
                destination = reset(request, response);
                break;
            default:
                destination = home(request, response);
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }

    protected String home(HttpServletRequest request, HttpServletResponse response){
        // hoogste rating
        request.setAttribute("hoogsteRating", database.hoogsteRating());

        // 5 recente liedjes
        request.setAttribute("vijfRecente", database.vijfRecente());

        return "index.jsp";
    }

    protected String toevoegen(HttpServletRequest request, HttpServletResponse response){
        return "toevoegen.jsp";
    }

    protected String add(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<>();

        Lied lied = new Lied();
        setTitel(lied, request, errors);
        setArtist(lied, request, errors);
        setGenre(lied, request, errors);
        setRating(lied, request, errors);
        setFavoriet(lied, request, errors);
        setAantalKeerBeluisterd(lied, request, errors);

        if(errors.size() == 0){
            try {
                database.voegLiedToe(lied);
                return overzicht(request, response);
            } catch (IllegalArgumentException e){
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return toevoegen(request, response);

    }

    // SETTERS
    private void setTitel(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        String titel = request.getParameter("titel");
        //boolean titelErrors = false;

        try {
            //request.setAttribute("titelPreviousValue", titel);
            lied.setTitel(titel);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            //titelErrors = true;
        }
    }

    private void setArtist(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        String artist = request.getParameter("artist");
        //boolean titelErrors = false;

        try {
            //request.setAttribute("titelPreviousValue", titel);
            lied.setArtist(artist);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            //titelErrors = true;
        }
    }

    private void setGenre(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        String genre = request.getParameter("genre");
        //boolean titelErrors = false;

        try {
            //request.setAttribute("titelPreviousValue", titel);
            lied.setGenre(genre);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            //titelErrors = true;
        }
    }

    private void setRating(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        String rating = request.getParameter("rating");
        //boolean titelErrors = false;

        try {
            //request.setAttribute("titelPreviousValue", titel);
            lied.setRating(Integer.parseInt(rating));
        } catch (NumberFormatException e){
            errors.add("U vulde geen nummer in");
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            //titelErrors = true;
        }
    }

    private void setFavoriet(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        String favoriet = request.getParameter("favoriet");
        lied.setFavoriet(Boolean.parseBoolean(favoriet));
    }

    private void setAantalKeerBeluisterd(Lied lied, HttpServletRequest request, ArrayList<String> errors){
        lied.setAantalKeerBeluisterd(1);
    }

    // END SETTERS

    protected String overzicht(HttpServletRequest request, HttpServletResponse response){
        // tabel
        request.setAttribute("liedjes", database.getLiedjes());

        // aantal, ratings
        request.setAttribute("aantalFavorieten", database.totaalAantalFavorieten());
        if(database.hoogsteRating() == null){
            request.setAttribute("hoogsteRatingTitel", "...");
            request.setAttribute("hoogsteRatingArtist", "...");
            request.setAttribute("hoogsteRatingRating", "...");
        } else {
            request.setAttribute("hoogsteRating", database.hoogsteRating());
        }
        request.setAttribute("gemiddeldeRating", database.gemiddeldeRating());

        return "overzicht.jsp";
    }

    protected String zoek(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = getCookieWithKey(request);
        if(cookie != null){
            request.setAttribute("lastSearch", cookie.getValue());
        }
        return "zoek.jsp";
    }

    protected String verwijderBevestiging(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        int lied = Integer.parseInt(id);

        Lied l = database.vindId(lied);

        System.out.println(l);

        request.setAttribute("lied", l);
        return "verwijderBevestiging.jsp";
    }

    protected String verwijder(HttpServletRequest request, HttpServletResponse response){
        String verwijder = request.getParameter("verwijder");

        if(verwijder.equals("Verwijder")){
            String titel = request.getParameter("titel");
            String artist = request.getParameter("artist");
            Lied l = database.vindLied(titel, artist);
            database.verwijderLied(l);
        }
        return overzicht(request, response);
    }

    protected String search(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String naam = request.getParameter("zoek");

        if(naam == null || naam.trim().isEmpty()){
            destination = zoek(request, response);
        } else {
            ArrayList<Lied> liedjes = database.vindUitLijst(naam);
            Cookie lastSearch = new Cookie("lastSearch", URLEncoder.encode(naam, "UTF-8"));
            response.addCookie(lastSearch);
            request.setAttribute("lastSearch", URLDecoder.decode(lastSearch.getValue(), "UTF-8"));
            lastSearch.setMaxAge(60*60*24); // 24 hours
            if(liedjes.isEmpty()) {
                request.setAttribute("liedjes", database.getLiedjes());
                request.setAttribute("zoek", naam);
                destination = "nietGevonden.jsp";
            } else {
                request.setAttribute("gevondenLiedjes", liedjes);
                destination = "gevonden.jsp";
            }
        }

        return destination;
    }

    protected String update(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        int lied = Integer.parseInt(id);

        Lied l = database.vindId(lied);

        request.setAttribute("lied", l);
        return "update.jsp";
    }

    protected String edit(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String titel = request.getParameter("titel");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");
        String favoriet = request.getParameter("favoriet");

        if(titel.trim().isEmpty() || artist.trim().isEmpty() || genre.trim().isEmpty() || rating.trim().isEmpty() || Integer.parseInt(rating) < 0 ||Integer.parseInt(rating) > 10){
            request.setAttribute("message", "Er was een probleem bij het aanpassen van de waarden");
        } else {
            Lied l = new Lied(Integer.parseInt(id), titel, artist, genre, Integer.parseInt(rating), Boolean.parseBoolean(favoriet), 1);
            database.veranderLied(l);
            request.setAttribute("message", "De aanpassing is gelukt!");
        }
        return overzicht(request, response);
    }

    protected String logboek(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("logboek", logboek);
        return "logboek.jsp";
    }

    protected String reset(HttpServletRequest request, HttpServletResponse response){
        logboek.clear();
        return logboek(request, response);
    }

    // COOKIES

    private Cookie getCookieWithKey(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            return null;
        }
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("lastSearch"))
                return cookie;
        }
        return null;
    }
}
