package com.unsoed.responsi1mobileh1d023049.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobileh1d023049.databinding.FragmentTeamSquadBinding
import com.unsoed.responsi1mobileh1d023049.ui.MainViewModel
import com.unsoed.responsi1mobileh1d023049.ui.adapter.PlayerAdapter

class TeamSquadFragment : Fragment() {

    private var _binding: FragmentTeamSquadBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTeamSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.teamData.observe(viewLifecycleOwner) { team ->
            if (team != null) {
                // Update data di adapter
                playerAdapter = PlayerAdapter(team.squad)
                binding.rvPlayers.adapter = playerAdapter
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    private fun setupRecyclerView() {
        binding.rvPlayers.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}