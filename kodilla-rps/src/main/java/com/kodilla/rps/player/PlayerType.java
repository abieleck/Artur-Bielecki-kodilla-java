package com.kodilla.rps.player;

import com.kodilla.rps.settings.Settings;
import java.util.function.Function;

public enum PlayerType {
    RANDOM("Moves are randomly selected.", RandomPlayer::new),
    IMITATE("Imitate opponent's responses to previous move.", ImitatePlayer::new),
    AGGRESSIVE("Randomly choose from moves which can result in maximum score.", AggressivePlayer::new),
    CONSERVATIVE("Randomly choose from moves which produce minimum loss.", ConservativePlayer::new),
    CHEATING("With probability 50% choose winning move with maximum score, otherwise play randomly.", CheatingPlayer::new);

    private final String description;
    private final Function<Settings, ComputerPlayer> playerConstructor;

    PlayerType(String description, Function<Settings, ComputerPlayer> playerConstructor) {
        this.description = description;
        this.playerConstructor = playerConstructor;
    }

    public String getDescription() {
        return description;
    }

    public ComputerPlayer getNewComputerPlayer(Settings settings) {
        return playerConstructor.apply(settings);
    }


}
