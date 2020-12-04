import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class Movie implements Serializable {
    String name;
    String genre;
    String country;
    int year;
    int costRUB;
}

public class Lab7_2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File f = new File("Movies.txt");
        if (!f.exists()) {
            f.delete();
        }
        f.createNewFile();

        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.print("Введите количество фильмов для записи в файл\n" + "=> ");

        int kol = sc.nextInt();
        sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (int i = 0; i < kol; i++) {
                Movie movie = new Movie();

                System.out.print("Введите название " + (i + 1) + " фильма => ");
                movie.name = sc.next();

                System.out.print("Введите его жанр => ");
                movie.genre = sc.next();

                System.out.print("Введите страну => ");
                movie.country = sc.next();

                System.out.print("Введите год выхода фильма => ");
                movie.year = sc.nextInt();

                System.out.print("Введите стоимость проката => ");
                movie.costRUB = sc.nextInt();

                oos.writeObject(movie);
                sc.nextLine();
            }
            oos.flush();
            oos.close();
            fos.close();
            sc.close();

        } catch (IOException e) {
            System.out.println("End of file " + e);
        }

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream oin = new ObjectInputStream(fis)) {
            Movie movie;
            System.out.println("Название \t\t Год \t\t Страна \t\t  Жанр \t\t Стоимость проката");
            for (int i = 0; i < kol; i++) {
                movie = (Movie) oin.readObject();
                System.out.println(movie.name + "\t\t" + movie.year + "\t\t" + movie.country + "\t\t\t" + movie.genre + "\t\t\t" + movie.costRUB);
            }
            File f2 = new File("Movies_rus.txt");
            if (!f2.exists()) {
                f2.delete();
            }
            f2.createNewFile();

            try (FileOutputStream fos2 = new FileOutputStream(f2);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            FileInputStream fis2 = new FileInputStream(f);
            ObjectInputStream oin2 = new ObjectInputStream(fis2)){
                for (int i = 0; i < kol; i++) {
                    movie = (Movie) oin2.readObject();
                    String country = new String(movie.country.getBytes(StandardCharsets.UTF_8));
                    if (country.equals("Россия")) {
                        oos2.writeObject(movie);
                        System.out.format(
                                "Сохранен фильм %s от %d года выпуска",
                                new String(movie.name.getBytes(StandardCharsets.UTF_8)),
                                movie.year
                        );
                    }
                }
            }
            catch (IOException e) {
                System.out.println("End of file " + e);
        }
    }
}}
