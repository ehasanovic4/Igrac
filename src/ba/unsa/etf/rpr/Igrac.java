package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Igrac implements Comparable<Igrac>{
    private String nadimak;
    private double zivotniPoeni;
    private List<Napad> napadi = new ArrayList<>();

    public Igrac(String nadimak) {
        this.nadimak = nadimak;
        this.zivotniPoeni = 100;
    }

    public Igrac(){
        //sta ovdje
    }

    public abstract void napadni(String nazivNapada, Igrac meta) throws IlegalanNapad;
    public abstract void primiNapad(Napad napad);
    public abstract void napadni(String nazivNapada, Igrac meta, double koeficijent) throws IlegalanNapad;
    public abstract void primiNapad(Napad napad, double koeficijent);

    public void provjeriNapad(String nazivNapada, Igrac meta) throws IlegalanNapad {
        if(dajNapadIzNaziva(nazivNapada) == null){
            throw new IlegalanNapad(nadimak + " ne može izvršiti napad " + nazivNapada);
        }

        if(meta.getZivotniPoeni() == 0){
            throw new IlegalanNapad("Ovaj igrač je već završio igru");
        }

        if(izIstogTima(meta)){
            throw new IlegalanNapad("Nije moguće izvršiti napad na prijatelja");
        }
    }

    protected abstract boolean izIstogTima(Igrac meta);

    protected Napad dajNapadIzNaziva(String nazivNapada){
        List<Napad> pronadjeno = napadi.stream().filter(napad -> napad.getNazivNapada().equals(nazivNapada))
                .collect(Collectors.toList());
        return pronadjeno.isEmpty() ? null : pronadjeno.get(0);
    }

    public void registrujNapad(Napad napad){
        if (dajNapadIzNaziva(napad.getNazivNapada()) != null){
            throw new IllegalArgumentException("Napad sa ovim nazivom je već registrovan");
        }
        napadi.add(napad);
    }

    @Override
    public int compareTo(Igrac igrac){
        if (igrac.getZivotniPoeni() == getZivotniPoeni()){
            return getNadimak().compareTo(igrac.getNadimak());
            //TODO provjeriti poredak
        }

        return Double.compare(getZivotniPoeni(), igrac.getZivotniPoeni());
    }

    @Override
    public  String toString(){
        return nadimak + " (preostalo " + getZivotniPoeni() + " životnih poena)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Igrac igrac = (Igrac) o;

        return nadimak != null ? nadimak.equals(igrac.nadimak) : igrac.nadimak == null;
    }

    @Override
    public int hashCode() {
        return nadimak != null ? nadimak.hashCode() : 0;
    }

    public String getNadimak() {
        return nadimak;
    }

    public void setNadimak(String nadimak) {
        this.nadimak = nadimak;
    }

    public double getZivotniPoeni() {
        return zivotniPoeni;
    }

    public void setZivotniPoeni(double zivotniPoeni) {
        this.zivotniPoeni = zivotniPoeni;
    }

    public List<Napad> getNapadi() {
        return napadi;
    }

    public void setNapadi(List<Napad> napadi) {
        this.napadi = napadi;
    }
}
