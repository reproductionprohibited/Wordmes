package fragments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InformationSpacer(height: Int = 24) = Spacer(modifier = Modifier.height(height.dp))