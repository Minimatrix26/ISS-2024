package org.example.iss_hospital_v2.Repo;

import org.example.iss_hospital_v2.Model.Medicament;
import org.example.iss_hospital_v2.Model.Sectie;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class SectieRepo {
    private String JDBC_URL = "jdbc:sqlite:spital.db";

    private Connection connection;

    public SectieRepo() {
        openConnection();
        createTable();
    }

    private void createTable() {
        try(final Statement stmt = connection.createStatement()){
            stmt.execute("CREATE TABLE IF NOT EXISTS sectii(id int, nume varchar(30));");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try{
            if(connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sectie> getAll(){
        ArrayList<Sectie> sectii = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sectii;")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String nume = rs.getString(2);
                Sectie sectie = new Sectie(id, nume);
                sectii.add(sectie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sectii;
    }

    public void add(Sectie sectie){
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO sectii VALUES (?,?);")){
            stmt.setInt(1, sectie.getId());
            stmt.setString(2, sectie.getNume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM sectii WHERE id = ?;")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SectieRepo sectieRepo = new SectieRepo();

/*
        Sectie sectie1 = new Sectie(1, "Cardiologie");
        Sectie sectie2 = new Sectie(2, "ATI");
        Sectie sectie3 = new Sectie(3, "Test");

        sectieRepo.add(sectie1);
        sectieRepo.add(sectie2);
        sectieRepo.add(sectie3);

        sectieRepo.delete(3);
*/

        for (var s : sectieRepo.getAll()){
            System.out.println(s);
        }

    }
}
