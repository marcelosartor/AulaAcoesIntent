package br.com.msartor.aulaacoesintent


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aulaacoesintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        verificarPermissao()

        binding.btnExecutar.setOnClickListener {
           //chamadaTelefonica()
           compartilharTexto()
        }
    }

    private fun compartilharTexto() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, "Olá Marcelo")
            type = "text/plain"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val shareIntent = Intent.createChooser(intent, "Compartilhar")
        startActivity(shareIntent)

    }

    private fun chamadaTelefonica() {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:+5548991112222")
        }
        startActivity(intent)
    }

    private fun verificarPermissao() {
        val permissaoLigacao = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CALL_PHONE
        )
        if(permissaoLigacao == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                1
            )
        }
    }


}