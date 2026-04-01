public class Polisa {
    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.5;


    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public String getKlient() {
        return klient;
    }

    public double getSkladkaBazowa() {
        return skladkaBazowa;
    }

    public int getPoziomRyzyka() {
        return poziomRyzyka;
    }

    public double getWartoscPojazdu() {
        return wartoscPojazdu;
    }

    public boolean isCzyMaAlarm() {
        return czyMaAlarm;
    }

    public boolean isCzyBezszkodowyKlient() {
        return czyBezszkodowyKlient;
    }
    public double obliczSkladkeKoncowa(){
        double skladka = skladkaBazowa + OPLATA_ADMINISTRACYJNA;
        skladka += poziomRyzyka * 120.0;

        if(wartoscPojazdu > 60000.0)
            skladka += ;

        if(czyMaAlarm)
            skladka -= 50.0;

        if(czyBezszkodowyKlient)
            skladka *= 0.9;

        if(skladka < skladkaBazowa)
            skladka = skladkaBazowa;

        return skladka;
    }
    public double obliczSkladkeOdnowienia(){
        double biezaca = obliczSkladkeKoncowa();
        double odnowieniowa = biezaca;

        if(poziomRyzyka == 4)
            odnowieniowa *= 1.15;
        else if(poziomRyzyka > 4)
            odnowieniowa *= 1.2;

        if(wartoscPojazdu > 60000.0)
            odnowieniowa += 100.0;

        if(czyBezszkodowyKlient)
            odnowieniowa *= 0.9;

        if(czyMaAlarm)
            odnowieniowa *= 0.95;

        double minimum = biezaca * 0.85;
        if(odnowieniowa < minimum)
            odnowieniowa = minimum;

        double maksimum = biezaca * 1.3;
        if(odnowieniowa > maksimum)
            odnowieniowa = maksimum;

        return odnowieniowa;
    }

    public String pobierzPodsumowanieRyzyka(){
        String ocena;
        if(poziomRyzyka <= 1)
            ocena = "minimalne";
        else if(poziomRyzyka == 2)
            ocena = "niskie";
        else if(poziomRyzyka == 3)
            ocena = "srednie";
        else if(poziomRyzyka == 4)
            ocena = "wysokie";
        else
            ocena = "bardzo wysokie";
        return "Poziom ryzyka: " + ocena;
    }
    double ocenaRyzyka = pobierzPodsumowanieRyzyka();
    
    public String toString (){
        return "Polisa {" +
                "numer polisy = " + numerPolisy +
                ", klient = " + klient + '\'' +
                ", skladka bazowa = " + skladkaBazowa +
                ", ocena ryzyka = " + ocenaRyzyka + '\'' +
                ", wartosc pojazdu = " + wartoscPojazdu +
                ", czy ma alarm = " + czyMaAlarm + '\'' +
                ", czy bezszkodowy klient = " + czyBezszkodowyKlient + '\'';

    }
}
