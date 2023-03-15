import java.io.IOException;
import java.util.Scanner;
public class SõneMäng {
    public static void main(String[] args) throws IOException {
        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();
        boolean mängKäib = true;

        System.out.println("Mängija peab ära arvama õige sõna. \n1) Suur täht tähistab äraarvatud tähte\n2) Väike täht tähistab tähte, mis vales kohas\n3) \"_\" tähistab et sellist tähte pole sõnas");
        SõneAnalüsaator sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5);

        while (mängKäib) {
            //LISADA JUURDE (Scanner) sisestatud sõna kontrollimine
            Scanner sisestatudSõna = new Scanner(System.in);
            String proovitavSõna = sisestatudSõna.nextLine();
            sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);
            if (sõneAnalüsaator.kontrolliVastust(proovitavSõna)) {
                mängija.setVõite(mängija.getVõite()+1);
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                //Lisatud
                System.out.println("\nSõna on äraarvatud - "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();
                if (otsus.equals("ei")){
                    System.out.println();
                    System.out.println("Oled arvanud ära :"+mängija.getVõite()+" sõna ja oled kaotanud : "+mängija.getKaotusi()+" korda. Sinu skoor on "+(mängija.getVõite()-mängija.getKaotusi()));
                    break;
                }
                sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5);
                continue;
            }

            if (sõneAnalüsaator.getKatse() >= sõneAnalüsaator.getLubatudKatsied()) {
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                //Lisatud
                System.out.println("Katsete arv on täis. Sõna oli "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();
                mängija.setKaotusi(mängija.getKaotusi()+1);
                if (otsus.equals("ei")) {
                    System.out.println("Oled arvanud ära :"+mängija.getVõite()+" sõna ja oled kaotanud : "+mängija.getKaotusi()+" korda. Sinu skoor on "+(mängija.getVõite()-mängija.getKaotusi()));
                    mängKäib = false;
                    break;
                }
                if (otsus.equals("jah")){
                    sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5);
                }

            }
        }
    }
}
