package collection;

import utility.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class Ask {
    public static class AskBreak extends Exception {}


    public static City askCity(Console console, Integer id) throws AskBreak{
        /*
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null
    private int population; //Значение поля должно быть больше 0
    private Integer metersAboveSeaLevel;
    private Long telephoneCode; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100000
    private long agglomeration;
    private Government government;//Поле не может быть null
    private Human governor



    */
        try {
            console.print("name: ");
            String name;
            while (true) {
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.equals("")) break;
                console.print("name: ");
            }
            var coordinates = askCoordinates(console);
            console.print("age: ");
            var area = askArea(console);
            var population = askPopulation(console);
            var metersAboveSeaLevel = askMetersAboveSeaLevel(console);
            var telephoneCode = askTelephoneCode(console);
            var agglomeration = askAgglomeretion(console);
            var government = askGovernment(console);
            var governor = askGovernor(console);

            return new City(id, name, coordinates, area,population,metersAboveSeaLevel,telephoneCode,agglomeration,government,governor);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
    public static Human askGovernor(Console console) throws AskBreak {
        try{
            console.print("governor birthday: ");
            LocalDate birthday;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) { birthday = null; break;}
                try { birthday = LocalDate.from(LocalDateTime.parse(line, DateTimeFormatter.ISO_DATE_TIME)); break; } catch (
                        DateTimeParseException e) { }
                try { birthday = LocalDate.from(LocalDate.parse(line+"T00:00:00.0000", DateTimeFormatter.ISO_DATE_TIME).atStartOfDay()); break; } catch (DateTimeParseException e) { }
                console.print("governor.birthday: ");
            }
            return new Human(birthday);


        }
        catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }



    }




    public static Government askGovernment(Console console) throws AskBreak {
        try {
            console.print("Government ("+Government.names()+"): ");
            Government government;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { government = Government.valueOf(line); break; } catch (NullPointerException | IllegalArgumentException  e) { }
                }
                console.print("Color: ");
            }
            return government;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }


    }





    public static long askAgglomeretion(Console console) throws AskBreak {
        try{
            console.print("agglomeration: ");
            long agglomeretion = 0;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")){
                try {
                    agglomeretion = Long.parseLong(line);
                    if(agglomeretion > 0) break;

                } catch (NumberFormatException e) {
                    console.printError("Ошибка чтения");
                    return 0;
                }}
                console.print("agglomeration: ");

            }
            return agglomeretion;
        }
        catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return 0;
        }
    }

    public static Long askTelephoneCode(Console console) throws AskBreak{
        try {
        console.print("telephone code: ");
        Long telephoneCode;
        while (true) {
            var line = console.readln().trim();
            if (line.equals("exit")) throw new AskBreak();
            if (!line.equals("")) {
                try {
                    telephoneCode = Long.parseLong(line);
                    if (telephoneCode > 0 && telephoneCode < 100000) break;
                } catch (NumberFormatException e) {
                    console.printError("Ошибка чтения");
                    return null;
                }
            }
            console.print("telephone code: ");
        }
        return telephoneCode;
    }
        catch (NoSuchElementException | IllegalStateException e) {
        console.printError("Ошибка чтения");
        return null;
    }
    }



    public static Integer askMetersAboveSeaLevel(Console console) throws AskBreak {
        try {
            console.print("meters above sea level: ");
            Integer metersAboveSeaLevel;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {


                    try {
                        metersAboveSeaLevel = Integer.parseInt(line);
                        if (metersAboveSeaLevel > 0) break;
                    } catch (NumberFormatException e) {
                        console.printError("Ошибка чтения");
                        return null;
                    }
                }
                console.print("meters above sea level: ");
            }
            return metersAboveSeaLevel;
        }
        catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Long askArea(Console console) throws AskBreak {
        try{
            console.print("area: ");
            Long area;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {

                    try {
                        area = Long.parseLong(line);
                        if (area > 0) break;
                    } catch (NumberFormatException e) {
                        console.printError("Ошибка чтения");
                        return null;
                    }
                }
                console.print("area: ");
            }
        return area;
        }
        catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }

    }

    public static int askPopulation(Console console) throws AskBreak {
        try{
            console.print("population: ");
            int population = 0;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {

                    try {
                        population = Integer.parseInt(line);
                        if (population > 0) break;
                    } catch (NumberFormatException e) {
                        console.printError("Ошибка чтения");

                    }
                }
                console.print("population: ");
            }
            return population;
        }
        catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return 0;
        }
    }
    public static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            // private Integer x; //Значение поля должно быть больше -485, Поле не может быть null
            // private Double y; //Максимальное значение поля: 907, Поле не может быть null
            console.print("coordinates.x: ");
            Float x;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { x = Float.parseFloat(line); if (x!=null) break; } catch (NumberFormatException e) { }
                }
                console.print("coordinates.x: ");
            }
            console.print("coordinates.y: ");
            float y;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { y = Float.parseFloat(line); if (y<=986) break; } catch (NumberFormatException e) { }
                }
                console.print("coordinates.y: ");
            }

            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }


}
