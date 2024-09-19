package com.pdm.weatherapp.ui.pages

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.pdm.weatherapp.db.fb.FBDatabase
import com.pdm.weatherapp.model.User

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {

    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var confirmaSenha by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    val fbDB = remember { FBDatabase() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Crie sua Conta",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Digite seu nome") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { nome = it }
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = senha,
            label = { Text(text = "Digite sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { senha = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = confirmaSenha,
            label = { Text(text = "Confirme sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { confirmaSenha = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Firebase.auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(activity!!) { task ->
                        if(task.isSuccessful) {
                            fbDB.register(User(nome, email))
                            Toast.makeText(activity,
                                "Registro OK!", Toast.LENGTH_LONG).show()
                            activity.finish()
                        } else {
                            Toast.makeText(activity,
                                "Registro Falhou!", Toast.LENGTH_LONG).show()
                        }
                    }
            },
            enabled = nome.isNotEmpty() && email.isNotEmpty() && senha == confirmaSenha
        ) {
            Text(
                text = "Criar Conta"
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                nome = ""; email = ""; senha = ""; confirmaSenha = ""
            }
        )
        {
            Text(
                text = "Limpar"
            )
        }
    }
}