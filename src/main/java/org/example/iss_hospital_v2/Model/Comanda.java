package org.example.iss_hospital_v2.Model;

public class Comanda {
    private int id;
    private int sectieId;
    private int medicamentId;
    private int cantitate;
    private boolean onorata;

    public Comanda(int id, int sectieId, int medicamentId, int cantitate, boolean onorata) {
        this.id = id;
        this.sectieId = sectieId;
        this.medicamentId = medicamentId;
        this.cantitate = cantitate;
        this.onorata = onorata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectieId() {
        return sectieId;
    }

    public void setSectieId(int sectieId) {
        this.sectieId = sectieId;
    }

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public boolean isOnorata() {
        return onorata;
    }

    public void setOnorata(boolean onorata) {
        this.onorata = onorata;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", sectieId=" + sectieId +
                ", medicamentId=" + medicamentId +
                ", cantitate=" + cantitate +
                ", onorata=" + onorata +
                '}';
    }
}
