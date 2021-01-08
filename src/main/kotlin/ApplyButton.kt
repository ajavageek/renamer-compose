import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ApplyButton(
    state: StateModel,
    modifier: Modifier
) {
    val rename = {
        var renamed = false
        state.files.forEachIndexed { index, file ->
            val candidate = state.candidates[index]
            println("File.....: ${file.name}")
            println("Candidate: ${candidate.name}")
            if (file != candidate) {
                file.renameTo(candidate)
                renamed = true
            }
        }
        if (renamed) state.refresh()
    }
    Button(
        content = { Text("Apply") },
        onClick = rename,
        modifier = modifier,
    )
}