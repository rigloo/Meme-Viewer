import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rigosapps.memeviewer.ui.theme.DarkPurple
import com.rigosapps.memeviewer.ui.theme.LightPurple

enum class SelectionType {
    NONE,
    SINGLE,
    MULTIPLE,
}

data class ToggleButtonOption(
    val text: String,
    val iconRes: Int?,
)

@Composable
fun ToggleButton(
    options: Array<ToggleButtonOption>,
    modifier: Modifier = Modifier,
    type: SelectionType = SelectionType.SINGLE,
    onClick: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
) {
    val state = remember { mutableStateMapOf<String, ToggleButtonOption>() }

    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, LightPurple),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkPurple),
        contentPadding = PaddingValues(0.dp, 0.dp),
        modifier = modifier
            .padding(0.dp)
            .height(52.dp),
    ) {
        if (options.isEmpty()) {
            return@OutlinedButton
        }
        val onItemClick: (option: ToggleButtonOption) -> Unit = { option ->
            if (type == SelectionType.SINGLE) {
                options.forEach {
                    val key = it.text
                    if (key == option.text) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
            } else {
                val key = option.text
                if (!state.contains(key)) {
                    state[key] = option
                } else {
                    state.remove(key)
                }
            }
            onClick(state.values.toTypedArray())
        }
        if (options.size == 1) {
            val option = options.first()
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            return@OutlinedButton
        }
        val first = options.first()
        val last = options.last()
        val middle = options.slice(1..options.size - 2)
        SelectionPill(
            option = first,
            selected = state.contains(first.text),
            onClick = onItemClick,
        )
        VerticalDivider()
        middle.map { option ->
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            VerticalDivider()
        }
        SelectionPill(
            option = last,
            selected = state.contains(last.text),
            onClick = onItemClick,
        )
    }
}