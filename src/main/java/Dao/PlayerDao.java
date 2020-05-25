package Dao;

import Model.Highscore;
import Model.Player;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class PlayerDao {

    private Gson gson = new Gson();

    public Player[] GetPlayers() throws IOException {
        return gson.fromJson( ReadFile(), Player[].class);
    }

    public void SaveScore(String playerName, String levelName, int score) throws IOException {
        Player[] players = GetPlayers();
        for (Player player: players) {
            if(playerName.equals(player.getName())){
                player.addHighscore(levelName,score);
                WriteFile( gson.toJson(players) );
                return;
            }
        }

        WriteFile( gson.toJson( ArrayUtils.add(players,new Player(playerName, new Highscore(levelName,score))) ));
    }

    public Highscore[] getHighscoresOf(String playerName){
        Player[] players = new Player[]{};
        try {
            players = this.GetPlayers();
        }catch (IOException ex){
            // TODO: 2020. 05. 25. handle this
        }
        for (Player p: players) {
            if(p.getName().equals(playerName)){
                return p.getHighscores();
            }
        }
        throw new IllegalArgumentException();
    }


    private String ReadFile() throws IOException{
        try (BufferedReader reader = Files.newBufferedReader(Path.of(getClass().getResource("/players/players.json").toURI()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException | URISyntaxException x) {
            throw new IOException();
        }
    }

    private void WriteFile(String content) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(getClass().getResource("/players/players.json").toURI()))) {
            writer.write(content);
        } catch (IOException | URISyntaxException x) {
            throw new IOException();
        }
    }

}
