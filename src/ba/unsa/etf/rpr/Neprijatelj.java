package ba.unsa.etf.rpr;

public class Neprijatelj extends Igrac {

    public Neprijatelj(String nadimak) {
        super(nadimak);
    }

    @Override
    public void napadni(String nazivNapada, Igrac meta, double koeficijent) throws IlegalanNapad {
        provjeriNapad(nazivNapada, meta);
        Napad napad = dajNapadIzNaziva(nazivNapada);
        meta.primiNapad(napad, koeficijent);
    }

    @Override
    public void primiNapad(Napad napad, double koeficijent) {
        setZivotniPoeni(getZivotniPoeni() - napad.getDjelovanje() * koeficijent);

        if(getZivotniPoeni() < 0){
            setZivotniPoeni(0);
        }
    }

    @Override
    public void napadni(String nazivNapada, Igrac meta) throws IlegalanNapad {
        napadni(nazivNapada, meta, 1);
    }

    @Override
    public void primiNapad(Napad napad) {
        primiNapad(napad, 1);
    }

    @Override
    protected boolean izIstogTima(Igrac meta) {
        return meta instanceof Neprijatelj;
    }
}
