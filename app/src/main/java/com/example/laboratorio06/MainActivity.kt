package com.example.laboratorio06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio06.ui.theme.Laboratorio06Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio06Theme {
                val navController = rememberNavController()
                AppNavigator(navController)
            }
        }
    }
}

// Sistema de navegación con NavHost
@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            CustomScaffold(navController)
        }
        composable("profile_screen") {
            UserProfileScreen()
        }
        composable("build_screen") { BuildScreen() }
        composable("menu_screen") { MenuScreen() }
        composable("favorite_screen") { FavoriteScreen() }
        composable("delete_screen") { DeleteScreen() }
    }
}

// Función para mostrar la pantalla principal con navegación
@Composable
fun CustomScaffold(navController: NavHostController) {
    var clickCount by remember { mutableStateOf(0) }  // Estado para contar clics

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB { clickCount++ } },  // Incrementar contador al hacer clic
        content = { padding ->
            CustomContent(padding, clickCount)  // Pasar contador al contenido
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Sample Title") },
        actions = {
            IconButton(onClick = { navController.navigate("profile_screen") }) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { navController.navigate("build_screen") }) {
                Icon(Icons.Filled.Build, contentDescription = "Build description")
            }
            IconButton(onClick = { navController.navigate("menu_screen") }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu description")
            }
            IconButton(onClick = { navController.navigate("favorite_screen") }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite description")
            }
            IconButton(onClick = { navController.navigate("delete_screen") }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete description")
            }
        }
    }
}

// Vistas individuales para cada botón
@Composable
fun BuildScreen() {
    Text(text = "Pantalla de Configuración")
}

@Composable
fun MenuScreen() {
    Text(text = "Pantalla de Menú")
}

@Composable
fun FavoriteScreen() {
    Text(text = "Pantalla de Favoritos")
}

@Composable
fun DeleteScreen() {
    Text(text = "Pantalla de Eliminar")
}

@Composable
fun UserProfileScreen() {
    Text(text = "Esta es la pantalla de perfil del usuario")
}

// Boton y conteo de los clicks
@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Text(
            fontSize = 24.sp,
            text = "click"
        )
    }
}

@Composable
fun CustomContent(padding: androidx.compose.foundation.layout.PaddingValues, clickCount: Int) {
    Column(modifier = Modifier.padding(padding), verticalArrangement = Arrangement.Center) {
        Text(text = "Mi sexta aplicación móvil, equide")
        Text(text = "Número de clics: $clickCount")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio06Theme {
        val navController = rememberNavController()
        CustomScaffold(navController = navController)
    }
}
