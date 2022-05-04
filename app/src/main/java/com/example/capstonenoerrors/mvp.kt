package com.example.capstonenoerrors

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_mvp.view.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import kotlinx.coroutines.*



class Mvp : Fragment() {

    private var sliderData: IntArray = IntArray(8)

    private var playerArray: MutableList<Player> = mutableListOf()

    private var playerStatsArray: MutableList<PlayerStats> = mutableListOf()


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mvp, container, false)

        sliderData = arguments?.getIntArray("sliders")!!


        //Uses the IO Thread in order to grab the players and put them into a list
        GlobalScope.launch(Dispatchers.IO) {
            createPlayersRequest()

            //waits for the first API call to get the players. Then makes an API call for each player to get their season stats for 2020-21
            launch {
                delay(3000)
                var x: Int = 0
                while (x < playerArray.size - 1) {
                        val getPlayerStats =
                            PlayerStats(
                                playerArray[x],
                                getPlayerStatsRequest(playerArray[x].id),
                                0.0
                            )
                        playerStatsArray.add(x, getPlayerStats)
                        x++

                }
                println()
                println("Stats Gathered")
            }
        }

            //Switches to a Default Thread with a delay in order to allow for the API calls to finish before calling determineMVPScore and sortMvpRace
            GlobalScope.launch(Dispatchers.Default) {
                delay(17000)
                playerStatsArray = determineMvpScore(playerStatsArray)
                playerStatsArray = sortMvpRace(playerStatsArray)
            }

        //This Coroutine reverts back to the main thread to update the UI and fill it with the top 10 MVP Candidates
            GlobalScope.launch(Dispatchers.Main){
                delay(21000)
                view.PlayerNameOne.text = playerStatsArray[playerStatsArray.size - 1].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 1].identity.lastName
                view.PlayerPointsOne.text = playerStatsArray[playerStatsArray.size - 1].playerStats[0].toString()
                view.PlayerReboundsOne.text = playerStatsArray[playerStatsArray.size - 1].playerStats[1].toString()
                view.PlayerAssistsOne.text = playerStatsArray[playerStatsArray.size - 1].playerStats[2].toString()

                view.PlayerNameTwo.text = playerStatsArray[playerStatsArray.size - 2].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 2].identity.lastName
                view.PlayerPointsTwo.text = playerStatsArray[playerStatsArray.size - 2].playerStats[0].toString()
                view.PlayerReboundsTwo.text = playerStatsArray[playerStatsArray.size - 2].playerStats[1].toString()
                view.PlayerAssistsTwo.text = playerStatsArray[playerStatsArray.size - 2].playerStats[2].toString()

                view.PlayerNameThree.text = playerStatsArray[playerStatsArray.size - 3].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 3].identity.lastName
                view.PlayerPointsThree.text = playerStatsArray[playerStatsArray.size - 3].playerStats[0].toString()
                view.PlayerReboundsThree.text = playerStatsArray[playerStatsArray.size - 3].playerStats[1].toString()
                view.PlayerAssistsThree.text = playerStatsArray[playerStatsArray.size - 3].playerStats[2].toString()

                view.PlayerNameFour.text = playerStatsArray[playerStatsArray.size - 4].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 4].identity.lastName
                view.PlayerPointsFour.text = playerStatsArray[playerStatsArray.size - 4].playerStats[0].toString()
                view.PlayerReboundsFour.text = playerStatsArray[playerStatsArray.size - 4].playerStats[1].toString()
                view.PlayerAssistsFour.text = playerStatsArray[playerStatsArray.size - 4].playerStats[2].toString()

                view.PlayerNameFive.text = playerStatsArray[playerStatsArray.size - 5].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 5].identity.lastName
                view.PlayerPointsFive.text = playerStatsArray[playerStatsArray.size - 5].playerStats[0].toString()
                view.PlayerReboundsFive.text = playerStatsArray[playerStatsArray.size - 5].playerStats[1].toString()
                view.PlayerAssistsFive.text = playerStatsArray[playerStatsArray.size - 5].playerStats[2].toString()

                view.PlayerNameSix.text = playerStatsArray[playerStatsArray.size - 6].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 6].identity.lastName
                view.PlayerPointsSix.text = playerStatsArray[playerStatsArray.size - 6].playerStats[0].toString()
                view.PlayerReboundsSix.text = playerStatsArray[playerStatsArray.size - 6].playerStats[1].toString()
                view.PlayerAssistsSix.text = playerStatsArray[playerStatsArray.size - 6].playerStats[2].toString()

                view.PlayerNameSeven.text = playerStatsArray[playerStatsArray.size - 7].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 7].identity.lastName
                view.PlayerPointsSeven.text = playerStatsArray[playerStatsArray.size - 7].playerStats[0].toString()
                view.PlayerReboundsSeven.text = playerStatsArray[playerStatsArray.size - 7].playerStats[1].toString()
                view.PlayerAssistsSeven.text = playerStatsArray[playerStatsArray.size - 7].playerStats[2].toString()

                view.PlayerNameEight.text = playerStatsArray[playerStatsArray.size - 8].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 8].identity.lastName
                view.PlayerPointsEight.text = playerStatsArray[playerStatsArray.size - 8].playerStats[0].toString()
                view.PlayerReboundsEight.text = playerStatsArray[playerStatsArray.size - 8].playerStats[1].toString()
                view.PlayerAssistsEight.text = playerStatsArray[playerStatsArray.size - 8].playerStats[2].toString()

                view.PlayerNameNine.text = playerStatsArray[playerStatsArray.size - 9].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 9].identity.lastName
                view.PlayerPointsNine.text = playerStatsArray[playerStatsArray.size - 9].playerStats[0].toString()
                view.PlayerReboundsNine.text = playerStatsArray[playerStatsArray.size - 9].playerStats[1].toString()
                view.PlayerAssistsNine.text = playerStatsArray[playerStatsArray.size - 9].playerStats[2].toString()

                view.PlayerNameTen.text = playerStatsArray[playerStatsArray.size - 10].identity.firstName + " " + playerStatsArray[playerStatsArray.size - 10].identity.lastName
                view.PlayerPointsTen.text = playerStatsArray[playerStatsArray.size - 10].playerStats[0].toString()
                view.PlayerReboundsTen.text = playerStatsArray[playerStatsArray.size - 10].playerStats[1].toString()
                view.PlayerAssistsTen.text = playerStatsArray[playerStatsArray.size - 10].playerStats[2].toString()
            }




            return view

    }

    //This function creates and enqueues a request using OkHttpClient to gather all of the active players in the
    //NBA from the selected API: nba-player-individual-stats (rapidapi.com). It then parses the data
    //to fill a list of players with their id, first name, and last name.
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
                print(playerArray.size)

                val body = response.body?.string()
                if (body != null) {
                    //print(body.subSequence(0, 500))
                }
                val jsonArray = JSONArray(body)
                var x: Int = 0
                while(x < jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(x)
                    val playerId = jsonObject.getInt("id")
                    val firstName = jsonObject.getString("firstName")
                    val lastName = jsonObject.getString("lastName")

                    val player: Player = Player(playerId, firstName, lastName)
//                    println(player.id)
//                    println(player.firstName)
//                    println(player.lastName)
                    playerArray.add(x, player)
                    x++
                }
                println("players created")
            }

        })


    }

    //enqueues a request for getting the players stats using their playerId stores the stats from the
    //"2020-21 season.
    //@param playerId: Int The player id for the player that we are getting stats for
    //@return DoubleArray: an array of doubles that holds the 8 different stats for the given player
    private fun getPlayerStatsRequest(playerId: Int): DoubleArray{
        val statsArray: DoubleArray = DoubleArray(8)

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url("https://nba-player-individual-stats.p.rapidapi.com/playerseasons?playerId=$playerId")
            .get()
            .addHeader("X-RapidAPI-Host", "nba-player-individual-stats.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "9d7a281996mshab05b6cff487c79p1e2c21jsned450e110e73")
            .build()

        val response = okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                print("season api request failed")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    //    print(body.subSequence(0, 500))
                }
                val jsonArray = JSONArray(body)
                var x: Int = 0
                while(x < jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(x)
                    val year = jsonObject.getString("season")
                    if(year == "2020-21"){
                        statsArray[0] = jsonObject.getDouble("pointsPerGame")
                        statsArray[3] = jsonObject.getDouble("blocksPerGame")
                        statsArray[2] = jsonObject.getDouble("assistsPerGame")
                        statsArray[1] = jsonObject.getDouble("reboundsPerGame")
                        statsArray[7] = jsonObject.getDouble("turnoversPerGame")
                        statsArray[6] = jsonObject.getDouble("percentageThree")
                        statsArray[4] = jsonObject.getDouble("percentageFieldGoal")
                        statsArray[5] = jsonObject.getDouble("percentageFreeThrow")
                    }
                    x++

                }

//                println()
//                println(statsArray[0])

            }

        })
        return statsArray
    }


    //This method uses my algorithm to determine the value they bring to the floor as a player according
    // to the users selections with the stats in the sliders fragment.
    //@param List<PlayerStats> A list of PlayerStats objects includes identity, list of stats, and mvp score
    private fun determineMvpScore(playerList: MutableList<PlayerStats>): MutableList<PlayerStats> {
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
                else if(statCount == 3){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 13.0)
                }
                else if(statCount == 4){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 0.5)
                }
                else if(statCount == 5){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 0.3)
                }
                else if(statCount == 6){
                    mvpScore += ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 0.7)
                }
                else if(statCount == 7){
                    mvpScore -= ((sliderData[statCount].toDouble() * playerList[playerCount].playerStats[statCount]) * 7.0)
                }

                statCount++
            }
            playerList[playerCount].playerScore = mvpScore
//            print(playerList[playerCount].identity.firstName + " " + playerList[playerCount].identity.lastName)
//            println(" $mvpScore")
            statCount = 0
            playerCount++
            mvpScore = 0.0

        }

        return playerList
    }


    //Sorts the players and the stats they are associated with in descending order
    //@param List<PlayerStats> A list of PlayerStats objects includes identity, list of stats, and mvp score
    private fun sortMvpRace(playerList: MutableList<PlayerStats>): MutableList<PlayerStats>{
        playerList.sortBy { it.playerScore }
        println("Players sorted")

//        var x: Int = 0
//        while(x < playerList.size){
//            println()
//            println(playerList[x].playerScore)
//        }
        return playerList
    }
}

