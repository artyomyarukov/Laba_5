import collection.*;
import managers.DumpManager;
import utility.Console;
import utility.StandartConsole;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args)throws Ask.AskBreak {

        try {
            DumpManager dumpManager = new DumpManager();
            Set<City> cities = new LinkedHashSet<>(dumpManager.loadFromFile("cities.xml"));
            cities.add(new City(1, "Moscow", new Coordinates(54.75f, 37.62f), 2511L, 12655050, 156, 495L, 20000000L, Government.REPUBLIC, new Human(LocalDate.of(1960, 1, 1))));
            cities.add(new City(2, "New York", new Coordinates(40.71f, -74.01f), 783L, 8419600, 10, 212L, 20000000L, Government.REPUBLIC, new Human(LocalDate.of(1950, 5, 15))));
            // Сохраняем коллекцию в файл
            dumpManager.saveToFile(cities, "cities.xml");
            System.out.println("Коллекция успешно сохранена в файл.");

            // Загружаем коллекцию из файла
            Set<City> loadedCities = new LinkedHashSet<>(dumpManager.loadFromFile("cities.xml"));
            System.out.println("Коллекция успешно загружена из файла:");
            loadedCities.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

    }
}