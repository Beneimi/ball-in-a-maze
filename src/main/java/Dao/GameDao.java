package Dao;

import Model.Game;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.tinylog.Logger;
import java.io.*;
import java.util.stream.Collectors;

/***
 * Class for accessing game data
 */
public class GameDao {

    private Gson gson = new Gson();

    /***
     * Loads saved games from disk
     * @return saved games
     * @throws IOException thrown when the file is not accessible
     */
    public SavedGame[] GetGames() throws IOException {
        return gson.fromJson( ReadFile(), SavedGame[].class);
    }

    /***
     * Saves a game to disk
     * @param name name of the game
     * @param game the <code>Game</code> to be saved
     * @throws IOException thrown when the file is not accessible
     */
    public void SaveGame(String name, Game game) throws IOException {
        SavedGame[] savedGames = GetGames();
        WriteFile( gson.toJson(ArrayUtils.add(savedGames,new SavedGame(game,name))) );
        Logger.info("Game saved");
    }


    private String ReadFile() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/savedGames/saved.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Can not read level data");
        }
    }

    private void WriteFile(String content) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/savedGames/saved.json").getFile()))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Can not write level data");
        }
    }

}
