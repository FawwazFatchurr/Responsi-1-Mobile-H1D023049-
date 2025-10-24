package com.unsoed.responsi1mobileh1d023049.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.unsoed.responsi1mobileh1d023049.databinding.FragmentCoachBinding
import com.unsoed.responsi1mobileh1d023049.ui.MainViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class CoachFragment : Fragment() {

    private var _binding: FragmentCoachBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.teamData.observe(viewLifecycleOwner) { team ->
            if (team != null) {
                val coach = team.coach


                val formattedDate = try {

                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

                    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                    val date = inputFormat.parse(coach.dateOfBirth)
                    date?.let { outputFormat.format(it) } ?: coach.dateOfBirth
                } catch (e: Exception) {
                    coach.dateOfBirth // Jika format gagal, tampilkan apa adanya
                }

                binding.tvCoachName.text = coach.name
                binding.tvCoachNationality.text = coach.nationality
                binding.detailTvDateOfBirth.text = formattedDate
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}