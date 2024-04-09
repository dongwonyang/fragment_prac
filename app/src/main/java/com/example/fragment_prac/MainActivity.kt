package com.example.fragment_prac

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.fragment_prac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentDataListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(SecondFragment())

        binding.run {
            btnFrag1.setOnClickListener{
                val dataToSend = "Hello First Fragment!\n" +
                        "From Activity"
                val fragment = FirstFragment.newInstance(dataToSend)
                setFragment(fragment)
            }

            btnFrag2.setOnClickListener {
                val dataToSend = "Hello Second Fragment!\n" +
                        "From Activity"
                val fragment = SecondFragment.newInstance(dataToSend)
                setFragment(fragment)
            }
        }

    }

    private fun setFragment(fragment: Fragment){
        this@MainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDataReceived(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}