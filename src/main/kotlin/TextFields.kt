import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DirectoryTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.path,
        onValueChange = { state.path = it },
        modifier = modifier,
        label = { Text("Folder") },
    )
}

@Composable
fun PatternTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.pattern,
        onValueChange = { state.pattern = it },
        modifier = modifier,
        label = { Text("Pattern") },
    )
}

@Composable
fun ReplacementTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.replacement,
        onValueChange = { state.replacement = it },
        modifier = modifier,
        label = { Text("Replacement") },
    )
}