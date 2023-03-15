import java.util.HashMap;
import java.util.Map;

public class SõneAnalüsaator {
    //Insediväljad
    private String õigeVastus;
    private int lubatudKatsied;
    private int katse;

    //Konstruktor
    public SõneAnalüsaator(String õigeVastus, int lubatudKatsied) {
        this.õigeVastus = õigeVastus.toLowerCase();
        this.lubatudKatsied = lubatudKatsied;
        this.katse = 0;
    }

    //Get
    public String getÕigeVastus() {
        return õigeVastus;
    }

    public int getLubatudKatsied() {
        return lubatudKatsied;
    }

    public int getKatse() {
        return katse;
    }

    //Set
    public void setKatse(int katse) {
        this.katse = katse;
    }

    //Meetodid
    public Map<Character, Integer> unikaalseteSümboliteArv(String sõne) {
        //loeme kokku mitu erinevat tähte sõnes esineb
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
        //ei pea oluliseks, kas mängija sisestab suuri või väikeseid tähti
        pakumine = pakumine.toLowerCase();

        //Kontrollime, et sõne pikkus oleks sama, mis vastusel
        if (õigeVastus.length() != pakumine.length()) {
            System.out.println("Sõne pikkus ei klapi (oodati " + õigeVastus.length() + " tähte, saadi " + pakumine.length() + " tähte)");
            return false;
        }

        //Kontrollime, kas vastati õigesti
        if (pakumine.equals(õigeVastus)) {
            return true; //Sõna on ära arvatud
        }

        //Kontrollime, mis tähed õigesti arvati
        String vihje = "";
        String[] tähedPakumine = pakumine.split("");
        String[] tähedÕigeVastus = õigeVastus.split("");
        Map<Character, Integer> uniaalseteSümboliteArvVastus = unikaalseteSümboliteArv(õigeVastus);

        //Kuvab suurt tähte, kui täht on õiges kohas
        //Kuvab väikest tähte, kui sisaldab tähte, aga täht on vales kohas
        //Kuvab "_", kui tähte ei ole üldse sõnas või samu tähti on üleliia.
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
        return false; //Sõna ei ole ära arvatud
    }
}
