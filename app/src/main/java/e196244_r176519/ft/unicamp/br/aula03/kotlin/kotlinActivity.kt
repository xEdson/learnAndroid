package e196244_r176519.ft.unicamp.br.aula03.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import e196244_r176519.ft.unicamp.br.aula03.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class kotlinActivity : AppCompatActivity() {




    var value : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null){
            value = savedInstanceState.getString("chave","")
        }
        setContentView(R.layout.activity_kotlin)

        btPull.setOnClickListener {
            saida.text = value
        }

        btPush.setOnClickListener {
            value = txtVal.text.toString()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("chave", value)
        outState?.putString("chave", value)
    }


}
