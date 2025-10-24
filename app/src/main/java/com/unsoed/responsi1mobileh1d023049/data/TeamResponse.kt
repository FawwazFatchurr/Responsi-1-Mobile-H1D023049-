package com.unsoed.responsi1mobileh1d023049.data

// Ini adalah model data utama
data class TeamResponse(
    val name: String,         // Nama Klub
    val crest: String,        // URL Logo Klub
    val coach: Coach,         // Objek Pelatih
    val squad: List<Player>   // Daftar Pemain
)

// Model untuk Pelatih
data class Coach(
    val name: String,
    val nationality: String,
    val dateOfBirth: String
)

// Model untuk Pemain
data class Player(
    val name: String,
    val position: String,
    val nationality: String,
    val dateOfBirth: String
)