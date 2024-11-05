package model;

/**
 *
 */
public class Time {
    private int id;
    private String nome;

    public Time(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Time(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Time{" + "nome='" + nome + '\'' + '}';
    }

}
