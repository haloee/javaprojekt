import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FizetesKiszamito {
    private List<Dolgozo> dolgozok;

    public FizetesKiszamito() {
        dolgozok = new ArrayList<>();
    }

    public void dolgozoAdatokBeolvasasa(String fajlnev) {
        try {
            File file = new File(fajlnev);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String sor = scanner.nextLine();
                String[] adatok = sor.split(";");

                int azonosito = Integer.parseInt(adatok[0]);
                String nev = adatok[1];
                String beosztas = adatok[2];
                int munkaido = Integer.parseInt(adatok[3]);
                int alapberVagyOraber = Integer.parseInt(adatok[4]);
                int tuloraPotlek = Integer.parseInt(adatok[5]);

                Dolgozo dolgozo = new Dolgozo(azonosito, nev, beosztas, munkaido, 0, 0, 0);
                dolgozok.add(dolgozo);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void munkaoraAdatokBeolvasasa(String fajlnev) {
        try {
            File file = new File(fajlnev);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String sor = scanner.nextLine();
                String[] adatok = sor.split(";");

                int azonosito = Integer.parseInt(adatok[0]);
                int ledolgozottOrak = Integer.parseInt(adatok[1]);

                for (Dolgozo dolgozo : dolgozok) {
                    if (dolgozo.getAzonosito() == azonosito) {
                        dolgozo.setLedolgozottOrak(ledolgozottOrak);
                        dolgozo.setLeNemDolgozottOrak(Math.max(dolgozo.getMunkaido() - ledolgozottOrak, 0));
                        dolgozo.setTulorak(Math.max(ledolgozottOrak - dolgozo.getMunkaido(), 0));
                        break;
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fizetesekKiirasa() {
        Collections.sort(dolgozok, Comparator.comparing(Dolgozo::getNev));

        for (Dolgozo dolgozo : dolgozok) {
            System.out.println(dolgozo.getNev() + ": " + dolgozo.fizetesSzamitas() + " HUF");
        }
    }

    public void dolgozokListazasa() {
        Collections.sort(dolgozok, Comparator.comparing(Dolgozo::getLeNemDolgozottOrak).reversed());

        for (Dolgozo dolgozo : dolgozok) {
            System.out.println(dolgozo.getNev() + ": " + dolgozo.getLeNemDolgozottOrak() + " le nem dolgozott óra");
        }
    }

    public void koltsegKiiras() {
        int munkaberOsszeg = 0;
        int tuloraOsszeg = 0;
        int kartaOkozoOsszeg = 0;

        for (Dolgozo dolgozo : dolgozok) {
            munkaberOsszeg += dolgozo.fizetesSzamitas();
            tuloraOsszeg += dolgozo.getTulorak() * 1000; // Túlóra pótlék HUF-ban
            kartaOkozoOsszeg += dolgozo.getLeNemDolgozottOrak() * dolgozo.getMunkaido();
        }

        System.out.println("Havi munkabér: " + munkaberOsszeg + " HUF");
        System.out.println("Havi túlóra díj: " + tuloraOsszeg + " HUF");
        System.out.println("Kárt okozott órák: " + kartaOkozoOsszeg + " HUF");
    }}