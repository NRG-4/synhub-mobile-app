package com.example.synhub.requests.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.R

@Composable
fun RequestAndValidationList(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(30.dp)
            .padding(top = 90.dp),
    ) {
        Card(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .height(260.dp),
            elevation = CardDefaults
                .cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A4E85)
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.becker), // Replace with actual image
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Alissa Becker",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                Row() {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(160.dp)
                            .width(280.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            Text(text = "Tarea 1", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(thickness = 2.dp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Descripción tarea 1", color = Color.Black)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .height(160.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF4CAF50))

                    )
                }
            }

        }

        Card(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .height(260.dp),
            elevation = CardDefaults
                .cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A4E85)
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.carlos), // Replace with actual image
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Jose Carlos",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                Row() {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(160.dp)
                            .width(280.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            Text(text = "Tarea 2", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(thickness = 2.dp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Descripción tarea 2", color = Color.Black)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .height(160.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFF44336))

                    )
                }
            }

        }
    }
}