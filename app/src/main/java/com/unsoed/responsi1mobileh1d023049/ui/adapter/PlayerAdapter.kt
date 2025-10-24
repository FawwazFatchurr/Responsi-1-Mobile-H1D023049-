package com.unsoed.responsi1mobileh1d023049.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.unsoed.responsi1mobileh1d023049.R
import com.unsoed.responsi1mobileh1d023049.data.Player
import com.unsoed.responsi1mobileh1d023049.databinding.ItemPlayerBinding
import java.text.SimpleDateFormat
import java.util.Locale

class PlayerAdapter(private var playerList: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun getItemCount() = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        val context = holder.itemView.context

        holder.binding.tvPlayerName.text = player.name
        holder.binding.tvNationality.text = player.nationality

        val cardColor = when (player.position) {
            "Goalkeeper" -> ContextCompat.getColor(context, R.color.colorGoalkeeper)
            "Defender" -> ContextCompat.getColor(context, R.color.colorDefender)
            "Midfielder" -> ContextCompat.getColor(context, R.color.colorMidfielder)
            "Forward", "Attacker" -> ContextCompat.getColor(context, R.color.colorForward)
            else -> ContextCompat.getColor(context, android.R.color.white)
        }
        holder.binding.cardPlayer.setCardBackgroundColor(cardColor)

        holder.itemView.setOnClickListener {
            val dialog = BottomSheetDialog(context)
            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.bottom_sheet_player_detail,
                null
            )

            val detailName = bottomSheetView.findViewById<TextView>(R.id.detail_tvPlayerName)
            val detailDob = bottomSheetView.findViewById<TextView>(R.id.detail_tvDateOfBirth)
            val detailNat = bottomSheetView.findViewById<TextView>(R.id.detail_tvNationality)
            val detailPos = bottomSheetView.findViewById<TextView>(R.id.detail_tvPosition)

            val formattedDate = try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(player.dateOfBirth)
                date?.let { outputFormat.format(it) } ?: player.dateOfBirth
            } catch (e: Exception) {
                player.dateOfBirth
            }

            detailName.text = player.name
            detailDob.text = formattedDate
            detailNat.text = player.nationality
            detailPos.text = player.position

            dialog.setContentView(bottomSheetView)
            dialog.show()
        }
    }
}