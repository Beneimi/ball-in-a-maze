package Dao;

import Model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedGame {
    Game game;
    String name;
}
