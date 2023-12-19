package dal;

import be.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO implements IPlaylistDAO {

    private final ConnectionManager connectionManager;

    public PlaylistDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Playlist getPlaylist(int id) {
        try (Connection con = connectionManager.getConnection()) {
            String sql = "SELECT * FROM Playlists WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Playlist(id, name);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting playlist from the database", e);
        }
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        String sql = "INSERT INTO Playlists (name) VALUES (?)";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, playlist.getName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding playlist failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    playlist.setId(generatedId);
                } else {
                    throw new SQLException("Adding playlist failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding playlist to the database", e);
        }
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        String sql = "UPDATE Playlists SET name=? WHERE id=?";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, playlist.getName());
            pstmt.setInt(2, playlist.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating playlist failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating playlist in the database", e);
        }
    }

    @Override
    public void deletePlaylist(int id) {
        String sql = "DELETE FROM Playlists WHERE id=?";

        try (Connection con = connectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting playlist from the database", e);
        }
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();

        try (Connection con = connectionManager.getConnection()) {
            String sql = "SELECT * FROM Playlists";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Playlist playlist = new Playlist(id, name);
                playlists.add(playlist);
            }
            return playlists;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all playlists from the database", e);
        }
    }
}
