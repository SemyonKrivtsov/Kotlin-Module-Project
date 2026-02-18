sealed class Entity(open val title: String) {

    data class Note(
        override val title: String,
        private val text: String
    ) : Entity(title) {
        fun getContent(): String = text
    }

    class Archive(override val title: String) : Entity(title) {
        private val notes = mutableListOf<Note>()

        fun addNote(note: Note) {
            notes.add(note)
        }

        fun getNotes(): List<Note> = notes.toList()
    }
}