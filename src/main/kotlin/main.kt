import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.SwingPanel
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.swing.JScrollPane


@InternalCoroutinesApi
@ExperimentalComposeApi
fun main() = Window("Renamer Composer") {

    DesktopTheme {
        Column {

            val padding = Modifier.padding(10.dp)

            val tracker = remember { mutableStateOf(false) }
            val state = remember { StateModel(tracker) }

            // We use the LaunchedEffect below to scope a subscription that pushes updates to it.
            val fileModel = remember { FileTableModel() }

            Row {
                Text("Folder:", padding)
                DirectoryTextField(state, padding.weight(1f, true))
                FolderPickerButton(state, padding)
            }
            Row {
                Text("Pattern:", padding)
                PatternTextField(state, padding.weight(0.5f, true))
                Text("Replacement:", padding)
                ReplacementTextField(state, padding.weight(0.5f, true))
            }
            Row(padding.fillMaxWidth().fillMaxHeight(0.85f)) {
                // Monitor candidates and notify the model of updates
                LaunchedEffect(fileModel) {
                    // snapshotFlow runs the block and emits its result whenever
                    // any snapshot state read by the block was changed.
                    snapshotFlow { Pair(state.files, state.candidates) }
                        .collect { fileModel.files = it }
                }
                // Don't recreate the swing UI elements on every recomposition
                val scrollingTable = remember(fileModel) { JScrollPane(FileTable(fileModel)) }
                SwingPanel(scrollingTable)
            }
            Row(Modifier.align(Alignment.End)) {
                ApplyButton(state, padding)
            }
        }
    }
}