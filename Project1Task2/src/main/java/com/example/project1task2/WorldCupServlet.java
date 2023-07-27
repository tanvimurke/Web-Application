package com.example.project1task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "worldCupServlet", value = "/worldCupServlet")
public class WorldCupServlet extends HttpServlet {
    //initialize
    WorldCupModel wcm = null;
    List<emojiFlag> emojicountries;

    public void init() {
        //initializze
        wcm = new WorldCupModel();

        //to extract emoji image using gson
        //get url
        String urlemoji = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0";
        //to handle SSLHandshakeException
        String response = wcm.fetch(urlemoji, "TLSV1.3");
        //create gson object
        Gson gson = new Gson();
        //store data in array of Java objects
        emojiFlag[] emojiflag = gson.fromJson(response, emojiFlag[].class);

        //to filter these countries
        List<String> countries = Arrays.asList("Argentina", "Australia", "Brazil", "Canada", "China", "Colombia", "Costa Rica",
                "Denmark", "England", "France", "Germany", "Ireland", "Italy", "Jamaica", "Japan", "Morocco",
                "Netherlands", "New Zealand", "Nigeria", "Norway", "Philippines", "South Africa", "South Korea",
                "Spain", "Sweden", "Switzerland", "United States", "Vietnam", "Zambia");

        //create a list of filtered countries i.e. the countries that are present in the dropdown
        emojicountries = new ArrayList<>();
        for (emojiFlag countryname : emojiflag) {
            if (countries.contains(countryname.getName())) {
                emojicountries.add(countryname);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //get all the returned data from the Model and set it which is used to display the data in jsp

        //get selected country and set attribute
        String country = request.getParameter("item");
        request.setAttribute("country", country);

        //get nickname and set attribute
        String nickname = wcm.getNickname(country);
        request.setAttribute("nickname", nickname);

        //get capital and set attribute
        String capital = wcm.getCapital(country);
        request.setAttribute("capital", capital);

        //get player details and set attribute
        String playermessage = wcm.getTopScorer(country);
        request.setAttribute("playermessage", playermessage);

        //get flag image and set attribute
        String imgsrc = wcm.getFlag(country);
        request.setAttribute("imgsrc", imgsrc);

        //get emoji flag image and set attribute
        String emojisrc = wcm.getEmojiFlag(emojicountries, country);
        request.setAttribute("emojisrc", emojisrc);

        //decides which page is displayed
        String nextPage = "";

        if (country != null) {
            nextPage = "result.jsp";
        } else {
            nextPage = "index.jsp";
        }

        //implementation of webapp for mobile screen compatibility
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        //redirects to next view
        RequestDispatcher view = request.getRequestDispatcher(nextPage);
        view.forward(request, response);

    }

    public void destroy() {
    }
}