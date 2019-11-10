package com.github.vissanuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GithubData
    {
        static final List<DataDetails> idname = new ArrayList<>();
        static final List<DataDetails> repo = new ArrayList<>();
        static final List<DataDetails> follower = new ArrayList<>();
        static final List<DataDetails> following = new ArrayList<>();
        static final List<DataDetails> stars = new ArrayList<>();



        public static void main(String[] args)
        {
            new GithubData().getData();

        }

        public void getData()
        {
            try
            {
                for (int i = 1; i <= 2; i++)
                {
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.github.com/users/zhamri/followers?per_page=100&page=" + i + "&&access_token=5b521cdd08af76608924ed689b5521f7e241923b").openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder resBuilder = new StringBuilder();
                    String line;
                    while ((line = read.readLine()) != null)
                    {
                        resBuilder.append("\n").append(line);
                    }

                    read.close();
                    Arrays.stream(resBuilder.toString().split("\"login\":\"")).skip(1).map(l -> l.split("\",")[0]).forEach(l -> idname.add(new DataDetails(l)));
                    Arrays.stream(resBuilder.toString().split("\"repos_url\":\"")).skip(1).map(l1 -> l1.split("\",")[0]).forEach(l1 -> repo.add(new DataDetails(l1)));
                    Arrays.stream(resBuilder.toString().split("\"followers_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).forEach(l2 -> follower.add(new DataDetails(l2)));
                    Arrays.stream(resBuilder.toString().split("\"following_url\":\"")).skip(1).map(l3 -> l3.split("\\{/other_user}\",")[0]).forEach(l3 -> following.add(new DataDetails(l3)));
                    Arrays.stream(resBuilder.toString().split("\"starred_url\":\"")).skip(1).map(l4 -> l4.split("\\{/owner}\\{/repo}\",")[0]).forEach(l4 -> stars.add(new DataDetails(l4)));

                }
                System.out.println("Successfully Collect ID name.");
            }
            catch (IOException e)
            {
              e.printStackTrace();

            }
        }

    }
