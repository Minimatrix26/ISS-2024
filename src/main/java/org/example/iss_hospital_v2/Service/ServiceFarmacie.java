package org.example.iss_hospital_v2.Service;

import org.example.iss_hospital_v2.Model.Comanda;
import org.example.iss_hospital_v2.Model.Medicament;
import org.example.iss_hospital_v2.Model.Sectie;
import org.example.iss_hospital_v2.Repo.ComandaRepo;
import org.example.iss_hospital_v2.Repo.MedicamentRepo;
import org.example.iss_hospital_v2.Repo.SectieRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceFarmacie {
    private MedicamentRepo medicamentRepo;
    private SectieRepo sectieRepo;
    private ComandaRepo comandaRepo;

    public ServiceFarmacie(MedicamentRepo medicamentRepo, SectieRepo sectieRepo, ComandaRepo comandaRepo) {
        this.medicamentRepo = medicamentRepo;
        this.sectieRepo = sectieRepo;
        this.comandaRepo = comandaRepo;
    }

    public ArrayList<Comanda> getAllComenzi() {
        return comandaRepo.getAll();
    }

    public ArrayList<Medicament> getAllMedicamente() {
        return medicamentRepo.getAll();
    }

    public ArrayList<Sectie> getAllSectii() {
        return sectieRepo.getAll();
    }

    public void adaugaComanda(Comanda comanda) {
        comandaRepo.add(comanda);
    }

    public void onoreazaComanda(int comandaId) throws SQLException {
        Comanda comanda = comandaRepo.getById(comandaId);
        if(comanda != null && !comanda.isOnorata()) {
            comandaRepo.update(comandaId);
        }
    }
}
