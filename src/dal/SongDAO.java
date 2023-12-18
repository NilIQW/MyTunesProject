package dal;

import be.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO implements ISongDAO {
    private final ConnectionManager connectionManager;

    public SongDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Song getSong(int id) {
        try (Connection con = connectionManager.getConnection()) {
            String sql = "SELECT * FROM Songs WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int songId = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String filePath = rs.getString("filePath");
                String duration = rs.getString("duration");

                Song song = new Song(songId, title, artist, genre, filePath, duration);
                return song;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting song from the database", e);
        }
    }

    public void addSong(Song song) {
        String sql = "INSERT INTO Songs (title, artist, genre, filePath, duration) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getGenre());
            pstmt.setString(4, song.getFilePath());
            pstmt.setString(5, song.getDuration());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding song failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    song.setId(generatedId);
                } else {
                    throw new SQLException("Adding song failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding song to the database", e);
        }
    }

    public void updateSong(Song song) {
        String sql = "UPDATE Songs SET title=?, artist=?, genre=?, filePath=?, duration=? WHERE id=?";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getGenre());
            pstmt.setString(4, song.getFilePath());
            pstmt.setString(5, song.getDuration());
            pstmt.setInt(6, song.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating song failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating song in the database", e);
        }
    }

    public void deleteSong(int id) {
        String sql = "DELETE FROM Songs WHERE id=?";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting song from the database", e);
        }
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();

        try (Connection con = connectionManager.getConnection()) {
            String sql = "SELECT * FROM Songs";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String filePath = rs.getString("filePath");
                String duration = rs.getString("duration");

                Song song = new Song(id, title, artist, genre, filePath, duration);
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all songs from the database", e);
        }
    }
}
