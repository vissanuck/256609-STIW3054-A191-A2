package com.github.vissanuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GithubFollowing
{


    public static final List<DataDetailsTwo> followingCount = new ArrayList<>();

    public static void main(String[] args)
    {
        new GithubFollowing().getFollowing();

    }

    public void getFollowing() {
        try {
            for (int i = 0; i < GithubData.following.size(); i++) {
                long t1 = 0;
                int c = 1;
                do {
                    HttpURLConnection conn = (HttpURLConnection) new URL(GithubData.following.get(i).getData0() + "?per_page=100&&page=" + c + "&&access_token=9efa7d7cf9b425df53be1ebbb6f1c30ad9bcce9c").openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = read.readLine()) != null) {
                        responseSB2.append("\n" + line2);
                    }
                    read.close();
                    t1 += Arrays.stream(responseSB2.toString().split("\"avatar_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    c++;
                } while (t1 == 100 || t1 == 200 || t1 == 300 || t1 == 400 || t1 == 500 || t1 == 600 || t1 == 700 || t1 == 800 || t1 == 900 || t1 == 1000
                        || t1 == 1100 || t1 == 1200 || t1 == 1300 || t1 == 1400 || t1 == 1500 || t1 == 1600 || t1 == 1700 || t1 == 1800 || t1 == 1900 || t1 == 2000
                        || t1 == 2100 || t1 == 2200 || t1 == 2300 || t1 == 2400 || t1 == 2500 || t1 == 2600 || t1 == 2700 || t1 == 2800 || t1 == 2900 || t1 == 3000
                        || t1 == 3100 || t1 == 3200 || t1 == 3300 || t1 == 3400 || t1 == 3500 || t1 == 3600 || t1 == 3700 || t1 == 3800 || t1 == 3900 || t1 == 4000
                        || t1 == 4100 || t1 == 4200 || t1 == 4300 || t1 == 4400 || t1 == 4500 || t1 == 4600 || t1 == 4700 || t1 == 4800 || t1 == 4900 || t1 == 5000
                        || t1 == 5100 || t1 == 5200 || t1 == 5300 || t1 == 5400 || t1 == 5500 || t1 == 5600 || t1 == 5700 || t1 == 5800 || t1 == 5900 || t1 == 6000
                        || t1 == 6100 || t1 == 6200 || t1 == 6300 || t1 == 6400 || t1 == 6500 || t1 == 6600 || t1 == 6700 || t1 == 6800 || t1 == 6900 || t1 == 7000);
                followingCount.add(new DataDetailsTwo(t1));
            }
           System.out.println("Successfully Collect Followings.");
        } catch (IOException e) {
            e.printStackTrace();
           // System.out.println("ERROR : Failed to access no. of following");
        }
    }

}
