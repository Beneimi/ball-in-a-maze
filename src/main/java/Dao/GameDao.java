package Dao;

import Model.Game;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class GameDao {

    private Gson gson = new Gson();

    public SavedGame[] GetGames() throws IOException {
        return gson.fromJson( ReadFile(), SavedGame[].class);
    }

    public void SaveGame(SavedGame game) throws IOException {
        SavedGame[] savedGames = GetGames();
        WriteFile( gson.toJson(ArrayUtils.add(savedGames,game)) );
    }


    private String ReadFile() throws IOException{
        try (BufferedReader reader = Files.newBufferedReader(Path.of(getClass().getResource("/savedGames/saved.json").toURI()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException | URISyntaxException x) {
            throw new IOException();
        }
    }

    private void WriteFile(String content) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(getClass().getResource("/savedGames/saved.json").toURI()))) {
            writer.write(content);
        } catch (IOException | URISyntaxException x) {
            throw new IOException();
        }
    }

}
