public class SõneAnalüsaator {
    //Insediväljad
    private String õigeVastus;

    //Konstruktor
    public SõneAnalüsaator(String õigeVastus) {
        this.õigeVastus = õigeVastus.toLowerCase();
    }

    //Meetodid
    public boolean kontrolliVastust(String pakumine) {
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
        for (int i = 0; i < tähedPakumine.length; i++) {
            if (tähedPakumine[i].equals(tähedÕigeVastus[i])) {
                vihje += tähedPakumine[i];
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
        System.out.println(sõneAnalüsaator.kontrolliVastust("kass"));
    }
    */
}
