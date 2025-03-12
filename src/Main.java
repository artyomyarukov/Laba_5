import collection.*;
import managers.CollectionManager;
import managers.DumpManager;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args)throws Ask.AskBreak {
        // Создаем Scanner для чтения ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем имя файла у пользователя
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine().trim();

        // Создаем DumpManager и CollectionManager
        DumpManager dumpManager = new DumpManager();
        CollectionManager manager = new CollectionManager(dumpManager, filename);

        // Загружаем коллекцию из файла при запуске
        Collection<City> loadedCities = dumpManager.loadFromFile(filename);
        manager.getCollection().addAll(loadedCities);

        // Выводим текущую коллекцию
        System.out.println("Текущая коллекция:");
        System.out.println(manager);

        // Пример добавления нового города
        City newCity = new City(
                manager.getFreeId(), // Генерация уникального ID
                "Moscow",
                new Coordinates(55.75f, 37.62f),
                LocalDateTime.now(), // Текущая дата и время
                2511L, // area
                12655050, // population
                156, // metersAboveSeaLevel
                495L, // telephoneCode
                20000000L, // agglomeration
                Government.REPUBLIC, // government
                new Human(LocalDate.of(1960, 1, 1)) // governor
        );

        if (manager.add(newCity)) {
            System.out.println("Город успешно добавлен.");
        } else {
            System.out.println("Город уже существует.");
        }

        // Выводим коллекцию после добавления
        System.out.println("Коллекция после добавления:");
        System.out.println(manager);

        // Сохраняем коллекцию в файл
        dumpManager.saveToFile(manager.getCollection(), filename);

        // Закрываем Scanner
        scanner.close();
    }
}



