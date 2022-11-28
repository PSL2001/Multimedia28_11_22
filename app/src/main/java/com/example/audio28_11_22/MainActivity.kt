package com.example.audio28_11_22

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.audio28_11_22.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mp: MediaPlayer
    lateinit var audioManager: AudioManager
    var pause = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPause.isEnabled = false
        mp = MediaPlayer.create(this, R.raw.cancion1)
        setListeners()
        iniciarVolumen()
    }

    private fun iniciarVolumen() {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

    }

    private fun setListeners() {
        binding.btnPlay.setOnClickListener {
            startCancion()
        }
        binding.btnPause.setOnClickListener {
            pausarCancion()
        }
        binding.btnInicio.setOnClickListener {
            inicioCancion()
        }
    }

    private fun inicioCancion() {
        if (mp != null) {
            mp.seekTo(0);
        }
    }

    private fun pausarCancion() {
        binding.btnPlay.isEnabled = true
        binding.btnPause.isEnabled = false
        mp.pause()
    }

    private fun startCancion() {
        binding.btnPlay.isEnabled = false
        binding.btnPause.isEnabled = true
        mp.start()
    }
}