import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //SõneLugeja kontroll
        SõneLugeja a = new SõneLugeja("src/testSõnad.txt");
        System.out.println(a.arvatavSõna());

    }

}