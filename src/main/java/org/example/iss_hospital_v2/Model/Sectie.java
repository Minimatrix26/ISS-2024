package org.example.iss_hospital_v2.Model;

public class Sectie {
    private int id;
    private String nume;

    public Sectie(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Sectie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
