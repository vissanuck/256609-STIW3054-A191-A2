package com.github.vissanuck;

public class GithubMain {

    public static void main(String[] args) {
        new GithubData().getData();
        new GithubRepo().getRepo();
        new GithubFollower().getFollower();
        new GithubFollowing().getFollowing();
        new GithubStar().getOrganization();
        new GithubExcel().getExcel();
    }
}
