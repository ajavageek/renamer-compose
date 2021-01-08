import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DirectoryTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.path,
        onValueChange = { state.path = it },
        modifier = modifier,
    )
}

@Composable
fun PatternTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.pattern,
        onValueChange = { state.pattern = it },
        modifier = modifier,
    )
}

@Composable
fun ReplacementTextField(state: StateModel, modifier: Modifier) {
    TextField(
        value = state.replacement,
        onValueChange = { state.replacement = it },
        modifier = modifier,
    )
}