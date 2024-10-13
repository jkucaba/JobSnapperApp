package jk.jobsnapper.controllers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jk.jobsnapper.controllers.AdminControllers.AdminActivity
import jk.jobsnapper.controllers.EmployeeControllers.EmployeeActivity
import jk.jobsnapper.controllers.EmployerControllers.EmployerActivity
import jk.jobsnapper.databinding.ActivityMainBinding
import jk.jobsnapper.models.ApiClient
import jk.jobsnapper.models.LoginRequest
import jk.jobsnapper.models.Model
import jk.jobsnapper.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()

        Thread {
            try {
                Model.getInstance(this)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()

        binding.login.setOnClickListener { view ->
            login(view)
        }
    }

    fun login(view: View) {
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        val loginRequest = LoginRequest(email, password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jwt = apiClient.login(loginRequest)
                Model.getInstanceWC().token = jwt
                apiClient.getPublicKey()
                withContext(Dispatchers.Main) {
                    apiClient.setToken(jwt)
                    Toast.makeText(this@MainActivity, "Zalogowano pomyślnie", Toast.LENGTH_SHORT)
                        .show()
                    val role = apiClient.getUserRoleFromToken(jwt)
                    val idUser = apiClient.getIdUserFromToken(jwt)
                    val firstName = apiClient.getFirstNameFromToken(jwt)
                    val lastName = apiClient.getLastNameFromToken(jwt)
                    val email = apiClient.getEmailFromToken(jwt)
                    val birthDate = apiClient.getBirthDateFromToken(jwt)

                    val user = User(idUser, firstName, lastName, email, birthDate, role)

                    Model.getInstanceWC().user = user

                    when (role) {
                        "admin" -> startActivity(Intent(this@MainActivity, AdminActivity::class.java))
                        "pracownik" -> startActivity(Intent(this@MainActivity, EmployeeActivity::class.java))
                        "employer" -> startActivity(Intent(this@MainActivity, EmployerActivity::class.java))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("MainActivity", "Błąd podczas logowania", e)
                    Toast.makeText(this@MainActivity, "Błąd podczas logowania", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}