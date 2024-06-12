package com.kawaki.weather.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kawaki.weather.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(searchState: MutableState<String>, onClick: () -> Unit) {

    val focusRequester = remember { FocusRequester() }
    val dynamicTextBoxColor = if (isSystemInDarkTheme()) Utils.customLightBlack else Utils.customLightWhite

    TextField(value = searchState.value, onValueChange = { searchState.value = it },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults
            .textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = dynamicTextBoxColor,
                textColor = Color.White),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onClick.invoke() }),
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        placeholder = { Text(text = "Udupi", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f))) },
        textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
    )

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }
}