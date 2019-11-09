package com.github.vissanuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GithubRepo
{

    static final List<DataDetailsTwo> repoCount = new ArrayList<>();

    public static void main(String[] args)
        {
            new GithubRepo().getRepo();
        }

    public void getRepo()
    {
        try
        {
            for (int i = 0; i <GithubData.repo.size(); i++)
            {
                long t1 = 0;
                int a = 1;
                do {
                    HttpURLConnection conn = (HttpURLConnection) new URL(GithubData.repo.get(i).getData0() + "?per_page=100&&page=" + a + "&&access_token=323c11e265f9037d7a5260ee1720013fad46aa92").openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder resBuilder = new StringBuilder();
                    String line;
                    while ((line = read.readLine()) != null) {
                        resBuilder.append("\n").append(line);
                    }
                    read.close();
                    t1 += Arrays.stream(resBuilder.toString().split("\"full_name\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    a++;
                } while (t1 == 100 || t1 == 200 || t1 == 300 || t1 == 400 || t1 == 500 || t1 == 600 || t1 == 700 || t1 == 800 || t1 == 900 || t1 == 1000);
                repoCount.add(new DataDetailsTwo(t1));
            }
          System.out.println("Successfully Collect Repositories.");
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println("Fail.");
        }

    }
}