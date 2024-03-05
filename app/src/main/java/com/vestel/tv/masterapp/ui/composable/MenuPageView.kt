package com.vestel.tv.masterapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MenuHeader(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 10.dp)
    )
    Box(
        Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(Color.White)
    )
}