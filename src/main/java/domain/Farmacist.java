package domain;

public class Farmacist {

    private Long id;
    private String nume;
    private String username;

    public Farmacist() {

    }

    public Farmacist(Long id, String nume, String username) {
        this.id = id;
        this.nume = nume;
        this.username = username;
    }

    public Farmacist(String nume, String username) {
        this.id = 0L;
        this.nume = nume;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Farmacist{" +
                "id=" + id +
                ", name='" + nume + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
