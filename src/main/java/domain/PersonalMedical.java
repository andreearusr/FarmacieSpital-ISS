package domain;

public class PersonalMedical {

    private Long id;
    private String nume;
    private String username;
    private Sectie sectie;

    public PersonalMedical() {

    }

    public PersonalMedical(Long id, String nume, String username, Sectie sectie) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.sectie = sectie;
    }

    public PersonalMedical(String nume, String username, Sectie sectie) {
        this.id = 0L;
        this.nume = nume;
        this.username = username;
        this.sectie = sectie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sectie getSectie() {
        return sectie;
    }

    public void setSectie(Sectie sectie) {
        this.sectie = sectie;
    }

    public String getNume() {
        return nume;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "PersonalMedical{" +
                "id=" + id +
                '}';
    }
}
