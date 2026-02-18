import java.util.Scanner

fun validateString(scanner: Scanner, inputPrompt: String, errorPrompt: String): String {
    var value: String
    do {
        print(inputPrompt)
        value = scanner.nextLine().trim()

        if (value.isEmpty()) {
            println(errorPrompt)
        }
    } while (value.isEmpty())

    return value
}