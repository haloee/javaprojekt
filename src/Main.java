// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FizetesKiszamito fizetesKiszamito = new FizetesKiszamito();
        fizetesKiszamito.dolgozoAdatokBeolvasasa("dolgozok.txt");
        fizetesKiszamito.munkaoraAdatokBeolvasasa("munkaorak.txt");
        fizetesKiszamito.fizetesekKiirasa();
        fizetesKiszamito.dolgozokListazasa();
        fizetesKiszamito.koltsegKiiras();
    }
}
