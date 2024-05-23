package org.example.iss_hospital_v2.Repo;

import org.example.iss_hospital_v2.Model.Medicament;

import java.sql.*;
import java.util.ArrayList;
import org.sqlite.SQLiteDataSource;

public class MedicamentRepo {
    private String JDBC_URL = "jdbc:sqlite:spital.db";

    private Connection connection;

    public MedicamentRepo() {
        openConnection();
        createTable();
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

    public void closeConnection() {
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        try(final Statement stmt = connection.createStatement()){
            stmt.execute("CREATE TABLE IF NOT EXISTS medicamente(id int, nume varchar(30));");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Medicament> getAll(){
        ArrayList<Medicament> medicamente = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM medicamente;")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String nume = rs.getString(2);
                Medicament med = new Medicament(id, nume);
                medicamente.add(med);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicamente;
    }

    public void add(Medicament med){
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO medicamente VALUES (?,?);")){
            stmt.setInt(1, med.getId());
            stmt.setString(2, med.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM medicamente WHERE id = ?;")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MedicamentRepo medicamentRepo = new MedicamentRepo();
        //Medicament newMed1 = new Medicament(1, "Ibuprofen");
        //Medicament newMed2 = new Medicament(2, "Algocalmin");
        //Medicament newMed3 = new Medicament(3, "Test");
        //medicamentRepo.add(newMed1);
        //medicamentRepo.add(newMed2);
        //medicamentRepo.add(newMed3);

        //medicamentRepo.delete(3);

        ArrayList<Medicament> medicamentArrayList = new ArrayList<>();
        medicamentArrayList = medicamentRepo.getAll();

        for (var med : medicamentArrayList){
            System.out.println(med);
        }
        medicamentRepo.closeConnection();
    }
}
