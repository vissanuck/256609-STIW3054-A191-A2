package com.github.vissanuck;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class GithubExcel
{

    public static void main(String[] args)
    {
        new GithubExcel().getExcel();
    }


    public void getExcel() {
        if (GithubData.idname.isEmpty() ||GithubRepo.repoCount.isEmpty() || GithubFollower.followerCount.isEmpty() || GithubFollowing.followingCount.isEmpty()|| GithubStar.starsCount.isEmpty() ) {
            System.out.println("Error No Data.");
            System.exit(0);
        }
        String excelFile = "Assignment 2.xlsx";
        System.out.println("Creating " + excelFile + "..");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Github Zhamri's Followers");
        try {
            int k = 0;
            do {
                if (k == 0) {
                    XSSFRow row = sheet.createRow(k);
                    XSSFCell cell1_1 = row.createCell(0);
                    cell1_1.setCellValue("No.");
                    XSSFCell cell1_2 = row.createCell(1);
                    cell1_2.setCellValue("login id");
                    XSSFCell cell1_3 = row.createCell(2);
                    cell1_3.setCellValue("Number of repositories");
                    XSSFCell cell1_4 = row.createCell(3);
                    cell1_4.setCellValue("Number of followers");
                    XSSFCell cell1_5 = row.createCell(4);
                    cell1_5.setCellValue("Number of following");
                    XSSFCell cell1_6 = row.createCell(5);
                    cell1_6.setCellValue("Number of stars");
                    k++;
                } else {
                    int l = 1;
                    for (int i = 0; i < GithubData.idname.size(); i++) {
                        XSSFRow row = sheet.createRow(l);
                        XSSFCell cell2_1 = row.createCell(0);
                        cell2_1.setCellValue(l);
                        XSSFCell cell2_2 = row.createCell(1);
                        cell2_2.setCellValue(GithubData.idname.get(i).getData0());
                        XSSFCell cell2_3 = row.createCell(2);
                        cell2_3.setCellValue(GithubRepo.repoCount.get(i).getData0());
                        XSSFCell cell2_4 = row.createCell(3);
                        cell2_4.setCellValue(GithubFollower.followerCount.get(i).getData0());
                        XSSFCell cell2_5 = row.createCell(4);
                        cell2_5.setCellValue(GithubFollowing.followingCount.get(i).getData0());
                        XSSFCell cell2_6 = row.createCell(5);
                        cell2_6.setCellValue(GithubStar.starsCount.get(i).getData0());

                        l++;
                    }
                    k++;
                }
            } while (k < 2);
            FileOutputStream outputFile = new FileOutputStream(excelFile);
            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
            System.out.println(excelFile + " Successfully Created.");
        } catch (IOException e) {
            System.out.println("Error Fail To Write Data!");
        }
    }
}
