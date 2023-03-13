import java.util.HashMap;
import java.util.Map;

public class SõneAnalüsaator {
    //Insediväljad
    private String õigeVastus;

    //Konstruktor
    public SõneAnalüsaator(String õigeVastus) {
        this.õigeVastus = õigeVastus.toLowerCase();
    }

    //Meetodid
    public Map<Character, Integer> unikaalseteSümboliteArv(String sõne) {
        HashMap<Character, Integer> tagasta = new HashMap<>();

        for (int i = 0; i < sõne.length(); i++) {
            char täht = sõne.charAt(i);
            if (tagasta.containsKey(täht)) {
                tagasta.put(täht, tagasta.get(täht) + 1);
            } else {
                tagasta.put(täht, 1);
            }
        }

        return tagasta;
    }
    public boolean kontrolliVastust(String pakumine) {
        //Kuvab suurt tähte, kui täht on õiges kohas
        //Kuvab väikest tähte, kui sisaldab tähte, aga täht on vales kohas
        //Kuvab "_", kui tähte ei ole üldse sõnas või samu tähti on üleliia.

        pakumine = pakumine.toLowerCase();

        //Kontrollime, et sõne pikkus oleks sama, mis vastusel
        if (õigeVastus.length() != pakumine.length()) {
            System.out.println("Sõne pikkus ei klapi (oodati " + õigeVastus.length() + " tähte, saadi " + pakumine.length() + " tähte)");
            return false;
        }

        //Kontrollime, kas vastati õigesti
        if (pakumine.equals(õigeVastus)) {
            return true;
        }

        //Kontrollime, mis tähed õigesti vastati
        String vihje = "";
        String[] tähedPakumine = pakumine.split("");
        String[] tähedÕigeVastus = õigeVastus.split("");
        Map<Character, Integer> uniaalseteSümboliteArvVastus = unikaalseteSümboliteArv(õigeVastus);

        for (int i = 0; i < tähedPakumine.length; i++) {
            char otsitavSümbol = tähedPakumine[i].charAt(0);

            if (tähedPakumine[i].equals(tähedÕigeVastus[i])) {
                vihje += tähedPakumine[i].toUpperCase();
                uniaalseteSümboliteArvVastus.put(otsitavSümbol, uniaalseteSümboliteArvVastus.get(otsitavSümbol) - 1);
                if (uniaalseteSümboliteArvVastus.get(otsitavSümbol) < 1) {
                    uniaalseteSümboliteArvVastus.remove(otsitavSümbol);
                }
            }
            else if (uniaalseteSümboliteArvVastus.containsKey(otsitavSümbol)) {
                vihje += tähedPakumine[i].toLowerCase();
                uniaalseteSümboliteArvVastus.put(otsitavSümbol, uniaalseteSümboliteArvVastus.get(otsitavSümbol) - 1);
                if (uniaalseteSümboliteArvVastus.get(otsitavSümbol) < 1) {
                    uniaalseteSümboliteArvVastus.remove(otsitavSümbol);
                }
            }
            else {
                vihje += "_";
            }
        }

        System.out.println(vihje);

        return false;
    }


    /*test
    public static void main(String[] args) {
        SõneAnalüsaator sõneAnalüsaator = new SõneAnalüsaator("koer");
        System.out.println(sõneAnalüsaator.kontrolliVastust("kaks"));
    }
    */
}
