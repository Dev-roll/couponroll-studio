package app.web.couponrollstudio.ui.store_settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StoreSettingsScreen() {
    ThemeSettings()
    ToggleSettings()
}

@Composable
fun ThemeSettings() {
    var selectedTheme by remember { mutableStateOf("Light") }

    Column(Modifier.padding(16.dp)) {
        Text(text = "テーマ")
        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedTheme == "Light",
                onClick = { selectedTheme = "Light" },
                Modifier.clickable { selectedTheme = "Light" }
            )
            Text(
                text = "ライト",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { selectedTheme = "Light" }
            )
        }
        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedTheme == "Dark",
                onClick = { selectedTheme = "Dark" },
                Modifier.clickable { selectedTheme = "Dark" }
            )
            Text(
                text = "ダーク",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { selectedTheme = "Dark" }
            )
        }
        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedTheme == "System",
                onClick = { selectedTheme = "System" },
                Modifier.clickable { selectedTheme = "System" }
            )
            Text(
                text = "システム",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { selectedTheme = "System" }
            )
        }
    }
}

@Composable
fun ToggleSettings() {
    // トグルボタンの状態を保持するための変数を作成する
    val isChecked = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            modifier = Modifier.padding(8.dp)
        )
    }
}
