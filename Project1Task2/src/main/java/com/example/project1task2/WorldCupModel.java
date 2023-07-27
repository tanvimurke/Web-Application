package com.example.project1task2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

public class WorldCupModel {

    //get nickname of the country
    public String getNickname(String country) throws IOException {
        //get data using jsoup
        Document doc = Jsoup.connect("https://www.topendsports.com/sport/soccer/team-nicknames-women.htm").get();
        //extract data from table
        Elements body = doc.select("table");
        //get first table
        Element table = body.get(0);
        //get all rows
        Elements rows = table.select("tr");

        String nickname = "";
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            //get columns
            Elements columns = row.select("td");
            //get first column
            String countries = columns.get(0).text();

            //check country and then get it's nickname
            if (countries.equalsIgnoreCase(country)) {
                //checks the selected country and extraxcts it;s nickname from the column
                nickname = columns.get(1).text();
                break;
            } else {
                //displays Not Found if no nickname is found
                nickname = "Not Found";
            }
        }
        return nickname;
    }

    //to get the capital city
    public String getCapital(String country) throws IOException {
        //handles the outlier where the country is Great Britain and not England
        if (country.equalsIgnoreCase("England")) {
            country = "great%20britain";
        }

        //extract data for capital city
        String url = "https://restcountries.com/v3.1/name/" + country.toLowerCase();
        //extracts the body
        String body = Jsoup.connect(url).ignoreContentType(true).execute().body();
        //uses json array to store the extracted data
        JSONArray allData = new JSONArray(body);
        //gets the last capital class for the correct capital city
        JSONObject countryObj = allData.getJSONObject(allData.length()-1);
        //extract the capital value
        JSONArray capitals = countryObj.getJSONArray("capital");

        String capital = "";

        //if there are multiple capital cities
         if (capitals.length() > 1) {
             StringBuilder sb = new StringBuilder();
                for (int i = 0; i < capitals.length(); i++) {
                    //append mulitple cities
                    sb.append(capitals.getString(i)).append(", ");
                }
                //deletes the last , from the string
             capital = sb.deleteCharAt(sb.length()-2).toString();
         } else {
             //if only one capital then get first
                capital = capitals.getString(0);
            }

        return capital;
    }

    //to get the top scorer
    public String getTopScorer(String country) throws IOException {

        //get the data using jsoup
        Document docscore = Jsoup.connect("https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring").get();
        //getting first table only
        Element tablescore = docscore.select("table").first();
        //get the rows
        Elements rowsscore = tablescore.select("tbody tr");
        //initialize
        String player = "";
        String playermessage = "";
        int goals = 0;
        //get player and goals
        for (Element row : rowsscore) {
            //get the exact values in the desired columns
            Elements cells = row.select("td");
            String countryscore = cells.get(2).text();
            if (countryscore.equalsIgnoreCase(country)) {
                player = cells.get(1).text();
                goals = Integer.parseInt(cells.get(4).text());
                playermessage = "" + player + ", " + goals + " goals";
                break;
            } else {
                //if no top scorer is available in the table
                playermessage = "N/A";
            }
        }
        return playermessage;
    }

    //to get the flag image
    public String getFlag(String country) throws IOException {

        //if country has two words then url needs to be altered
        String[] splitname = country.split(" ");
        if (splitname.length > 1) {
            //special case for south korea
            if (country.equalsIgnoreCase("South Korea")) {
                country = "korea-south";
            } else {
                //add - in the country name
                country = splitname[0].toLowerCase() + "-" + splitname[1].toLowerCase();
            }
            //handling special case
        } else if (country.equalsIgnoreCase("England")) {
            country = "united-kingdom";
        }

        //store the url
        String urlflag = "https://www.cia.gov/the-world-factbook/countries/" + country.toLowerCase() + "/flag";
        //get data using jsoup
        Document docflag = Jsoup.connect(urlflag).get();
        //get the flag image
        Element bodyimg = docflag.select("img").get(1);
        //create the image link
        String imgsrc = "https://www.cia.gov" + bodyimg.attr("src");

        return imgsrc;

    }

    //to get the emoji flag
    public String getEmojiFlag(List<emojiFlag> emojiflag, String country) {

        //extract the emoji flag image from the data
        String emojisrc = "";
        for (int i = 0; i < emojiflag.size(); i++) {
            if (emojiflag.get(i).getName().equalsIgnoreCase(country)) {
                //gets the desired flag from the country name
                emojisrc = emojiflag.get(i).getImage();
            }
        }
        return emojisrc;
    }

    //to handle the SSLHandshakeException
    public String fetch(String searchURL, String certType) {
        try {
            // Create trust manager, which lets you ignore SSLHandshakeExceptions
            createTrustManager(certType);
        } catch (KeyManagementException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        }

        String response = "";
        try {
            URL url = new URL(searchURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Something wrong with URL");
            return null;
        }
        return response;
    }

    public void createTrustManager(String certType) throws KeyManagementException, NoSuchAlgorithmException {
        /**
         * Annoying SSLHandShakeException. After trying several methods, finally this
         * seemed to work.
         * Taken from: http://www.nakov.com/blog/2009/07/16/disable-certificate-validation-in-java-ssl-connections/
         */
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance(certType);
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
