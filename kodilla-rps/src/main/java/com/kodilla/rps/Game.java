package com.kodilla.rps;

public class Game {

    private String playerName;
    private Settings settings;
    private int winningScore;
    private Menu finalGameMenu = new Menu("Available options: ", "Enter your choice: ");
    private Menu roundMenu = new Menu("Make a move or finish the game:", "Enter your choice: ");
    private Menu finishGameMenu = new Menu("Are you sure you want to finish playing?",
            "Enter your choice: ");;
    private int computerScore;
    private int playerScore;
    private ComputerPlayer computerPlayer;

    public Game(String playerName, Settings settings) {
        this.playerName = playerName;
        this.settings = settings;
        this.computerPlayer = settings.getComputerPlayer();

        finalGameMenu.addItem(new MenuItem('n', "start new game", () -> {}));
        finalGameMenu.addItem(new MenuItem('z', "finish playing", () -> {throw new EndGameException();}));
        finalGameMenu.addItem(new MenuItem('x', "exit the application",
                (new EndApplicationMenu(finalGameMenu))::execute));

        for(int i=0; i<settings.getMovesCount(); i++) {
            char moveKey = Integer.toString(i+1).charAt(0);
            roundMenu.addItem(new MenuItem(moveKey, settings.getMoveName(i), () -> {playRound(moveKey);}));
        }
        roundMenu.addItem(new MenuItem('z', "finish current game", finishGameMenu::execute));
        roundMenu.addItem(new MenuItem('x', "exit the application",
                (new EndApplicationMenu(roundMenu))::execute));


        finishGameMenu.addItem(new MenuItem('y', "finish playing",
                () ->{throw new EndGameException();}));
        finishGameMenu.addItem(new MenuItem('n', "return to the game", roundMenu::execute));
    }

    private void playRound(char playerMove) {
        char computerMove = computerPlayer.nextMove(playerMove);
        int roundResult = settings.getPlayerScore(playerMove, computerMove);
        playerScore += Integer.max(roundResult, 0);
        computerScore -= Integer.min(roundResult, 0);

        System.out.print("Your move: " + settings.getMoveName(playerMove)
                + ", computer's move: " + settings.getMoveName(computerMove) + ". "
                + settings.getResultDescription(playerMove, computerMove) + " ");
        if (roundResult == 0) {
            System.out.println("Result is a draw 0:0");
        } else {
            System.out.println(((roundResult > 0) ? playerName : "Computer")
                    + " wins this round with "
                    + Math.abs(roundResult)
                    + (Math.abs(roundResult) > 1 ? " points" : " point"));
        }

        if (computerScore < winningScore && playerScore < winningScore) {
            System.out.println("Current result of the game "+ playerName + ":computer is " + playerScore
                    + ":" + computerScore);
            System.out.println();
        }
    }

    public void playGame() throws EndApplicationException {

        try {
            while(true) {
                computerScore = 0;
                playerScore = 0;
                String getWinningScore = Input.getInput(
                        "Enter minimum score which ends the game:", "[1-9][0-9]*");
                winningScore = Integer.parseInt(getWinningScore);

                do {
                    roundMenu.execute();
                } while (computerScore < winningScore && playerScore < winningScore);

                System.out.println("\nMinimum required score has been reached and the game is finished");
                System.out.print("The result of the game is ");
                if (computerScore==playerScore) {
                    System.out.println("a draw " + computerScore + ":" + playerScore + "\n");
                } else if (playerScore > computerScore) {
                    System.out.println(playerScore + ":" + computerScore
                            + " for " + playerName + ". Congratulations!\n");
                } else {
                    System.out.println(computerScore + ":" + playerScore + " for computer.\n");
                }

                finalGameMenu.execute();
            }
        } catch (EndGameException e) {}
    }
}
