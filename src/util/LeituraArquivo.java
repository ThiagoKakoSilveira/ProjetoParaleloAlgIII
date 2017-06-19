package util;

/**
 *
 * @author 631420067
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import modelo.Bike;

public class LeituraArquivo {

    ArrayList<Bike> bikes = new ArrayList<>();

    public int hashvalue(int hashcode, int j) {
        java.util.Random gen = new java.util.Random(hashcode);
        for (int i = 0; i < j; i++) {
            gen.nextInt();
        }
        return gen.nextInt();
    }

    public LeituraArquivo() {
    }

    public static void main(String[] args) {
        (new LeituraArquivo()).run();
    }

    public ArrayList<Bike> run() {
        @SuppressWarnings("unused")
        String dummy;
        try {
            // cria scanner
            
            FileReader file = new FileReader("estacoes-bikepoa.csv");
            Scanner arq = new Scanner(file);
            String a = "";
            arq.nextLine();
            while (arq.hasNext()) {
                a += arq.next() + "\n";
            }
            a = a.replace(",", ".");
            arq = new Scanner(a);
            // configura scanner
            arq.useLocale(Locale.US);
            arq.useDelimiter("[;\n]");
            // usa scanner
            while (arq.hasNext()) {
                int id = arq.nextInt();
                String nome = arq.next();
                double lat = arq.nextDouble();
                double lon = arq.nextDouble();
                bikes.add(new Bike(id, nome, lat, lon));
            }
            arq.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bikes;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }
    
}
