package com.example.progressojogador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PlayerProgressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayerProgressApp()
        }
    }
}

@Composable
fun PlayerProgressApp() {
    val players = remember { mutableStateListOf<Player>(Player(), Player(), Player(), Player(), Player(), Player()) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Progresso dos Players", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        players.forEachIndexed { index, player ->
            PlayerCard(player = player, playerNumber = index + 1)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class Player(
    var name: String = "",
    var level: Int = 1,
    var equipmentBonus: Int = 0,
    var modifiers: Int = 0
) {
    fun totalPower(): Int {
        return level + equipmentBonus + modifiers
    }
}

@Composable
fun PlayerCard(player: Player, playerNumber: Int) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Player $playerNumber", fontSize = 20.sp)
            TextField(
                value = player.name,
                onValueChange = { player.name = it },
                label = { Text("Nome do Player") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nível: ${player.level}", fontSize = 18.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    if (player.level > 1) player.level--
                }) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (player.level < 10) player.level++
                }) {
                    Text(text = "+")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Bônus de Equipamento: ${player.equipmentBonus}", fontSize = 18.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { player.equipmentBonus-- }) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { player.equipmentBonus++ }) {
                    Text(text = "+")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Modificadores: ${player.modifiers}", fontSize = 18.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { player.modifiers-- }) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { player.modifiers++ }) {
                    Text(text = "+")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Poder Total: ${player.totalPower()}", fontSize = 18.sp)
        }
    }
}
