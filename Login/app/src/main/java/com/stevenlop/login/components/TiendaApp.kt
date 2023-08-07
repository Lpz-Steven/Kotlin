package com.stevenlop.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.stevenlop.login.pages.login.LoginScreen
import com.stevenlop.login.R

enum class PagesScreen {
    Start,
    DashBoard
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.proyecto_login)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TiendaAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO */ }
            )
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            NavHost(
                navController = navController,
                startDestination = PagesScreen.Start.name
            ) {
                composable(route = PagesScreen.Start.name) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun ScaffoldContent(
    padding: PaddingValues,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = padding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            content()
        }
    }
}

