import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa){
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }
    public void dodajPolise(Polisa polisa){
        if(polisa != null)
            polisy.add(polisa);
    }

    public void wypiszRaport() {
        System.out.println("Raport biura: " + nazwa);
        if (polisy.isEmpty()) {
            System.out.println("Brak polis w systemie");
            return;
        }
        for (Polisa p : polisy)
            System.out.println(p);
    }

    public double policzLacznaSkladke() {
        double suma = 0.0;
        for(Polisa p: polisy){
            suma += p.obliczSkladkeKoncowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0.0;
        for(Polisa p: polisy){
            suma += p.obliczSkladkeOdnowienia();
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;
        for(Polisa p: polisy) {
            if(p.getPoziomRyzyka() > 3)
                licznik++;
        }
        return licznik;
    }

    public Polisa znajdzPoNumerze(String numerPolisy) {
        for(Polisa p: polisy){
            if(p.getNumerPolisy().equals(numerPolisy))
                return p;
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog){
        System.out.println("Polisy tansze niz " + prog + ":");
        boolean znaleziono = false;

        for(Polisa p: polisy){
            if(p.obliczSkladkeKoncowa() < prog){
                System.out.println(p);
                znaleziono = true;
            }
        }
        if(!znaleziono)
            System.out.println("Nie znaleziono takich polis");
    }
}
