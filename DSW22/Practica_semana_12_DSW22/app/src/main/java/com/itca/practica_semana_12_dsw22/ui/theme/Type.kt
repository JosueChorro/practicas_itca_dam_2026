package com.itca.practica_semana_12_dsw22.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.itca.practica_semana_12_dsw22.R

val Font2 = FontFamily(
    Font(R.font.roboto_boldtalic, FontWeight.Bold),
    Font(R.font.roboto_mediumitalic, FontWeight.Normal),
    Font(R.font.roboto_semibolditalic, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 2.sp
    )
)

val FontOfficial = Typography(
    bodyMedium = TextStyle(
        fontFamily = Font2,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 2.sp
    )
)