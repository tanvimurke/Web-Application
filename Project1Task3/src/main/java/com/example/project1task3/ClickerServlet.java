package com.example.project1task3;

import java.io.*;
import java.util.Map;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "clickerServlet", urlPatterns = {"/clickerServlet","/getResults"})
public class ClickerServlet extends HttpServlet {

    //initialize
    ClickerModel cm;

    public void init() {
        cm = new ClickerModel();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //gets choice
        String answer = request.getParameter("answer");

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

        //access the data in a map and set the count to be displayed
        Map<String, Integer> map = null;
        map = cm.getCount(answer);

        //get count of A from the map
        Integer countA = map.get("A");
        //check if it is null and assign it as 0
        if(countA==null){
            countA=0;
        }
        //sets the count in attribute
        request.setAttribute("countA", countA);

        //get count of B from the map
        Integer countB = map.get("B");
        //check if it is null and assign it as 0
        if(countB==null){
            countB=0;
        }
        //sets the count in attribute
        request.setAttribute("countB", countB);

        //get count of C from the map
        Integer countC = map.get("C");
        //check if it is null and assign it as 0
        if(countC==null){
            countC=0;
        }
        //sets the count in attribute
        request.setAttribute("countC", countC);

        //get count of D from the map
        Integer countD = map.get("D");
        //check if it is null and assign it as 0
        if(countD==null){
            countD=0;
        }
        //sets the count in attribute
        request.setAttribute("countD", countD);

        //display result according the url path
        String url = request.getServletPath();

        //decides the link for redirection
        String nextPage = "";
        if (url.contains("/getResults")) {
            cm.clearCount();
            nextPage = "result.jsp";
        }else{
            nextPage = "index.jsp";
        }

        //redirects to the page
        RequestDispatcher view = request.getRequestDispatcher(nextPage);
        view.forward(request, response);

    }

    public void destroy() {
    }
}