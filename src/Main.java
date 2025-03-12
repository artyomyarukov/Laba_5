import collection.*;
import utility.Console;
import utility.StandartConsole;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<City> cities = new ArrayList<City>();
    public static void main(String[] args)throws Ask.AskBreak {
        StandartConsole console = new StandartConsole();
        cities.add(Ask.askCity(console, 100));

       /* Human h = new Human(java.time.LocalDate.now());
        Human h2 = new Human(java.time.LocalDate.now().plusDays(1));
        cities.add(new City(12,"wwqw",new Coordinates(12F,12F), LocalDate.now().atStartOfDay(),1221L,323,434,4334L,4343, Government.JUNTA,h));
        cities.add(new City(13,"wqw",new Coordinates(11F,12F), LocalDate.now().atStartOfDay(),1221L,323,434,4334L,4343,Government.JUNTA,h2));*/
        for (City c : cities) {
            System.out.println(c);
        }





    }
}