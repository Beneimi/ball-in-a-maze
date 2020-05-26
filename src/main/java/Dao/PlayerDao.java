package Dao;

import Model.Highscore;
import Model.Player;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.tinylog.Logger;
import java.io.*;
import java.util.stream.Collectors;

/***
 * Class for accessing player data
 */
public class PlayerDao {

    private Gson gson = new Gson();

    public Player[] GetPlayers() throws IOException {
        return gson.fromJson( ReadFile(), Player[].class);
    }

    /***
     * Saves score to disk
     * @param playerName name of the player
     * @param levelName name of the level
     * @param score score to be saved
     * @throws IOException thrown when the file is not accessible
     */
    public void SaveScore(String playerName, String levelName, int score) throws IOException {
        Player[] players = GetPlayers();
        for (Player player: players) {
            if(playerName.equals(player.getName())){
                player.addHighscore(levelName,score);
                WriteFile( gson.toJson(players) );
                Logger.info("Score saved");
                return;
            }
        }
        WriteFile( gson.toJson( ArrayUtils.add(players,new Player(playerName, new Highscore(levelName,score))) ));
        Logger.info("Score saved");
    }

    /***
     * Loads <code>Highscore</code>s of a <code>Player</code> from disk
     * @return the saved highscores
     * @throws IOException thrown when the file is not accessible
     */
    public Highscore[] getHighscoresOf(String playerName) throws IOException,IllegalArgumentException{
        Player[] players = this.GetPlayers();
        for (Player p: players) {
            if(p.getName().equals(playerName)){
                return p.getHighscores();
            }
        }
        throw new IllegalArgumentException("User does not exist with the given name");
    }


    private String ReadFile() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/players.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Can not read player data");
        }
    }

    private void WriteFile(String content) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/players.json").getFile()))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Can not write player data");
        }
    }

}
