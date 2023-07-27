package com.example.project1task1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "helloServlet", value = "/helloServlet")
public class ComputeHashes extends HttpServlet {


    public void init() {
    }

    //implemented Post method as it used to send large, sensitive data and value would not be visible in the URL
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //gets the text and hash method type from the user
        String text = request.getParameter("text");
        String hash = request.getParameter("hash");
        //initialize
        String base64Binary = "";
        String hexBinary = "";

        try {
            //implementation of hashing
            MessageDigest md = MessageDigest.getInstance(hash);
            //update messagedigest
            md.update(text.getBytes());
            //stores in byte array
            byte[] hashvalue = md.digest();

            //used to print hash value
            base64Binary = javax.xml.bind.DatatypeConverter.printBase64Binary(hashvalue);
            hexBinary = javax.xml.bind.DatatypeConverter.printHexBinary(hashvalue);

        } catch (NoSuchAlgorithmException e) {
            //if hashing is not available
            System.out.println("No hashing available" + e);
            e.printStackTrace();
        }


        // Display the hashing results of MD5 and SHA-256
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h4> Input String: </h4>" + text);
        out.println("<h4> Hash Method: </h4>" + hash);
        out.println("<h4> Base 64 Binary: </h4>");
        out.println(base64Binary);
        out.println();
        out.println("<h4> Hex Binary: </h4>");
        out.println(hexBinary);
        out.println("</body></html>");

    }

    public void destroy() {
    }
}