package ba.unsa.etf.rpr;

public class Napad {
    private String nazivNapada;
    private double djelovanje;

    public Napad(String nazivNapada, double djelovanje) {
        this.nazivNapada = nazivNapada;
        this.djelovanje = djelovanje;
    }

    public Napad() {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Napad napad = (Napad) o;
//
//        return nazivNapada != null ? nazivNapada.equals(napad.nazivNapada) : napad.nazivNapada == null;
//    }

    @Override
    public int hashCode() {
        return nazivNapada != null ? nazivNapada.hashCode() : 0;
    }
}
