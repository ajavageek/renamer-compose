import androidx.compose.runtime.*
import java.io.File

class StateModel {

    private val tracker = mutableStateOf(false)

    var path: String by mutableStateOf(System.getProperty("user.home"))
    var pattern: String by mutableStateOf("")
    var replacement: String by mutableStateOf("")

    val files: List<File> by derivedStateOf {
        tracker.value
        File(path).listFiles()
            ?.filter { !it.isHidden && it.isFile }
            ?.toList()
            ?.sortedBy { it.name }
            ?: emptyList()
    }

    val candidates: List<File>
        get() {
            val regex = pattern.toRegex()
            return files.map {
                if (pattern.isBlank()) it
                else {
                    val newName = regex.replace(it.name, replacement)
                    File(it.parent, newName)
                }
            }
        }

    fun recompose() {
        val (currentRefresh, setRefresh) = tracker
        setRefresh(currentRefresh.not())
    }
}