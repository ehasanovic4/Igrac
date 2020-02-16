package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class IgraTest {

    private static Igra igra;

    @BeforeAll
    static void setUpClass() {
        Igrac igrac1 = new Heroj("superman", 5);
        Igrac igrac2 = new Heroj("spiderman", 6);
        Igrac igrac3 = new Heroj("batman", 4);

        Igrac neprijatelj1 = new Neprijatelj("joker");
        Igrac neprijatelj2 = new Neprijatelj("sandman");
        Igrac neprijatelj3 = new Neprijatelj("venom");

        igra.registrujIgraca(igrac1);
        igra.registrujIgraca(neprijatelj1);
        igra.registrujIgraca(igrac2);
        igra.registrujIgraca(neprijatelj2);
        igra.registrujIgraca(igrac3);
        igra.registrujIgraca(neprijatelj3);
    }

    @BeforeEach
    void setUp() {

    }
}