import java.util.Scanner

class App {
    private val scanner = Scanner(System.`in`)
    private val arhivies = mutableListOf<Archive>()

    private fun showNotesMenu(archive: Archive) {
        val menu = Menu<Note>(
            "Заметки",
            scanner,
            archive.getNotes(),
            { noteTitle -> createNote(archive, noteTitle) },
            { note -> showNoteView(note) })
    }

    fun start() {
        val menu = Menu<Archive>(
            "Архивы",
            scanner,
            arhivies,
            { archiveTitle -> arhivies.add(Archive(archiveTitle)) },
            { archive -> showNotesMenu(archive) })
    }

    fun showNoteView(note: Note) {
        println("\nЗаметка: ${note.title}")
        println("=".repeat(50))
        println(note.getContent())
        println("=".repeat(50))
        return
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