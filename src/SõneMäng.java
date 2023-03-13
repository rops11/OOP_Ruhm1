import java.io.IOException;
public class SõneMäng {
    public static void main(String[] args) throws IOException {
        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();
        boolean mängKäib = true;

        System.out.println("Mängija peab ära arvama õige sõna. \n1) Suur täht tähistab äraarvatud tähte\n2) Väike täht tähistab tähte, mis vales kohas\n3) \"_\" tähistab ei sellist tähte pole sõnas");
        SõneAnalüsaator sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 3);

        while (mängKäib) {
            //LISADA JUURDE (Scanner) sisestatud sõna kontrollimine
            String proovitavSõna = "kaks";
            sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);
            if (sõneAnalüsaator.kontrolliVastust(proovitavSõna)) {
                mängija.setVõite(mängija.getVõite()+1);
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                continue;
            }

            if (sõneAnalüsaator.getKatse() >= sõneAnalüsaator.getLubatudKatsied()) {
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                mängija.setKaotusi(mängija.getKaotusi()+1);
                mängKäib = false;
                break;
            }
        }
    }
}
