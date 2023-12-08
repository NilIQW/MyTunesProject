package dal;

import be.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO implements ISongDAO{
    private final ConnectionManager cm = new ConnectionManager();

    @Override
    public Song getSong(int id) {
        try(Connection con = cm.getConnection())
        {
            String sql = "SELECT * FROM Song WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String title     = rs.getString("title");
                String artist    = rs.getString("artist");

                Song newSong = new Song();
                return newSong;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteSong(int id) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Song WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateSong(Song song) {
        try(Connection con = cm.getConnection())
        {
            String sql = "UPDATE Song SET title=?, artist=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getGenre());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSong(Song song) {
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO Songs(title, artist) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();

        try(Connection con = cm.getConnection())
        {
            String sql = "SELECT * FROM Song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id          = rs.getInt("id");
                String name     = rs.getString("name");
                String email    = rs.getString("email");

                Song song = new Song();
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

