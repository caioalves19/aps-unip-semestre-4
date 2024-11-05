package model;

public class CampeonatoTime {
    private int idCampeonato;
    private int idTime;

    public CampeonatoTime(int idCampeonato, int idTime) {
        this.idCampeonato = idCampeonato;
        this.idTime = idTime;
    }

    int getIdCampeonato() {
        return idCampeonato;
    }

    int getIdTime() {
        return idTime;
    }
}
