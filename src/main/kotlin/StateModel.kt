import androidx.compose.runtime.*
import java.io.File
import java.lang.IllegalArgumentException
import java.util.regex.PatternSyntaxException

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
            return try {
                val regex = pattern.toRegex()
                files.map {
                    if (pattern.isBlank()) it
                    else {
                        val newName = regex.replace(it.name, replacement)
                        File(it.parent, newName)
                    }
                }
            }
            catch(e: PatternSyntaxException) { files }
            catch(e: IllegalArgumentException) { files }
        }

    fun recompose() {
        val (currentRefresh, setRefresh) = tracker
        setRefresh(currentRefresh.not())
    }
}