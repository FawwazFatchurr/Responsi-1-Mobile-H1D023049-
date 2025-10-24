package com.unsoed.responsi1mobileh1d023049.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobileh1d023049.databinding.ActivityMainBinding
import com.unsoed.responsi1mobileh1d023049.ui.fragments.CoachFragment
import com.unsoed.responsi1mobileh1d023049.ui.fragments.HistoryFragment
import com.unsoed.responsi1mobileh1d023049.ui.fragments.TeamSquadFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar (Opsional, jika Anda mau tampilan full seperti gambar)
        supportActionBar?.hide()

        // Observasi data klub untuk mengisi header dan info utama
        viewModel.teamData.observe(this) { team ->
            if (team != null) {
                // Set nama klub di header
                binding.tvHeaderClubName.text = team.name

                // Muat logo klub
                Glide.with(this)
                    .load(team.crest)
                    .into(binding.ivClubLogo)
            }
        }

        // Observasi status loading
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBarMain.isVisible = isLoading
        }

        // Setup listener untuk CardView navigasi
        binding.cardNavHistory.setOnClickListener {
            // Kita akan membuka fragment di halaman penuh
            openFragment(HistoryFragment())
        }

        binding.cardNavCoach.setOnClickListener {
            openFragment(CoachFragment())
        }

        binding.cardNavSquad.setOnClickListener {
            openFragment(TeamSquadFragment())
        }
    }

    // Fungsi untuk membuka Fragment di layar penuh
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(android.R.id.content, fragment) // Mengganti seluruh konten Activity
        transaction.addToBackStack(null) // Agar bisa di-back
        transaction.commit()
    }
}