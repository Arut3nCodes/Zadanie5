package com.example.zadanie5

public class DataItem {
    var playerName : String = "Jankos"
    var teamName : String = "Team Heretics"
    var wins : Int = 463
    var grade : Float = 3.5f
    constructor()
    constructor(num : Int) : this() {
        playerName = "Jankos" + num
    }
    constructor(playerName : String, teamName : String, wins : Int, grade : Float){
        this.playerName = playerName
        this.teamName = teamName
        this.wins = wins
        this.grade = grade
    }
}