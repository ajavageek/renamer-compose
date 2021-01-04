import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import java.io.File
import javax.swing.JFileChooser
import javax.swing.JFileChooser.APPROVE_OPTION

@Composable
fun FolderPickerButton(path: MutableState<String>, modifier: Modifier) {
    val chooseDirectory = {
        val fileChooser = JFileChooser().apply {
            fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        }
        val (currentPath, setPathTo) = path
        fileChooser.currentDirectory = File(currentPath)
        if (fileChooser.showOpenDialog(null) == APPROVE_OPTION) setPathTo(fileChooser.selectedFile.path)
    }
    Button(
        content = { Text("Browse...") },
        onClick = chooseDirectory,
        modifier = modifier,
    )
}