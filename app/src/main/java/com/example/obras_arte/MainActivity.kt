@file:Suppress("UNUSED_EXPRESSION")

package com.example.obras_arte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obras_arte.ui.theme.Obras_ArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Obras_ArteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DadosObras()
                }
            }
        }
    }
}

@Composable
fun DadosObras() {

    val obraAtual = remember {
        mutableStateOf(1)
    }
    when (obraAtual.value) {
        1 ->
            Obras(
                imagemObraID = R.drawable.the_arnolfini_marriage_painting,
                tituloObra = R.string.titulo_casal,
                autorObra = R.string.autor_casal,
                proximaObra = {
                    obraAtual.value = 2
                },
                obraAnterior = {
                    obraAtual.value = 1
                })

        2-> Obras(imagemObraID =R.drawable.obra2 ,
            tituloObra = R.string.titulo_ronda,
            autorObra = R.string.autor_ronda,
            proximaObra = {
                          obraAtual.value=3
            },
            obraAnterior = {obraAtual.value=1})

        3-> Obras(imagemObraID =R.drawable.las_meninas_de_diego_velazquez_painting ,
            tituloObra = R.string.titulo_meninas,
            autorObra = R.string.autor_meninas,
            proximaObra ={obraAtual.value=4} ,
            obraAnterior = {obraAtual.value=2})

        4-> Obras(imagemObraID = R.drawable.medusa_de_caravaggio,
            tituloObra = R.string.titulo_medusa,
            autorObra = R.string.autor_medusa,
            proximaObra = {obraAtual.value=1},
            obraAnterior = {obraAtual.value=3})
    }
}


@Composable
fun Obras(
    imagemObraID: Int,
    tituloObra: Int,
    autorObra: Int,
    obraAnterior: () -> Unit,
    proximaObra: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = imagemObraID), contentDescription = null)

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = tituloObra), fontSize = 25.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
        )
        Text(text = stringResource(id = autorObra))
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = obraAnterior,
            modifier = Modifier
                .align(alignment = Alignment.Bottom)
                .padding(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.anterior),
                fontSize = 12.sp
            )
        }
        Button(
            onClick = proximaObra,
            modifier = Modifier
                .padding(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.proxima_obra),
                fontSize = 12.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Obras_ArteTheme {
        DadosObras()
    }
}