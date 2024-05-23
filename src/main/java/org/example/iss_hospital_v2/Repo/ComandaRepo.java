package org.example.iss_hospital_v2.Repo;

import org.example.iss_hospital_v2.Model.Comanda;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class ComandaRepo {
    private String JDBC_URL = "jdbc:sqlite:spital.db";

    private Connection connection;

    public ComandaRepo() {
        openConnection();
        createTable();
        deleteOnorate();
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

    private void createTable() {
        try(final Statement stmt= connection.createStatement()){
            stmt.execute("CREATE TABLE IF NOT EXISTS comenzi(id int, sectieId int, medicamentId int, cantitate int, onorata boolean);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comanda> getAll(){
        ArrayList<Comanda> comenzi = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM comenzi;")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                int sectieId = rs.getInt(2);
                int medicamentId = rs.getInt(3);
                int cantitate = rs.getInt(4);
                boolean onorata = rs.getBoolean(5);
                Comanda comanda = new Comanda(id, sectieId, medicamentId, cantitate, onorata);
                comenzi.add(comanda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comenzi;
    }

    public void add(Comanda comanda) {
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO comenzi VALUES (?, ?, ?, ?, ?);")) {
            stmt.setInt(1, comanda.getId());
            stmt.setInt(2, comanda.getSectieId());
            stmt.setInt(3, comanda.getMedicamentId());
            stmt.setInt(4, comanda.getCantitate());
            stmt.setBoolean(5, comanda.isOnorata());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM comenzi WHERE id = ?;")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOnorate() {
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM comenzi WHERE onorata = ?;")) {
            stmt.setBoolean(1, true);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int idToUpdate) throws SQLException {
        Comanda comandaToUpdate = getById(idToUpdate);
        comandaToUpdate.setOnorata(true);
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE comenzi SET onorata = ? WHERE id = ?;")) {
            stmt.setBoolean(1, comandaToUpdate.isOnorata());
            stmt.setInt(2, comandaToUpdate.getId());
            stmt.executeUpdate();
        }

    }

    public Comanda getById(int id) {
        ArrayList<Comanda> comenzi = getAll();
        Comanda comandaFound = null;
        for(Comanda comanda : comenzi) {
            if(comanda.getId() == id) {
                comandaFound = comanda;
            }
        }
        return comandaFound;
    }

    public static void main(String[] args) throws SQLException {
        ComandaRepo comandaRepo = new ComandaRepo();

        //comandaRepo.add(new Comanda(1, 1, 1, 10, false));
        //comandaRepo.add(new Comanda(2, 2, 2, 10, false));

        //comandaRepo.update(1);
        //Comanda someComanda = comandaRepo.getById(1);
        //System.out.println(someComanda);

        ArrayList<Comanda> comenzi = comandaRepo.getAll();
        for(Comanda comanda : comenzi) {
            System.out.println(comanda);
        }
    }
}
