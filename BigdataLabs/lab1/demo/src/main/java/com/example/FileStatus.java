package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class FileStatus {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        FileSystem fs;
        Path fi = new Path("/user/root/input/purchases.txt");
        if(args.length < 1) {
            System.out.println("Vous avez oublié le chemin du fichier");
            System.exit(1);
        }
        
        try {
            fs = FileSystem.get(config);
            Path path = new Path(args[0]);
            org.apache.hadoop.fs.FileStatus status = fs.getFileStatus(path);
            
            System.out.println("Informations du fichier: " + path);
            System.out.println("Taille: " + status.getLen() + " octets");
            System.out.println("Est un répertoire: " + status.isDirectory());
            System.out.println("Propriétaire: " + status.getOwner());
            System.out.println("Groupe: " + status.getGroup());
            System.out.println("Permissions: " + status.getPermission());
            BlockLocation[] blocks = fs.getFileBlockLocations(status, 0, status.getLen());
            for (BlockLocation block : blocks) {
                String[] hosts = block.getHosts();
                System.out.println(" Bloc offset " + block.getOffset() + " length " + block.getLength() + " is located on hosts: " + String.join(", ", hosts));
                System.out.println("Block length: " + block.getLength());


            }

fs.rename(fi, new Path("/user/root/input", "achats.txt"));

        } catch (Exception e) {
            System.err.println("Erreur lors de l'accès au fichier: " + e.getMessage());
            System.exit(1);
        }
    }
}