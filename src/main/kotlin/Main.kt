import java.util.Scanner

fun main(args: Array<String>) {
    val archives = mutableListOf<Archive>()
    while (true) {
        val menu = Menu("Список архивов:")
        menu.addItem("Создать архив") {
            createArchive(archives)
        }

        for (archive in archives) {
            menu.addItem(archive.name) {
                openArchive(archive)
            }
        }

        val exitStatus = menu.show(true)
        if (!exitStatus) {
            break
        }
    }
    println("Спасибо за использование приложения!")
}

private fun createArchive(archives: MutableList<Archive>) {
    val name = Menu.readString("Введите название архива")
    if (!name.isEmpty()) {
        if (archives.any { it.name == name }) {
            println("Архив с названием '$name' уже существует")
        } else {
            archives.add(Archive(name))
            println("Архив '$name' успешно создан!")
        }
    } else {
        println("Архив обязательно должен содержать имя")
    }
}

private fun openArchive(archive: Archive) {
    while (true) {
        val notesMenu = Menu("Список заметок")
        notesMenu.addItem("Создать заметку") {
            createNote(archive)
        }
        for (note in archive.notes) {
            notesMenu.addItem(note.name) {
                showNote(note)
            }
        }
        val exitStatus = notesMenu.show()
        if (!exitStatus) {
            break
        }
    }

}

private fun createNote(archive: Archive) {
    val name = Menu.readString("Введите название заметки")
    if (name.isEmpty()) return

    val content = Menu.readString("Введите содержание заметки:")
    if (content.isEmpty()) return

    archive.notes.add(Note(name, content))
    println("Заметка '$name' создана в архиве '${archive.name}'!")
}

private fun showNote(note: Note) {
    println("\n=== ${note.name} ===")
    println(note.content)
    println("\nНажмите Enter для возврата...")
    readLine()
}