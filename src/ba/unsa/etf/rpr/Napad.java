package ba.unsa.etf.rpr;

public class Napad {
    private String nazivNapada;
    private double djelovanje;

    public Napad(String nazivNapada, double djelovanje) {
        this.nazivNapada = nazivNapada;
        this.djelovanje = djelovanje;
    }

    public String getNazivNapada() {
        return nazivNapada;
    }

    public void setNazivNapada(String nazivNapada) {
        this.nazivNapada = nazivNapada;
    }

    public double getDjelovanje() {
        return djelovanje;
    }

    public void setDjelovanje(double djelovanje) {
        this.djelovanje = djelovanje;
    }
}
