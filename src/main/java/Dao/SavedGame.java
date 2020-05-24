package Dao;

import Model.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SavedGame {
    Game game;
    String name;
}
