import java.util.Scanner

class App {
    private val scanner = Scanner(System.`in`)
    private val archives = mutableListOf<Archive>()

    private fun addArchive(title: String) {
        val newArchive = Archive(title)
        archives.add(newArchive)
    }

    private fun showNotesMenu(archive: Archive) {
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

    fun showNoteView(note: Note) {
        println("\nЗаметка: ${note.title}")
        println("=".repeat(50))
        println(note.getContent())
        println("=".repeat(50))
    }

    fun createNote(archive: Archive, name: String) {
        val text = validateString(
            scanner,
            "Введите содержимое заметки:",
            "Нельзя создать заметку с пустым содержанием. Введите еще раз!"
        )
        val note = Note(name, text)
        archive.addNote(note)
    }
}