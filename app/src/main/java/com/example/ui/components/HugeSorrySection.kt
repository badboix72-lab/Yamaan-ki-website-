package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HugeSorrySection(
  userName: String,
  onSendHug: () -> Unit,
  onSendWhatsAppHug: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pulsing")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.15f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 900, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "heartPulse"
  )

  val haloAlpha by infiniteTransition.animateFloat(
    initialValue = 0.2f,
    targetValue = 0.55f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "haloGlow"
  )

  AnimatedVisibility(
    visible = true,
    enter = fadeIn(tween(600)) + slideInVertically(tween(600))
  ) {
    Column(
      modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Grand pulsing heart icon with radiant halo rings
      Box(
        modifier = Modifier
          .size(130.dp)
          .padding(8.dp),
        contentAlignment = Alignment.Center
      ) {
        // Outer halo
        Box(
          modifier = Modifier
            .size(120.dp)
            .scale(pulseScale * 1.08f)
            .clip(CircleShape)
            .background(
              MaterialTheme.colorScheme.primary.copy(alpha = haloAlpha * 0.4f)
            )
        )
        // Middle ring
        Box(
          modifier = Modifier
            .size(96.dp)
            .scale(pulseScale)
            .clip(CircleShape)
            .background(
              Brush.radialGradient(
                colors = listOf(
                  MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                  MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
                )
              )
            )
        )
        // Center heart
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = "Beating heart",
          modifier = Modifier
            .size(54.dp)
            .scale(pulseScale),
          tint = Color.White
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // The HUGE SORRY Typography
      Text(
        text = "I AM SO SORRY",
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Black,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        modifier = Modifier.testTag("huge_sorry_header")
      )

      Spacer(modifier = Modifier.height(6.dp))

      // PROMINENT NAME DISPLAY
      Surface(
        modifier = Modifier
          .padding(vertical = 6.dp)
          .shadow(elevation = 8.dp, shape = RoundedCornerShape(28.dp))
          .testTag("prominent_name_banner"),
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
      ) {
        Row(
          modifier = Modifier
            .background(
              brush = Brush.horizontalGradient(
                colors = listOf(
                  MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                  MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f),
                  MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                )
              )
            )
            .padding(horizontal = 24.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = userName.uppercase(),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "🥺❤️",
            fontSize = 32.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = "Please forgive me, my love. You mean the whole world to me.",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Romantic Apology Letter Card
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              brush = Brush.verticalGradient(
                colors = listOf(
                  MaterialTheme.colorScheme.surface,
                  MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                )
              )
            )
            .padding(20.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.VolunteerActivism,
                  contentDescription = null,
                  modifier = Modifier.size(18.dp),
                  tint = MaterialTheme.colorScheme.primary
                )
              }
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = "Letter from Yamaan's Heart",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
              )
            }

            Text(
              text = "💌 Forever Yours",
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              color = MaterialTheme.colorScheme.secondary
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "My Dearest $userName,",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "I am writing this because words alone can hardly express how deeply sorry I am for making you sad or hurting your feelings. Your smile is my daily sunshine, and knowing that I caused you pain breaks my heart into pieces.\n\n" +
              "You are the most precious person in my life. Every laughter we share, every dream we talk about, and every small moment with you is a treasure I protect with my whole soul. I made a mistake, and I promise to listen better, care deeper, and be the person who always brings happiness and peace into your days.\n\n" +
              "Please look into my heart and feel how true and endless my love is for you. I will do whatever it takes to earn back that sweetest smile.",
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.88f),
            lineHeight = 25.sp
          )

          Spacer(modifier = Modifier.height(14.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(horizontalAlignment = Alignment.End) {
              Text(
                text = "With all my endless love & apologies,",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
              )
              Text(
                text = "Yamaan ❤️",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Button to send a hug on WhatsApp directly to Yamaan (+91 93364 35690)
      Button(
        onClick = onSendWhatsAppHug,
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("send_whatsapp_hug_button"),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF25D366) // WhatsApp official brand green
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Send Hug on WhatsApp to Yamaan 💬🫂",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Button to send a virtual in-app hug back to Yamaan
      Button(
        onClick = onSendHug,
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("send_hug_button"),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = null,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Send Virtual Hug & Hearts 🫂💕",
          fontSize = 15.sp,
          fontWeight = FontWeight.SemiBold
        )
      }
    }
  }
}
