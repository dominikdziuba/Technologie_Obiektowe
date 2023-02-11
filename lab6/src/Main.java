import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import general.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("[a] Rozpocznij nową symulację");
            System.out.println("[b] Wczytaj symulację");
            Scanner sc = new Scanner(System.in);

            String choice = sc.nextLine();

            switch (choice) {
                case "a" -> {
                    System.out.println("[a] Wariant pierwszy - brak początkowej odporności na wirusa.");
                    System.out.println("[b] Wariant drugi - częściowa początkowa odporność na wirusa");
                    String choice2 = sc.nextLine();
                    switch (choice2) {
                        case "a":
                            new Simulation(800, 800, 300).beginSimulation(false);
                        case "b":
                            new Simulation(800, 800, 300).beginSimulation(true);
                        default:
                            System.out.println("Nieprawidłowa opcja. Spróbuj jeszcze raz.");
                    }
                }
                case "b" -> {
                    File root = new File("./");
                    String[] content = root.list();
                    if (content == null) {
                        System.out.println("Nie można wczytać pliku.");
                        break;
                    }
                    Arrays.stream(content).forEach(elem -> {
                        Pattern pattern = Pattern.compile("state");
                        Matcher matcher = pattern.matcher(elem);
                        boolean matchCorrect = matcher.find();
                        if (matchCorrect) {
                            System.out.println(elem);
                        }
                    });
                    System.out.println("Podaj nazwę pliku do wczytania");
                    String fileName = sc.nextLine();
                    new Simulation(800, 800, fileName).beginSimulation(false);
                }
                default -> System.out.println("Nieprawidłowa opcja. Spróbuj jeszcze raz.");
            }
        }
    }
}
