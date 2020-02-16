package ba.unsa.etf.rpr;

public class Heroj extends Igrac{
    private double odbrambeniPoeni;

    public Heroj(String nadimak, double odbrambeniPoeni) {
        super(nadimak);
        this.odbrambeniPoeni = odbrambeniPoeni;
    }

    public double getOdbrambeniPoeni() {
        return odbrambeniPoeni;
    }

    public void setOdbrambeniPoeni(double odbrambeniPoeni) {
        this.odbrambeniPoeni = odbrambeniPoeni;
    }

    @Override
    public void napadni(String nazivNapada, Igrac meta, double koeficijent) throws IlegalanNapad {
        provjeriNapad(nazivNapada, meta);
        Napad napad = dajNapadIzNaziva(nazivNapada);
        meta.primiNapad(napad, koeficijent);
    }

    @Override
    public void primiNapad(Napad napad, double koeficijent) {
        if(napad.getDjelovanje() * koeficijent > getOdbrambeniPoeni()){
            setZivotniPoeni(getZivotniPoeni() - (napad.getDjelovanje() * koeficijent - getOdbrambeniPoeni()));

            if(getZivotniPoeni() < 0){
                setZivotniPoeni(0);
            }
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
        return meta instanceof Heroj;
    }
}
