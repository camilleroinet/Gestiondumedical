package com.example.gestionmedical

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo

import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class Login : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricprompt: BiometricPrompt
    private lateinit var promptInfo: PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /****************************/
        /* Declaration des éléments */
        /****************************/
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val register: Button = findViewById(R.id.button_register)

        register.setOnClickListener(){
            if(username.text.toString() == "camille@gmail.com" && password.text.toString() == "camille"){
                val intent = Intent(applicationContext, MainActivity::class.java)
                Toast.makeText(applicationContext, "Authentification réussie", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Mauvais login et password", Toast.LENGTH_LONG).show()
            }
        }
        //1ERE TENTATIVE DE BIOMETRIE
        executor = ContextCompat.getMainExecutor(this)
        biometricprompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence) {
                    super.onAuthenticationError(errorCode,
                        errString)
                    Toast.makeText(applicationContext, "Erreur d'authentification $errString", Toast.LENGTH_LONG).show()
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentification OK", Toast.LENGTH_LONG).show()
                    //vibrateur2()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext,
                        "Authentification pas OK du tout", Toast.LENGTH_LONG).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login par Biometrie")
            .setSubtitle("log in en utilisant ton doigt et le bon (le truc au bout de ta main au cas où !)")
            .setNegativeButtonText("use ton doigt luck")
            .build()
        biometricprompt.authenticate(promptInfo)
    }
}
