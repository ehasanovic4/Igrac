package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Igra {
    private List<Igrac> igraci = new ArrayList<>();

    public List<Igrac> getIgraci() {
        return igraci;
    }

    public void registrujIgraca(Igrac igrac){
        if (igraci.contains(igrac)){
            throw new IllegalArgumentException("Već je u igri igrač sa ovim nadimkom");
        }

        igraci.add(igrac);
    }

    public void registrujNapadZaIgraca(Napad napad, Igrac igrac){
        if(!igraci.contains(igrac)){
            registrujIgraca(igrac);
        }
        igrac.registrujNapad(napad);
    }

    public List<Igrac> dajIgracePoKriteriju(Predicate<Igrac> kriterij){
        return igraci.stream().filter(kriterij).collect(Collectors.toList());
    }

    public List<Heroj> dajHeroje(){
        return dajIgracePoKriteriju(igrac -> igrac instanceof Heroj).stream()
                .map(igrac -> (Heroj)igrac).collect(Collectors.toList());
    }

    public List<Neprijatelj> dajNeprijatelje(){
        return dajIgracePoKriteriju(igrac -> igrac instanceof Neprijatelj).stream()
                .map(igrac -> (Neprijatelj)igrac).collect(Collectors.toList());
    }

    public List<Igrac> dajPrezivjeleHeroje(){
        return dajHeroje().stream().filter(heroj -> heroj.getZivotniPoeni() > 0).collect(Collectors.toList());
    }

    public List<Neprijatelj> dajPrezivjeleNeprijatelje(){
        return dajNeprijatelje().stream().filter(neprijatelj -> neprijatelj.getZivotniPoeni() > 0).collect(Collectors.toList());
    }

    public TreeSet<Igrac> dajPrezivjeleIgrace(){
        //za ovo treba compare to
        return new TreeSet<>(dajIgracePoKriteriju(igrac -> igrac.getZivotniPoeni() > 0));
    }

    public Set<Igrac> dajIgraceUVodstvu(){
        //vidjeti sta se desi kad ima vise sa istim poenima
        Optional<Igrac> pobjednik = igraci.stream().max(Igrac::compareTo);
        return new TreeSet<>(igraci.stream().
                filter(igrac -> igrac.getZivotniPoeni() == pobjednik.get().getZivotniPoeni()).collect(Collectors.toList()));
    }

    public void izvrsiNapad(Igrac napadac, Igrac meta, Napad napad) throws IlegalanNapad {
        if(napadac.getZivotniPoeni() == 0){
            throw new IlegalanNapad("Nije moguće napasti sa igračem koji nema preostalih životnih poena");
        }

        double koeficijent = 1;
        if(napadac.getZivotniPoeni() < 20){
            koeficijent = napadac.getZivotniPoeni() / 100;
        }

        napadac.napadni(napad.getNazivNapada(), meta, koeficijent);
    }

    public void izvrsiSerijuNapada(Igrac napadac, HashMap<Igrac, Napad> meteNapadi) throws IlegalanNapad {
        if(napadac.getZivotniPoeni() == 0){
            throw new IlegalanNapad("Nije moguće napasti sa igračem koji nema preostalih životnih poena");
        }

        for (Map.Entry<Igrac, Napad> e : meteNapadi.entrySet()){
            napadac.provjeriNapad(e.getValue().getNazivNapada(), e.getKey());
        }

        for (Map.Entry<Igrac, Napad> e : meteNapadi.entrySet()){
            napadac.napadni(e.getValue().getNazivNapada(), e.getKey());
        }
    }

    public boolean daLiJeMoguceNapasti(Igrac napadac, Igrac meta, Napad napad){
        if(napadac.getZivotniPoeni() == 0){
           return false;
        }

        try {
            napadac.provjeriNapad(napad.getNazivNapada(), meta);
        }catch (IlegalanNapad ilegalanNapad) {
            return false;
        }

        return true;
    }

    public String prikaziStanje(){
        //za ovo treba toString
        StringBuilder rezultat = new StringBuilder();
        List<Heroj> heroji = dajHeroje().stream().sorted(Igrac::compareTo).collect(Collectors.toList());
        List<Neprijatelj> neprijatelji = dajNeprijatelje().stream().sorted(Igrac::compareTo).collect(Collectors.toList());
        rezultat.append("Heroji koji su u igri:\n");
        for (int i = 0; i < heroji.size(); i++){
            rezultat.append(heroji.get(i));
            if(i != heroji.size() - 1) rezultat.append(",\n");
            else rezultat.append(".\n");
        }

        rezultat.append("Neprijatelji koji su u igri:\n");
        for (int i = 0; i < neprijatelji.size(); i++){
            rezultat.append(neprijatelji.get(i));
            if(i != neprijatelji.size() - 1) rezultat.append(",\n");
            else rezultat.append(".\n");
        }

        return rezultat.toString();
    }

    public int statusIgre(){
        int brojHeroja = dajPrezivjeleHeroje().size();
        int brojNeprijatelja = dajPrezivjeleNeprijatelje().size();

        if (brojHeroja == 0 && brojNeprijatelja == 0){
            return 0;
        }else if(brojHeroja == dajHeroje().size() && brojNeprijatelja == dajNeprijatelje().size()){
            return 3;
        }else if(brojHeroja > 0 && brojNeprijatelja == 0){
            return 1;
        }

        return 2;
    }

    public void ukloniGubitnike(){
        igraci = igraci.stream().filter(igrac -> igrac.getZivotniPoeni() > 0).collect(Collectors.toList());
    }

    public void restartuj(){
        igraci.stream().forEach(igrac -> igrac.setZivotniPoeni(100));
    }
}
