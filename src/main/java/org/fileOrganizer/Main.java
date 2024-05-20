package org.fileOrganizer;

public class Main {
    public static void main(String[] args) {
        Organizer organizer = new Organizer("/home/cleverson/Desktop/downloads");

        organizer.loop(500);
    }
}
