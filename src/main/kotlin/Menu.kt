import java.util.Scanner

class Menu <T : Entity>(
    private val title: String,
    private val scanner: Scanner,
    private val getEntities: () -> List<T>,
    private val createEntity: (String) -> Unit,
    private val showMenu: (T) -> Unit
) {

    init {
        load()
    }

    fun load() {
        var exitCode: Boolean
        do
        {
            printMenu()
            exitCode = readCommand(scanner)
        }
        while (exitCode)
    }

    private fun readCommand(scanner: Scanner): Boolean {
        print("Выберите действие: ")
        val entities = getEntities()
        val input = scanner.nextLine()

        try {
            when (val choice = input.toInt()) {
                0 -> {
                    readName(scanner)
                }
                in 1..entities.size -> {
                    val selectedEntity = entities[choice - 1]
                    showMenu(selectedEntity)
                }
                entities.size + 1 -> {
                    return false
                }
                else -> println("Нет такого пункта меню!")
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: введите число!")
        }

        return true
    }

    private fun printMenu() {
        val entities = getEntities()
        println("\nСписок \"${title}\":")
        println("0. Создать")
        entities.forEachIndexed { index, entity ->
            println("${index + 1}. ${entity.title}")
        }
        println("${entities.size + 1}. Выход")
    }

    private fun readName(scanner: Scanner) {
        print("Введите название: ")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Название не может быть пустым!")
        } else {
            createEntity(name)
        }
    }
}