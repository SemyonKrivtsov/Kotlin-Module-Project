import java.util.Scanner

class App {
    private val scanner = Scanner(System.`in`)
    private val archives = mutableListOf<Entity.Archive>()

    private fun addArchive(title: String) {
        val newArchive = Entity.Archive(title)
        archives.add(newArchive)
    }

    private fun showNotesMenu(archive: Entity.Archive) {
        Menu(
            "Заметки",
            scanner,
            { archive.getNotes() },
            { noteTitle -> createNote(archive, noteTitle) },
            { note -> showNoteView(note) }
        )
    }

    fun start() {
        try {
            Menu(
                "Архивы",
                scanner,
                { archives },
                { archiveTitle -> addArchive(archiveTitle) },
                { archive -> showNotesMenu(archive) }
            )
        } finally {
            scanner.close()
        }
    }

    fun showNoteView(note: Entity.Note) {
        println("\nЗаметка: ${note.title}")
        println("=".repeat(50))
        println(note.getContent())
        println("=".repeat(50))
    }

    fun createNote(archive: Entity.Archive, name: String) {
        val text = validateString(
            scanner,
            "Введите содержимое заметки:",
            "Нельзя создать заметку с пустым содержанием. Введите еще раз!"
        )
        val note = Entity.Note(name, text)
        archive.addNote(note)
    }
}