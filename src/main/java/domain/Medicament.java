package domain;

public class Medicament {

    private Long id;
    private String denumire;
    private String substantaActiva;
    private Double gramaj;
    private int stoc;

    public Medicament() {

    }


    public Medicament(Long id, String denumire, String substantaActiva, Double gramaj, int stoc) {
        this.id = id;
        this.denumire = denumire;
        this.substantaActiva = substantaActiva;
        this.gramaj = gramaj;
        this.stoc = stoc;
    }

    public Medicament(String denumire, String substantaActiva, Double gramaj, int stoc) {
        this.id = 0L;
        this.denumire = denumire;
        this.substantaActiva = substantaActiva;
        this.gramaj = gramaj;
        this.stoc = stoc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getSubstantaActiva() {
        return substantaActiva;
    }

    public Double getGramaj() {
        return gramaj;
    }

    public int getStoc() {
        return stoc;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setSubstantaActiva(String substantaActiva) {
        this.substantaActiva = substantaActiva;
    }

    public void setGramaj(Double gramaj) {
        this.gramaj = gramaj;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "Medicament - " + denumire + " " + " " + substantaActiva + " " + gramaj + " " + stoc;
    }

}
