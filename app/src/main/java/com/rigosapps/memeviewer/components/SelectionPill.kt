import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rigosapps.memeviewer.ui.theme.DarkPurple
import com.rigosapps.memeviewer.ui.theme.LightPurple

@Composable
fun SelectionPill(
    option: ToggleButtonOption,
    selected: Boolean,
    onClick: (option: ToggleButtonOption) -> Unit = {}
) {
    Button(
        onClick = { onClick(option) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        shape = RoundedCornerShape(0),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.padding(14.dp, 0.dp),
    ) {
        Row(
            modifier = Modifier.padding(0.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Title5(
                text = option.text,
                color = if (selected) LightPurple else DarkPurple,
                modifier = Modifier.padding(0.dp),
            )
            if (option.iconRes != null) {
                Icon(
                    painterResource(id = option.iconRes),
                    contentDescription = null,
                    tint = if (selected) LightPurple else DarkPurple,
                    modifier = Modifier.padding(4.dp, 2.dp, 2.dp, 2.dp),
                )
            }
        }
    }
}

@Composable
fun Title5(text: String, color: Any, modifier: Modifier) {



}
