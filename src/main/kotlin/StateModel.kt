import androidx.compose.runtime.mutableStateOf
import java.io.File

class StateModel {

    private val pathState = mutableStateOf(System.getProperty("user.home"))
    private val patternState = mutableStateOf("")
    private val replacementState = mutableStateOf("")

    var path: String
        get() = pathState.value
        set(value) {
            pathState.value = value
        }

    var pattern: String
        get() = patternState.value
        set(value) {
            patternState.value = value
        }

    var replacement: String
        get() = replacementState.value
        set(value) {
            replacementState.value = value
        }

    val files: List<File>
        get() = File(path).listFiles()
            ?.filter { !it.isHidden && it.isFile }
            ?.toList()
            ?.sortedBy { it.name }
            ?: emptyList()

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
}
