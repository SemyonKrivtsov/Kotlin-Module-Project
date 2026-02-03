import java.util.Scanner

class Menu <T : Entity>(
    private val title: String,
    private val scanner: Scanner,
    private val entities: MutableList<T>,
    private val createEntity: (String) -> Unit,
    private val showMenu: (T) -> Unit
) {

    init {
        load()
    }

    private fun readCommand(scanner: Scanner): Int {
        print("Выберите действие: ")
        val input = scanner.nextLine()

        try {
            when (val choice = input.toInt()) {
                0 -> {
                    print("Введите название: ")
                    val name = scanner.nextLine().trim()
                    if (name.isEmpty()) {
                        println("Название не может быть пустым!")
                    } else {
                        createEntity(name)
                    }
                }
                in 1..entities.size -> {
                    val selectedEntity = entities[choice - 1]
                    showMenu(selectedEntity)
                }
                entities.size + 1 -> {
                    return -1
                }
                else -> println("Нет такого пункта меню!")
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: введите число!")
        }

        return 0
    }

    private fun printMenu() {
        println("\nСписок \"${title}\":")
        println("0. Создать")
        entities.forEachIndexed { index, entity ->
            println("${index + 1}. ${entity.title}")
        }
        println("${entities.size + 1}. Выход")
    }

    fun load() {
        var exitCode: Int
        do
        {
            printMenu()
            exitCode = readCommand(scanner)
        }
        while (exitCode != -1)
    }
}