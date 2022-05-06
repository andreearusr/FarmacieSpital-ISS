package service;

import domain.*;
import repository.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service {
    private MedicamentRepository medicamentRepository;
    private ComandaRepository comandaRepository;
    private FarmacistRepository farmacistRepository;
    private PersonalMedicalRepository personalMedicalRepository;
    private Map<Long, PersonalMedical> PersonaleMedicale;
    private Map<Long, Farmacist> Farmacisti;

    public Service(MedicamentRepository medicamentRepository, ComandaRepository comandaRepository, FarmacistRepository farmacistRepository, PersonalMedicalRepository personalMedicalRepository) {
        this.medicamentRepository = medicamentRepository;
        this.comandaRepository = comandaRepository;
        this.farmacistRepository = farmacistRepository;
        this.personalMedicalRepository = personalMedicalRepository;
        PersonaleMedicale = new ConcurrentHashMap<>();
        Farmacisti = new ConcurrentHashMap<>();
    }

    public List<Comanda> getComenziSectie(Long idSectie) {
        return comandaRepository.getComenziSectie(idSectie);
    }

    public void inregistrareComanda(Comanda comanda) {
        comandaRepository.save(comanda);
    }

    public List<Medicament> getMedicamente() {
        return medicamentRepository.findAll();
    }

    public void stergeComanda(Comanda comanda) {
        comandaRepository.delete(comanda.getId());
    }

    public void modificaComanda(Long id, Comanda comandaNoua) {
        comandaRepository.update(id, comandaNoua);
    }

    public void modificaMedicament(Long id, Medicament medicamentNou) {
        medicamentRepository.update(id, medicamentNou);
    }

    public PersonalMedical getPersonal(Long id) {
        return personalMedicalRepository.findOne(id);
    }

    public Farmacist getFarmacist(Long id) {
        return farmacistRepository.findOne(id);
    }

    public List<Comanda> getComenziInAsteptare() {
        return comandaRepository.getComenziInAsteptare();
    }

    public List<Comanda> getComenziOnorate() {
        return comandaRepository.getComenziOnorate();
    }

    public void logInPersonalMedical(PersonalMedical personalMedical) throws LogException {
        Long personalMedicalId = personalMedical.getId();
        if (personalMedicalId != null) {
            if (PersonaleMedicale.get(personalMedicalId) != null)
                throw new LogException("PersonalMedical deja logat.");
            PersonaleMedicale.put(personalMedicalId, personalMedical);
        } else
            throw new LogException("Authentication failed.");
    }

    public void logOutPersonalMedical(PersonalMedical personalMedical) throws LogException {
        PersonalMedical pm = PersonaleMedicale.remove(personalMedical.getId());
        if (pm == null)
            throw new LogException("Personalul Medical " + personalMedical.getId() + "nu este logat.");
    }

    public void logInFarmacist(Farmacist farmacist) throws LogException {
        Long farmacistId = farmacist.getId();
        if (farmacistId != null) {
            if (Farmacisti.get(farmacistId) != null)
                throw new LogException("Farmacistul este deja logat.");
            Farmacisti.put(farmacistId, farmacist);
        } else
            throw new LogException("Authentication failed.");
    }

    public void logOutFarmacist(Farmacist farmacist) throws LogException {
        Farmacist f = Farmacisti.remove(farmacist.getId());
        if (f == null)
            throw new LogException("Farmacistul " + farmacist.getId() + "nu este logat.");
    }


}
