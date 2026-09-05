package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgivenessSection(
  userName: String,
  isForgiven: Boolean,
  onForgiveClick: () -> Unit,
  onSendWhatsAppHug: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  var showPromises by remember { mutableStateOf(false) }
  var selectedPenalty by remember { mutableStateOf<String?>(null) }

  val penalties = listOf(
    "Treat $userName to her absolute favorite meal 🍝",
    "Give $userName 100 warm cuddles today 🫂",
    "Admit that $userName is always 100% right 👑",
    "Write a personalized romantic poem for $userName ✍️",
    "Give $userName uninterrupted foot/back massage 💆‍♀️",
    "Promise to never ever repeat this mistake again 🤞"
  )

  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
    shape = RoundedCornerShape(24.dp),
    color = MaterialTheme.colorScheme.surface
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          brush = Brush.verticalGradient(
            colors = listOf(
              MaterialTheme.colorScheme.surface,
              MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
            )
          )
        )
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Will You Forgive Yamaan?",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "A sincere plea from Yamaan's heart to $userName",
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(16.dp))

      if (isForgiven) {
        // CELEBRATION CARD
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .testTag("forgiven_celebration_card"),
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
          ),
          border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Box(
              modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Celebration,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = Color.White
              )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
              text = "THANK YOU, MY LOVE $userName! 🎉❤️",
              style = MaterialTheme.typography.titleLarge,
              fontWeight = FontWeight.ExtraBold,
              color = MaterialTheme.colorScheme.primary,
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "Yamaan is the happiest person in the universe! You just made his whole world brighter. Yamaan promises to treasure you, protect your smile, and love you more and more every single day.",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface,
              textAlign = TextAlign.Center,
              lineHeight = 22.sp
            )

            if (onSendWhatsAppHug != null) {
              Spacer(modifier = Modifier.height(14.dp))
              Button(
                onClick = onSendWhatsAppHug,
                modifier = Modifier
                  .fillMaxWidth()
                  .height(48.dp)
                  .testTag("forgiveness_whatsapp_hug_button"),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = Color(0xFF25D366)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Favorite,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Send Yamaan a Hug on WhatsApp 💬🫂",
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              }
            }
          }
        }
      } else {
        // Forgive Action Button
        Button(
          onClick = onForgiveClick,
          modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .testTag("forgive_button"),
          shape = RoundedCornerShape(27.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
          ),
          elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Yes, I Forgive You Yamaan 🥰",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Secondary interactive actions
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = { showPromises = !showPromises },
            modifier = Modifier
              .weight(1f)
              .testTag("convince_me_button"),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
          ) {
            Text(
              text = if (showPromises) "Hide Promises" else "Convince Me 🥺",
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = MaterialTheme.colorScheme.primary
            )
          }
        }
      }

      // Convince me / Sweet promises reveal
      AnimatedVisibility(
        visible = showPromises && !isForgiven,
        enter = fadeIn(tween(400)) + expandVertically(tween(400))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Yamaan's Solemn Promises to $userName:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )

          Spacer(modifier = Modifier.height(10.dp))

          val promises = listOf(
            "🌹 Fresh flowers & heartfelt notes whenever you're low",
            "🍫 Never-ending supply of your favorite comfort food",
            "👂 Patient, active listening with zero arguments",
            "✨ Giving you my full attention whenever you talk",
            "💖 Putting your happiness and peace first, always"
          )

          promises.forEach { promise ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = promise,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Penalty picker
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "🎯 Pick a sweet penalty for Yamaan:",
          style = MaterialTheme.typography.labelLarge,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
          verticalArrangement = Arrangement.spacedBy(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          penalties.forEach { penalty ->
            val isChosen = selectedPenalty == penalty
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(
                  if (isChosen) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                  else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                )
                .border(
                  width = 1.dp,
                  color = if (isChosen) MaterialTheme.colorScheme.primary
                  else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                  shape = RoundedCornerShape(12.dp)
                )
                .clickable { selectedPenalty = penalty }
                .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(
                  text = penalty,
                  fontSize = 13.sp,
                  fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                  color = if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                if (isChosen) {
                  Text(
                    text = "Accepted! 🫡",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}
