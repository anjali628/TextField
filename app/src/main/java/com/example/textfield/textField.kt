package com.example.textfield

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldLayout() {

    var name by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    val focusRequester = remember {
        FocusRequester()
    }
    val controller = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        focusRequester.requestFocus()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        TextField(value = name, onValueChange ={
            name=it
        },
            textStyle = TextStyle(
                color = Color.Red,
                fontSize = 20.sp
            ),
            label = {
                Text(text = "Username")
            },
            placeholder = {
                Text(text = "Enter username")
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "")
            },
            trailingIcon = {
                if(name.isNotEmpty())
                Icon(Icons.Default.Close, contentDescription = "")
            },
           // isError = true,
          //  visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,

            ),
          //  singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                //backgroundColor = Color.Magenta,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .focusRequester(focusRequester)
                .height(50.dp),
        keyboardActions = KeyboardActions(onDone = {
            controller?.hide()
        }, onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = "", onValueChange = {} )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(20.dp))

        BasicTextField(value = username, onValueChange = {
            username=it
        },
        decorationBox = {
            Box(modifier = Modifier.fillMaxWidth()
                .border(2.dp, Color.Black,
                RoundedCornerShape(8.dp)
                )){
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Search ,
                        contentDescription = "" )
                    it.invoke()

                }
            }
        },
            cursorBrush = Brush.linearGradient(
                listOf(Color.Blue,
                    Color.Black,
                    Color.Magenta,
                    Color.Gray)
            ),
            onTextLayout ={
                if (it.lineCount == 3){
                    Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

}