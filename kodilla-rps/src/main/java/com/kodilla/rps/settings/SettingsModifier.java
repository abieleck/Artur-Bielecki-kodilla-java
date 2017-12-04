package com.kodilla.rps.settings;

import com.kodilla.rps.Input;
import com.kodilla.rps.menu.AppStatus;
import com.kodilla.rps.menu.Menu;
import com.kodilla.rps.menu.MenuItem;
import com.kodilla.rps.player.PlayerType;

public class SettingsModifier {

    private boolean settingsWereModified;
    private Settings newSettings;
    private Settings lastSavedSettings;

    private Menu topMenu = new Menu("Available options:", "Enter your choice: ");
    private Menu computerStrategyMenu = new Menu("Available computer strategies", "Enter your choice:");
    private Menu exitSaveMenu = new Menu(
            "Settings have been modified. Do you wish to save changes before exiting?",
            "Enter your choice: ");

    public SettingsModifier(Settings previousSettings) {
        this.newSettings = new Settings(previousSettings);
        this.lastSavedSettings = new Settings(previousSettings);
    }

    private void initializeMenu() {

        topMenu.addItem(new MenuItem('v', "view current settings", this::showSettings));
        topMenu.addItem(new MenuItem('d', "delete move", this::deleteMove));
        topMenu.addItem(new MenuItem('a', "add new move", this::addNewMove));
        topMenu.addItem(new MenuItem('r', "rename move", this::renameMove));
        topMenu.addItem(new MenuItem('c', "change score", this::changeScore));
        topMenu.addItem(new MenuItem('m', "modify description of result", this::modifyDescription));
        topMenu.addItem(new MenuItem('t', "Change computer's strategy", computerStrategyMenu::execute));
        topMenu.addItem(new MenuItem('f', "restore defaults", this::restoreDefaults));
        topMenu.addItem(new MenuItem('s', "save settings", this::saveSettings));
        topMenu.addItem(new MenuItem('q', "quit settings", this::quitSettings));

        PlayerType[] playerTypes = PlayerType.values();
        for(int i=0; i<playerTypes.length; i++) {
            int index = i;
            computerStrategyMenu.addItem(new MenuItem(Integer.toString(i+1).charAt(0),
                    playerTypes[i].name() + ": " + playerTypes[i].getDescription(),
                    () -> changeComputerStrategy(playerTypes[index])));
        }

        exitSaveMenu.addItem(new MenuItem('y', "save settings",
                () -> {saveSettings(); return AppStatus.END_SETTINGS;}));
        exitSaveMenu.addItem(new MenuItem('n', "do not save. Settings will not be modified",
                () -> AppStatus.END_SETTINGS));
        exitSaveMenu.addItem(new MenuItem('r', "do not save and return to editing settings",
                () -> AppStatus.NORMAL));
    }

    private AppStatus changeComputerStrategy(PlayerType newStrategy) {
        newSettings.setComputerStrategy(newStrategy);
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus restoreDefaults() {
        newSettings.restoreDefaults();
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus quitSettings() {
        if (settingsWereModified) {
            return exitSaveMenu.execute();
        } else {
            return AppStatus.END_SETTINGS;
        }
    }

    public Settings modifySettings() {

        initializeMenu();
        AppStatus appStatus;
        do {
            appStatus = topMenu.execute();
        } while (appStatus != AppStatus.END_SETTINGS);
        if (settingsWereModified) {
            return lastSavedSettings;
        }
        return newSettings;
    }

    private AppStatus showSettings() {
        System.out.println(newSettings.toString() + '\n');
        return AppStatus.NORMAL;
    }

    private AppStatus deleteMove() {
        if (newSettings.getMovesCount() < 4) {
            System.out.println("Currently there is a minimum allowed number of moves - 3. You cannot delete moves.");
        } else {
            System.out.println("Available moves:");
            System.out.println(newSettings.movesToString());
            String movesRange = "1-" + newSettings.getMovesCount();
            char moveToDelete = Input.getInput("Type the number of move you want to delete: ",
                    "[" + movesRange + "]").charAt(0);
            newSettings.deleteMove(moveToDelete);
            settingsWereModified = true;
        }
        return AppStatus.NORMAL;
    }

    private AppStatus addNewMove() {

        int movesCount = newSettings.getMovesCount();
        if (movesCount == 9) {
            System.out.println("There is already a maximum allowed number of moves - 9. You cannot add new moves.");
            return AppStatus.NORMAL;
        }

        String moveNumberRange = "1-" + (newSettings.getMovesCount() + 1);
        char move = Input.getInput(
                "Enter the number you would like to assign to the new move (" + moveNumberRange + "): ",
                "[" + moveNumberRange + "]").charAt(0);
        int moveNumber = Integer.parseInt(Character.toString(move));

        newSettings.insertBlankMove(moveNumber);

        String moveName;
        boolean moveAlreadyExists;
        do {
            moveName = Input.getInput("Enter the name for the new move: ", ".+");
            moveAlreadyExists = newSettings.moveNameDefined(moveName);
            if (moveAlreadyExists) {
                System.out.println("This name already exists. Please enter different name.");
            }
        } while (moveAlreadyExists);
        newSettings.replaceMoveName(moveNumber, moveName);

        for(int i=1; i<=movesCount + 1; i++) {
            if (i != moveNumber) {
                int move1Number = Integer.min(i, moveNumber);
                int move2Number = Integer.max(i, moveNumber);
                String move1Name = newSettings.getMoveName(move1Number);
                String move2Name = newSettings.getMoveName(move2Number);
                int score = Integer.parseInt(Input.getInput("Enter result for moves "
                        + move1Name + "-" + move2Name
                        + " (" + move1Number + "-" + move2Number + "); "
                        + "positive number means " + move1Name + " wins, negative means " + move2Name + " wins: " ,
                        "-?[1-9][0-9]*"));
                String description = Input.getInput("Enter description for moves "
                        + move1Name + "-" + move2Name + " (" + move1Number + "-" + move2Number + "); "
                        + "something like " + move1Name + " eat " + move2Name + ": ", ".+");
                newSettings.replaceScore(move1Number, move2Number, score);
                newSettings.replaceScoreDescription(move1Number, move2Number, description);
            }
        }
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus renameMove() {
        System.out.println("Current move names:");
        System.out.println(newSettings.movesToString());
        String movesRange = "1-" + newSettings.getMovesCount();
        int moveNumber = Integer.parseInt(Input.getInput("Enter the number of move you wish to rename ("
                        + movesRange + "): ","^[" + movesRange + "]$"));
        String newName = Input.getInput("Current name of the move is " + newSettings.getMoveName(moveNumber)
                + ". Enter new name for the move: ", ".+");
        newSettings.replaceMoveName(moveNumber, newName);
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus changeScore() {
        int move1Number;
        int move2Number;
        boolean identicalMoves;

        System.out.println("Current scores:");
        System.out.println(newSettings.scoresToString());
        String movesRange = "1-" + Integer.toString(newSettings.getMovesCount());
        do {
            move1Number = Integer.parseInt(Input.getInput("Enter number of the first move (" + movesRange
                    + "): ", "^[" + movesRange + "]$"));
            move2Number = Integer.parseInt(Input.getInput("Enter number of the second move (" + movesRange
                    + "): ", "^[" + movesRange + "]$"));
            identicalMoves = (move1Number == move2Number);
            if (identicalMoves) {
                System.out.println("For identical moves the result is always a draw 0:0. Enter different numbers");
            }
        } while (identicalMoves);
        int score = Integer.parseInt(Input.getInput("Enter score (positive score means "
                + newSettings.getMoveName(move1Number) + " wins, negative - " + newSettings.getMoveName(move2Number)
                + " wins):", "^-?[1-9][0-9]*$"));
        newSettings.replaceScore(move1Number, move2Number, score);
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus modifyDescription() {
        int move1Number;
        int move2Number;
        boolean identicalMoves;

        System.out.println("Current scores with descriptions:");
        System.out.println(newSettings.scoresToString());
        String movesRange = "1-" + Integer.toString(newSettings.getMovesCount());
        do {
            move1Number = Integer.parseInt(Input.getInput("Enter number of the first move (" + movesRange
                    + "): ", "^[" + movesRange + "]$"));
            move2Number = Integer.parseInt(Input.getInput("Enter number of the second move (" + movesRange
                    + "): ", "^[" + movesRange + "]$"));
            identicalMoves = (move1Number == move2Number);
            if (identicalMoves) {
                System.out.println("For identical moves the result is always a draw 0:0. Enter different numbers");
            }
        } while (identicalMoves);
        String description = Input.getInput("Enter description for game " + newSettings.getMoveName(move1Number)
                + "-" + newSettings.getMoveName(move2Number) + ":", ".+");
        newSettings.replaceScoreDescription(move1Number, move2Number, description);
        settingsWereModified = true;
        return AppStatus.NORMAL;
    }

    private AppStatus saveSettings() {
        (new SettingsStorage()).saveSettings(newSettings);
        settingsWereModified = false;
        lastSavedSettings = new Settings(newSettings);
        return AppStatus.NORMAL;
    }

}
