package br.com.fornaro.matafome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fornaro.matafome.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }
}
