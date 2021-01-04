import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun DirectoryTextField(path: MutableState<String>, modifier: Modifier) {
    TextField(
        value = path.value,
        onValueChange = { path.value = it },
        modifier = modifier,
    )
}

@Composable
fun PatternTextField(pattern: MutableState<String>, modifier: Modifier) {
    TextField(
        value = pattern.value,
        onValueChange = { pattern.value = it },
        modifier = modifier,
    )
}

@Composable
fun ReplacementTextField(replacement: MutableState<String>, modifier: Modifier) {
    TextField(
        value = replacement.value,
        onValueChange = { replacement.value = it },
        modifier = modifier,
    )
}