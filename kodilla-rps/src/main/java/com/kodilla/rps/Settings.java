package com.kodilla.rps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Settings {
    String confFileName = System.getProperty("user.home") + System.getProperty("file.separator") + "rps.conf";
    String computerStrategy = "random";

    private List<String> moveNames = new ArrayList<>();

    public int getMovesCount() {
        return moveNames.size();
    }

    public String getMoveName(int i) {
        if (i<0 || i>=moveNames.size()) {
            throw new IndexOutOfBoundsException();
        }
        return moveNames.get(i);
    }

    public String getResultDescription(char playerMove, char computerMove) {
        if (playerMove == computerMove) {
            return "";
        }
        if (playerMove == '1' && computerMove == '2' || playerMove == '2' && computerMove == 1) {
            return "Paper covers rock.";
        }
        if (playerMove == '1' && computerMove == '3' || playerMove == '3' && computerMove == 1) {
            return "Rock crushes scissors.";
        } else {
            return "Scissors cut paper.";
        }
    }

    public String getMoveName(char move) {
        return moveNames.get(Integer.parseInt(Character.toString(move)) - 1);
    }

    public int getPlayerScore(char playerMove, char computerMove) {
        if (playerMove == computerMove) return 0;
        int playerNumberMove = Integer.parseInt(Character.toString(playerMove));
        int computerNumberMove = Integer.parseInt(Character.toString(computerMove));
        if ((3 + playerNumberMove - computerNumberMove) % 3 == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public ComputerPlayer getComputerPlayer() {
        return new RandomPlayer(3);
    }

    public void readSettings() {
        restoreDefaults();
    }

    public void saveSettings() {
        try {
            FileWriter output = new FileWriter(confFileName);
            Properties properties = new Properties();
            properties.setProperty("numberOfMoves", Integer.toString(getMovesCount()));
            for(int i = 0; i<getMovesCount(); i++) {
                properties.setProperty("move" + (i+1), getMoveName(i));
            }
            for(int i=1; i<getMovesCount(); i++)
                for(int j=i+1; j<=getMovesCount(); j++) {
                    properties.setProperty("result" + i + j,
                            Integer.toString(getPlayerScore(
                                    Integer.toString(i).charAt(0), Integer.toString(j).charAt(0))));
                    properties.setProperty("result" + i + j + "Description",
                            getResultDescription(Integer.toString(i).charAt(0), Integer.toString(j).charAt(0)));

                }
            properties.setProperty("computerStrategy", "random");
            properties.store(output, "Configuration file for rock-paper-scissors game.");
            if (output != null) output.close();
            System.out.println("Settings saved to file " + confFileName);
        } catch (IOException e) {
            System.out.println("Sorry, settings have not been saved. There is a problem with opening settings file");
        }
    }

    public void restoreDefaults() {
        moveNames.clear();
        moveNames.add("rock");
        moveNames.add("paper");
        moveNames.add("scissors");
    }

    public void modify() {
        System.out.println("NOT IMPLEMENTED: Settings.modify");
    }


}
