package commands;

import managers.CollectionManager;
import utility.Console;
import collection.City;
import utility.ExecutionResponse;

/**
 * Команда 'clear'. Очищает коллекцию.
 *
 * @author artyom_yarukov
 */
public class Clear extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        collectionManager.getCollection().clear();
        collectionManager.update();
        return new ExecutionResponse("Коллекция очищена!");
    }
}