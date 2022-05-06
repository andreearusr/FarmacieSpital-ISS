package domain;

public class Comanda {

    private Long id;
    private int cantitate;
    private Medicament medicament;
    private Sectie sectie;
    private String status;

    public Comanda() {

    }

    public Comanda(Long id, int cantitate, Medicament medicament, Sectie sectie, String status) {
        this.id = id;
        this.cantitate = cantitate;
        this.status = status;
        this.medicament = medicament;
        this.sectie = sectie;
    }

    public Comanda(int cantitate, Medicament medicament, Sectie sectie,  String status) {
        this.id = 0L;
        this.cantitate = cantitate;
        this.status = status;
        this.medicament = medicament;
        this.sectie = sectie;

    }

    public Comanda(int cantitate, Medicament medicament, Sectie sectie) {
        this.id = 0L;
        this.cantitate = cantitate;
        this.status = "IN_ASTEPTARE";
        this.medicament = medicament;
        this.sectie = sectie;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getStatus() {
        return status;
    }

    public Sectie getSectie() {
        return sectie;
    }

    public void setSectie(Sectie sectie) {
        this.sectie = sectie;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }


    @Override
    public String toString() {
        return "Comanda - " + cantitate + " " + status + " " + medicament.getDenumire();
    }
}
