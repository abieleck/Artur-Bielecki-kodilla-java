package com.kodilla.rps.settings;

import com.kodilla.rps.player.PlayerType;

import java.io.*;
import java.util.*;

public class SettingsStorage {

    String confFileName = System.getProperty("user.home") + System.getProperty("file.separator") + "rps.conf";

    public void saveSettings(Settings settings) {
        OutputStream outputFile = null;
        Writer writter = null;
        Properties properties = new Properties();
        try {
            int movesCount = settings.getMovesCount();
            properties.setProperty("numberOfMoves", Integer.toString(movesCount));
            for(int i = 1; i <= movesCount; i++) {
                properties.setProperty("move" + i, settings.getMoveName(i));
            }
            for(int i=1; i<movesCount; i++)
                for(int j=i+1; j<=movesCount; j++) {
                    properties.setProperty("result" + i + j, Integer.toString(settings.getScore(i, j)));
                    properties.setProperty("result" + i + j + "Description", settings.getResultDescription(i, j));
                }
            properties.setProperty("computerStrategy", settings.getComputerStrategy().name());
            outputFile = new FileOutputStream(confFileName);
            writter = new OutputStreamWriter(outputFile, "UTF-8");
            properties.store(writter, "Configuration file for rock-paper-scissors game.");
            System.out.println("Settings saved to file " + confFileName);
        } catch (IOException e) {
            System.out.println("Settings have not been saved. There is a problem with settings file");
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
                if (outputFile != null) {
                    outputFile.close();
                }
            } catch (IOException e) {
                System.out.println("Warning: there is a problem with saving settings file.");
            }
        }
    }

    public Settings readSettings() {

        Properties properties = new Properties();
        InputStream inputFile = null;

        try {
            inputFile = new FileInputStream(confFileName);
            Reader reader = new InputStreamReader(inputFile, "UTF-8");
            properties.load(reader);
            int movesCount =  Integer.parseInt(properties.getProperty("numberOfMoves", "0"));
            if (movesCount < 3 || movesCount > 9) {
                throw new CorruptedSettingsException();
            }
            Settings settings = new Settings(movesCount);
            String computerStrategy = properties.getProperty("computerStrategy", "");
            try {
                PlayerType playerType = PlayerType.valueOf(computerStrategy);
                settings.setComputerStrategy(playerType);
            } catch (IllegalArgumentException e) {
                throw new CorruptedSettingsException();
            }
            for(int i=1; i<= movesCount; i++) {
                String moveName = properties.getProperty("move" + i, "");
                if (moveName.length()==0 || settings.moveNameDefined(moveName)) {
                    throw new CorruptedSettingsException();
                }
                settings.replaceMoveName(i, moveName);
            }
            for(int i = 1; i < movesCount; i++)
                for(int j = i + 1; j <= movesCount; j++) {
                    try {
                        int score = Integer.parseInt(properties.getProperty("result" + i + j));
                        settings.replaceScore(i, j, score);
                    } catch (NullPointerException | NumberFormatException e) {
                        throw new CorruptedSettingsException();
                    }
                    String description = properties.getProperty("result" + i + j + "Description");
                    if (description == null) {
                        throw new CorruptedSettingsException();
                    }
                    settings.replaceScoreDescription(i, j, description);
                }
            return settings;
        } catch (IOException e) {
            System.out.println("Cannot open settings file " + confFileName + ". Using default settings.");
            return new Settings();
        } catch (CorruptedSettingsException e) {
            System.out.println("Settings file " + confFileName + " is corrupted. Using default settings.");
            return new Settings();
        } finally  {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {}
            }
        }
    }
}
