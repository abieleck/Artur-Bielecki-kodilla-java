package com.kodilla.rps;

import com.kodilla.rps.menu.AppStatus;
import com.kodilla.rps.menu.EndApplicationMenu;
import com.kodilla.rps.menu.Menu;
import com.kodilla.rps.menu.MenuItem;
import com.kodilla.rps.settings.Settings;
import com.kodilla.rps.settings.SettingsModifier;
import com.kodilla.rps.settings.SettingsStorage;

public class RpsRunner {

    Settings settings;
    Menu topMenu = new Menu("Available options:","Type 'n' to start new game or choose other option:");
    boolean end;
    String playerName;

    public static void main(String[] args) {
        RpsRunner runner = new RpsRunner();
        runner.run();
    }

    private void initializeMenu() {
        topMenu.addItem(new MenuItem('n', "start new game",
                () -> (new Game(playerName, settings)).playGame()));
        topMenu.addItem(new MenuItem('s', "change settings", this::modifySettings));
        topMenu.addItem(new MenuItem('x', "exit application", (new EndApplicationMenu(topMenu)::execute)));
    }

    public RpsRunner() {
        Input.open();
        initializeMenu();
    }

    public void run() {
        SettingsStorage settingsStorage = new SettingsStorage();
        settings = settingsStorage.readSettings();
        System.out.println("Welcome to game rock-paper-scissors");
        playerName = Input.getInput("Enter your name:", "^.+$");

        while(!end) {
            if (topMenu.execute() == AppStatus.END_APPLICATION) {
                end = true;
            }
        }

        Input.close();
        System.out.println("Have a nice day :-)");
   }

   private AppStatus modifySettings() {
       SettingsModifier settingsModifier = new SettingsModifier(settings);
       settings = settingsModifier.modifySettings();
       return AppStatus.NORMAL;
   }

}
