import java.io.*;
import java.util.Scanner;

public class Lab7_1 {
    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Users\\vipka\\Desktop\\2 КУРС\\АиП\\Lab7IlyaB");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("Films.txt");
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество фильмов для записи в файл\n" + "=> ");
            int kol = sc.nextInt();
            sc.nextLine();
            String name, country, genre;
            int year, costRUB;
            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + " фильма => ");
                name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите год выхода фильма => ");
                year = sc.nextInt();
                sc.nextLine();
                rf.writeInt(year);

                System.out.print("Введите страну => ");
                country = sc.next();
                rf.writeUTF(country);
                for (int j = 0; j < 20 - country.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите его жанр => ");
                genre = sc.next();
                rf.writeUTF(genre);
                for (int j = 0; j < 20 - genre.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите стоимость проката => ");
                costRUB = sc.nextInt();
                sc.nextLine();
                rf.writeInt(costRUB);
            }
            rf.close();

            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);

            File f2 = new File("Rus_Films.txt");
            if (!f2.exists()) {
                f2.delete();
            }
            f2.createNewFile();
            RandomAccessFile rf1 = new RandomAccessFile(f2, "rw");

            System.out.println("Информация о     фильмах");
            System.out.println("Название \t\t Год \t\t Страна \t\t  Жанр \t\t Стоимость проката");
            for (int i = 0; i < kol; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.readByte();
                year = rf.readInt();
                country = rf.readUTF();
                for (int j = 0; j < 20 - country.length(); j++)
                    rf.readByte();
                genre = rf.readUTF();
                for (int j = 0; j < 20 - genre.length(); j++)
                    rf.readByte();
                costRUB = rf.readInt();
                System.out.println(name + "\t\t\t" + year + "\t\t\t" + country + "\t\t\t" + genre + "\t\t\t" + costRUB);
                if (country.equals("Россия")) {
                    rf1.writeUTF(name);
                    for (int j = 0; j < 20 - name.length(); j++)
                        rf1.writeByte(1);
                    rf1.writeInt(year);
                    rf1.writeUTF(country);
                    for (int j = 0; j < 20 - country.length(); j++)
                        rf1.writeByte(1);
                    rf1.writeUTF(genre);
                    for (int j = 0; j < 20 - genre.length(); j++)
                        rf1.writeByte(1);
                    rf1.writeInt(costRUB);
                    sc.nextLine();
                }
            }
            rf.close();
            rf1.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
