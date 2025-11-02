package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;

public class ReadHDFS {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        FileSystem fs = FileSystem.get(config);
        Path nomcomplet = new Path("/user/root/input/purchases.txt");

        try (FSDataInputStream inStream = fs.open(nomcomplet);
             BufferedReader br = new BufferedReader(new InputStreamReader(inStream))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
        } finally {
            if (fs != null) {
                fs.close();
            }
        }
    }
}
