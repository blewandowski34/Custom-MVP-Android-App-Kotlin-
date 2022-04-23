package com.example.capstonenoerrors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_mvp.view.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.IOException


class Mvp : Fragment() {

    var sliderData : IntArray = IntArray(9)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mvp, container, false)

        sliderData = arguments?.getIntArray("options")!!

        view.first_number.text = sliderData[0].toString()
        view.second_number.text = sliderData[1].toString()
        view.third_number.text = sliderData[2].toString()
        view.fourth_number.text = sliderData[3].toString()
        view.fifth_number.text = sliderData[4].toString()
        view.sixth_number.text = sliderData[5].toString()
        view.seventh_number.text = sliderData[6].toString()
        view.eighth_number.text = sliderData[7].toString()
        view.ninth_number.text = sliderData[8].toString()

        createPlayersRequest()

        return view
    }



    private fun createPlayersRequest() {
        val request =  Request.Builder()
            .url("https://nba-player-individual-stats.p.rapidapi.com/players")
            .get()
            .addHeader("X-RapidAPI-Host", "nba-player-individual-stats.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "9d7a281996mshab05b6cff487c79p1e2c21jsned450e110e73")
            .build()

        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                print("player api request failed")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                print(body)


                val gson = GsonBuilder().create()
                val playerList = gson.fromJson(body, PlayerList::class.java)
                print(playerList)



            }

        })

    }
    private fun makePlayerListRequest() {






//        val body = playerName.body?.string()
//        val jsonPlayers = JSONObject(body)
//        val jsonPlayerArray: JSONArray = jsonPlayers.getJSONArray(body)
//        var x: Int = 0
//        val size: Int = jsonPlayerArray.length()
//        playerList = ArrayList();
//        for(i in 0 until size){
//            val jsonPlayerDetails: JSONObject = jsonPlayerArray.getJSONObject(i)
//            val player: Player = Player()
//            player.playerId = jsonPlayerDetails.getInt("id")
//            val nameAppender: String = jsonPlayerDetails.getString("firstName") + jsonPlayerDetails.getString("lastName")
//            player.name = nameAppender
//            playerList.add(player)
        }










    fun determineMvpScore(playerList: List<PlayerStats>): List<PlayerStats> {
        var mvpScore: Double = 0.0
        var playerCount: Int = 0
        var statCount: Int = 0

        while(playerCount < playerList.size){
            while(statCount < playerList[playerCount].playerStats.size){

                if(statCount == 0) {
                    mvpScore += (sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount])
                }
                else if(statCount == 1 || statCount == 2){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 2.0)
                }
                else if(statCount == 3 || statCount == 4){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 10.0)
                }
                else if(statCount == 5){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 40.0)
                }
                else if(statCount == 6){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 35.0)
                }
                else if(statCount == 7){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 55.0)
                }
                else if(statCount == 8){
                    mvpScore -= ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 5.0)
                }

                statCount++
            }
            playerList[playerCount].playerScore = mvpScore
            statCount = 0
            playerCount++

        }

        return playerList
    }

    fun sortMvpRace(playerList: List<Player>){

    }

    fun getData(){

    }


}