import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import java.io.File

class StateModel(private val tracker: MutableState<Boolean>) {

    private val pathState = mutableStateOf(System.getProperty("user.home"))
    private val patternState = mutableStateOf("")
    private val replacementState = mutableStateOf("")
    private val filesState: State<List<File>> = derivedStateOf {
        tracker.value
        File(path).listFiles()
            ?.filter { !it.isHidden && it.isFile }
            ?.toList()
            ?.sortedBy { it.name }
            ?: emptyList()
    }

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
        get() = filesState.value

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
