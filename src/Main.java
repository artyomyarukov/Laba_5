import collection.*;
import managers.CollectionManager;
import managers.DumpManager;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args)throws Ask.AskBreak {
        DumpManager dumpManager = new DumpManager();
        CollectionManager manager = new CollectionManager(dumpManager, "cities.xml");

        // Загружаем коллекцию из файла при запуске
        if (!manager.init()) {
            System.out.println("Ошибка при загрузке коллекции из файла.");
            return;
        }


        // Выводим текущую коллекцию
        System.out.println("Текущая коллекция:");
        System.out.println(manager);

        // Добавляем хук для сохранения коллекции при завершении программы
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Сохранение коллекции перед завершением программы...");
            manager.saveCollection();
        }));

        // Пример добавления нового города
        City newCity = new City(
                manager.getFreeId(), // Генерация уникального ID
                "Moscow",
                new Coordinates(45.73f, 37.62f),
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

        // Программа завершается, и хук сохраняет коллекцию в файл
    }
}


