package domain;

public class Sectie {

    private Long id;
    private String nume;

    public Sectie() {

    }

    public Sectie(Long id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Sectie(String nume) {
        this.id = 0L;
        this.nume = nume;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
