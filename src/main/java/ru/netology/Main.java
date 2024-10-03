package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %s\n", clientSocket.getPort());

                    String name = aboutName(out, in);
                    aboutChild(out, in, name);
                    int age = aboutAge(out, in);
                    aboutGames(out, in, name, age);
                }
            }
        }
    }

    private static void aboutGames(PrintWriter out, BufferedReader in, String name, int age) throws IOException {
        if (age < 18) {
            out.println("Do you like to play games? (yes/no)");
            final String playGames = in.readLine();
            switch (playGames) {
                case "yes":
                    out.printf(format("Well, %s, let's play!", name));
                    break;
                case "no":
                    out.printf(format("It's a pity, %s!", name));
                    break;
            }
            System.out.println(playGames);
        } else {
            out.println("Do you like to play video games? (yes/no)");
            final String playVideoGames = in.readLine();
            switch (playVideoGames) {
                case "yes":
                    out.printf(format("Well, %s, let's go!", name));
                    break;
                case "no":
                    out.printf(format("Well, then bye, %s!", name));
                    break;
            }
            System.out.println(playVideoGames);
        }
    }

    private static int aboutAge(PrintWriter out, BufferedReader in) throws IOException {
        out.println("How old are you?");
        final int age = Integer.parseInt(in.readLine());
        System.out.println(age);
        return age;
    }

    private static void aboutChild(PrintWriter out, BufferedReader in, String name) throws IOException {
        out.println("Are you a child? (yes/no)");
        final String child = in.readLine();
        switch (child) {
            case "yes":
                out.printf(format("Welcome to the kids area, %s! Let's play!\n", name));
                break;
            case "no":
                out.printf(format("Welcome to the adult zone, %s! Have a good rest, or a good working day!\n", name));
                break;
        }
        System.out.println(child);
    }

    private static String aboutName(PrintWriter out, BufferedReader in) throws IOException {
        out.println("Write your name");
        final String name = in.readLine();
        System.out.println(name);
        return name;
    }
}