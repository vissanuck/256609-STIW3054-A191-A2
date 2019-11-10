package com.github.vissanuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GithubFollower
    {

        static final List<DataDetailsTwo> followerCount = new ArrayList<>();

        public static void main(String[] args)
        {
           new GithubFollower().getFollower();
        }

        public void getFollower()
            {
                try
                    {
                        for (int i = 0; i < GithubData.follower.size(); i++)
                            {
                                long t1 = 0;
                                int b = 1;
                                do
                                {
                                HttpURLConnection conn = (HttpURLConnection) new URL(GithubData.follower.get(i).getData0() + "?per_page=100&&page=" + b + "&&access_token=5b521cdd08af76608924ed689b5521f7e241923b").openConnection();
                                conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                                BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                StringBuilder resBuilder = new StringBuilder();
                                String line2;
                                while ((line2 = read.readLine()) != null)
                                {
                                    resBuilder.append("\n").append(line2);
                                }
                                read.close();
                                t1 += Arrays.stream(resBuilder.toString().split("\"avatar_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                                b++;
                                } while (t1 == 100 || t1 == 200 || t1 == 300 || t1 == 400 || t1 == 500 || t1 == 600 || t1 == 700 || t1 == 800 || t1 == 900 || t1 == 1000);
                                followerCount.add(new DataDetailsTwo(t1));
                            }
                        System.out.println("Successfully Collect Followers.");
                    } catch (IOException e)
                        {
                            e.printStackTrace();
                            //System.out.println("Faill ");
                        }
            }
    }
