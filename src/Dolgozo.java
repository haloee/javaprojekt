class Dolgozo {
    private int azonosito;
    private String nev;
    private String beosztas;
    private int munkaido;
    private int ledolgozottOrak;
    private int leNemDolgozottOrak;
    private int tulorak;

    public Dolgozo(int azonosito, String nev, String beosztas, int munkaido, int ledolgozottOrak, int leNemDolgozottOrak, int tulorak) {
        this.azonosito = azonosito;
        this.nev = nev;
        this.beosztas = beosztas;
        this.munkaido = munkaido;
        this.ledolgozottOrak = ledolgozottOrak;
        this.leNemDolgozottOrak = leNemDolgozottOrak;
        this.tulorak = tulorak;
    }

    public int getAzonosito() {
        return azonosito;
    }

    public String getNev() {
        return nev;
    }

    public String getBeosztas() {
        return beosztas;
    }

    public int getMunkaido() {
        return munkaido;
    }

    public int getLedolgozottOrak() {
        return ledolgozottOrak;
    }

    public int getLeNemDolgozottOrak() {
        return leNemDolgozottOrak;
    }

    public int getTulorak() {
        return tulorak;
    }

    public int fizetesSzamitas() {
        if (beosztas.equals("Vezető")) {
            int alapber = munkaido * ledolgozottOrak;
            int tuloraPotlek = tulorak * 1000; // Túlóra pótlék HUF-ban
            return alapber + tuloraPotlek;
        } else if (beosztas.equals("Fizikai munkás")) {
            int oraber = munkaido;
            double tuloraPotlek = tulorak * oraber * (1 + leNemDolgozottOrak / munkaido);
            return ledolgozottOrak * oraber + (int) tuloraPotlek;
        } else {
            return 0;
        }
    }

    public void setLedolgozottOrak(int ledolgozottOrak) {
    }

    public void setLeNemDolgozottOrak(int max) {
    }

    public void setTulorak(int max) {
    }
}
