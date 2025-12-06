import java.util.Scanner

class Menu(
    private val title: String
) {
    private val items = mutableListOf<Pair<String, () -> Unit>>()
    private val scanner = Scanner(System.`in`)

    fun addItem(name: String, action: () -> Unit) {
        items.add(Pair(name,action))
    }

    fun show(exitAfterAction: Boolean = true): Boolean {
        while (true) {
            printMenu()
            val input = readInput()
            when {
                input == null -> continue
                input == items.size -> return false
                input < items.size -> {
                    items[input].second()
                }
                else -> println("Пункта с номером $input нет")
            }
            return true;
        }
    }

    private fun printMenu() {
        println("\n$title")
        for ((index, item) in items.withIndex()) {
            println("${index}. ${item.first}")
        }
        println("${items.size}. Выход")
    }

    private fun readInput(): Int? {
        print("Введите номер пункта: ")
        val input = scanner.nextLine().trim()
        if (input.isEmpty()) {
            println("Введите число")
            return null
        }
        val number = input.toIntOrNull()
        if (number == null) {
            println("'$input' не является числом")
            return null
        }
        if (number < 0) {
            println("Номер не может быть отрицательным")
            return null
        }
        return number
    }

    companion object {
        fun readString(request: String): String {
            val scanner = Scanner(System.`in`)
            println(request)
            val input = scanner.nextLine().trim()
            return input
        }
    }
}