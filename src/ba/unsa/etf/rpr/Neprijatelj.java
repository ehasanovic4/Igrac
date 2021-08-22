package ba.unsa.etf.rpr;

public class Neprijatelj extends Igrac {

    public Neprijatelj(String nadimak) {
        super(nadimak);
    }

    @Override
    public void primiNapad(Napad napad) {
        this.setZivotniPoeni(getZivotniPoeni()-napad.getDjelovanje());
        if(getZivotniPoeni()<0) setZivotniPoeni(0);
    }

    @Override
    public void napadni(String nazivNapada, Igrac igracKogTrebaNapasti) throws IlegalanNapad {
        napadni(nazivNapada, igracKogTrebaNapasti, 1);
    }

    @Override
    public void primiNapad(Napad napad, double koeficijent) {
        this.setZivotniPoeni(getZivotniPoeni()-napad.getDjelovanje()*koeficijent);
        if(getZivotniPoeni()<0) setZivotniPoeni(0);
    }

    @Override
    public void napadni(String nazivNapada, Igrac igracKogTrebaNapasti, double koeficijent) throws IlegalanNapad {
        boolean postoji=false;
        for(Napad n : getNapadi()){
            if(n.getNazivNapada().equals(nazivNapada)) postoji=true;
        }
        if(!postoji) throw new IlegalanNapad(getNadimak()+" ne može izvršiti napad "+nazivNapada);
        if(igracKogTrebaNapasti.getZivotniPoeni()==0) throw new IlegalanNapad("Ovaj igrač je već završio igru");
        if(igracKogTrebaNapasti instanceof Neprijatelj) throw new IlegalanNapad("Nije moguće izvršiti napad na prijatelja");
        Napad napad = dajNapadIzNaziva(nazivNapada);
        igracKogTrebaNapasti.primiNapad(napad,koeficijent);
    }

    private Napad dajNapadIzNaziva(String nazivNapada) {
        for(Napad n : getNapadi()){
            if(n.getNazivNapada().equals(nazivNapada)) return n;
        }
        return null;
    }
}
