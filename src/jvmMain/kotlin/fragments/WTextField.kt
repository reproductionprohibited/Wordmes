package fragments

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme

@Composable
fun WTextField(
    value: String,
    onValueChange: (input: String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    singleLine: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = singleLine,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            textColor = theme.TextColor.copy(alpha = alpha),
            backgroundColor = theme.TextFieldColor.copy(alpha = alpha),
            focusedIndicatorColor = theme.TextFieldColor.copy(alpha = alpha),
            focusedLabelColor = theme.TextColor.copy(alpha = alpha)
        ),
    )
}