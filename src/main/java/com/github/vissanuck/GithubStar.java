package com.github.vissanuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GithubStar
{
    static final List<DataDetailsTwo> starsCount = new ArrayList<>();

    public static void main(String[] args)
    {
        new GithubStar().getOrganization();
    }

    public void getOrganization() {
        try {
            for (int i = 0; i < GithubData.stars.size(); i++) {
                long t1 = 0;
                int d = 1;
                do {
                    HttpURLConnection conn = (HttpURLConnection) new URL(GithubData.stars.get(i).getData0() + "?per_page=100&&page=" + d + "&&access_token=323c11e265f9037d7a5260ee1720013fad46aa92").openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = read.readLine()) != null) {
                        responseSB2.append("\n").append(line2);
                    }
                    read.close();
                    t1 += Arrays.stream(responseSB2.toString().split("\"avatar_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    d++;
                } while (t1 == 100 || t1 == 200 || t1 == 300 || t1 == 400 || t1 == 500 || t1 == 600 || t1 == 700 || t1 == 800 || t1 == 900 || t1 == 1000);
                starsCount.add(new DataDetailsTwo(t1));
            }
            System.out.println("Successfully Collect Organization");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("ERROR : Failed to access no. of subscription");
        }
    }
}
