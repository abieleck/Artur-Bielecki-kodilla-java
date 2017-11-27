package com.kodilla.rps;

public class RpsRunner {

    Settings settings = new Settings();
    Menu topMenu = new Menu("Available options:","Type 'n' to start new game or choose other option:");
    boolean end;
    String playerName;

    public static void main(String[] args) {
        RpsRunner runner = new RpsRunner();
        runner.run();
    }

    public RpsRunner() {
        Input.open();
        settings.readSettings();
        System.out.println("Welcome to game rock-paper-scissors");
        playerName = Input.getInput("Enter your name:", ".+");

        topMenu.addItem(new MenuItem('n', "start new game",
                () -> {(new Game(playerName, settings)).playGame();}));
        topMenu.addItem(new MenuItem('u', "change settings", settings::modify));
        topMenu.addItem(new MenuItem('x', "exit application", (new EndApplicationMenu(topMenu)::execute)));
    }

    public void run() {

        // a similar loop but without use of 'end' variable is in method Game::playGame
        while(!end) {
            try {
                topMenu.execute();
            } catch (EndApplicationException e) {
                end = true;
            } catch (EndGameException e) {}
        }

        Input.close();
        System.out.println("Have a nice day :-)");
   }
}
