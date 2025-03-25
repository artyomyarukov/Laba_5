package commands;

import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда 'execute_script'. Выполнить скрипт из файла.
 */
public class ExecuteScript extends Command {
    private final Console console;

    /**
     * Конструктор команды
     * @param console - консоль для ввода/выводай
     */


    public ExecuteScript(Console console) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse("Выполнение скрипта '" + arguments[1] + "'...");
    }
}