package com.unsoed.responsi1mobileh1d023049.network

import com.unsoed.responsi1mobileh1d023049.data.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("X-Auth-Token: 4c8626afc6254839aa1dd6d7552d3c4f")
    @GET("v4/teams/72") // ID Klub Anda 72
    suspend fun getTeamDetail(): Response<TeamResponse>
}