package com.example.audio28_11_22

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
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
        //Iniciamos el audiomanager
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //Seteamos el volumen maximo
        binding.sbVolumen.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        //
        binding.sbVolumen.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        binding.sbVolumen
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
        binding.sbVolumen.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
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