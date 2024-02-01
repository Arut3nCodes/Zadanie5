package com.example.zadanie5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class DBItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "playerName")
    var playerName = "Jankos"

    @ColumnInfo(name = "teamName")
    var teamName = "Team Heretics"

    @ColumnInfo(name = "wins")
    var wins = 463

    @ColumnInfo(name = "grade")
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DBItem

        if (id != other.id) return false
        if (playerName != other.playerName) return false
        if (teamName != other.teamName) return false
        if (wins != other.wins) return false
        if (grade != other.grade) return false

        return true
    }

}