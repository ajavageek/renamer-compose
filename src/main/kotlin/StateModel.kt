import androidx.compose.runtime.mutableStateOf

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
}
