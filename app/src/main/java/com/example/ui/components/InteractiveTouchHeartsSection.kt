package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SweetHeartItem(
  val id: Int,
  val shortLabel: String,
  val message: String,
  val subtext: String,
  val iconEmoji: String,
  val primaryColor: Color,
  val secondaryColor: Color,
  val heartSize: Dp = 64.dp,
  val animDurationMs: Int = 2400
)

val sweetHeartList = listOf(
  SweetHeartItem(
    id = 1,
    shortLabel = "Gorgeous",
    message = "You're gorgeous! ✨",
    subtext = "Every single time I look at you, my heart skips a beat. You take my breath away, Aqsa.",
    iconEmoji = "✨",
    primaryColor = Color(0xFFFF1744),
    secondaryColor = Color(0xFFFF5252),
    heartSize = 70.dp,
    animDurationMs = 2100
  ),
  SweetHeartItem(
    id = 2,
    shortLabel = "Prettiest",
    message = "You're the most prettiest! 🌸",
    subtext = "In the entire universe, among billions of stars, no one shines even half as prettily as you.",
    iconEmoji = "🌸",
    primaryColor = Color(0xFFE91E63),
    secondaryColor = Color(0xFFFF4081),
    heartSize = 76.dp,
    animDurationMs = 2600
  ),
  SweetHeartItem(
    id = 3,
    shortLabel = "Radiant Smile",
    message = "Your smile lights up my whole world! ☀️💖",
    subtext = "Seeing you smile is the warmest feeling in the world. It turns any ordinary day into pure magic.",
    iconEmoji = "☀️",
    primaryColor = Color(0xFFFF6D00),
    secondaryColor = Color(0xFFFFAB40),
    heartSize = 66.dp,
    animDurationMs = 2300
  ),
  SweetHeartItem(
    id = 4,
    shortLabel = "Purest Soul",
    message = "You have the kindest, purest heart! 🥺💕",
    subtext = "Your empathy, your gentle care, and your sweet soul are things I admire and cherish endlessly.",
    iconEmoji = "💖",
    primaryColor = Color(0xFF9C27B0),
    secondaryColor = Color(0xFFBA68C8),
    heartSize = 68.dp,
    animDurationMs = 2500
  ),
  SweetHeartItem(
    id = 5,
    shortLabel = "Cutest Ever",
    message = "You are the cutest human ever created! 🥰",
    subtext = "Every adorable little expression and sweet laugh of yours makes me fall head over heels for you.",
    iconEmoji = "🥰",
    primaryColor = Color(0xFFEC407A),
    secondaryColor = Color(0xFFF48FB1),
    heartSize = 72.dp,
    animDurationMs = 2200
  ),
  SweetHeartItem(
    id = 6,
    shortLabel = "My Forever",
    message = "You're my forever and always! 💍❤️",
    subtext = "I don't just love you for today; I love you for every tomorrow, through every up and down.",
    iconEmoji = "💍",
    primaryColor = Color(0xFFD50000),
    secondaryColor = Color(0xFFFF1744),
    heartSize = 74.dp,
    animDurationMs = 2700
  ),
  SweetHeartItem(
    id = 7,
    shortLabel = "One in a Trillion",
    message = "No one in this world compares to you! 💫",
    subtext = "You are unique, special, and irreplaceable. There is truly only one Aqsa in this whole universe.",
    iconEmoji = "💫",
    primaryColor = Color(0xFF7B1FA2),
    secondaryColor = Color(0xFF9C27B0),
    heartSize = 64.dp,
    animDurationMs = 2400
  ),
  SweetHeartItem(
    id = 8,
    shortLabel = "Breathtaking",
    message = "You're breathtakingly beautiful! 🌹",
    subtext = "Inside and out, your grace, elegance, and beauty leave me completely spellbound every single day.",
    iconEmoji = "🌹",
    primaryColor = Color(0xFFC2185B),
    secondaryColor = Color(0xFFE91E63),
    heartSize = 70.dp,
    animDurationMs = 2800
  )
)

@Composable
fun InteractiveTouchHeartsSection(
  userName: String,
  onHeartTouched: (SweetHeartItem) -> Unit,
  onShareToWhatsApp: ((SweetHeartItem) -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  var selectedHeart by remember { mutableStateOf<SweetHeartItem?>(sweetHeartList[0]) }
  var touchedCount by remember { mutableIntStateOf(0) }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
    shape = RoundedCornerShape(24.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color(0xFFFFF0F3),
              MaterialTheme.colorScheme.surface
            )
          )
        )
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Header tag
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.TouchApp,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Interactive Heart Garden 💕",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "Touch Any Heart to Reveal a Message",
        fontSize = 19.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center
      )

      Text(
        text = "Each animated heart carries a unique feeling from Yamaan's heart to $userName ❤️",
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Animated Heart Garden Grid
      // Row 1: 4 hearts
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
      ) {
        sweetHeartList.take(4).forEach { heartItem ->
          AnimatedFloatingHeartView(
            item = heartItem,
            isSelected = selectedHeart?.id == heartItem.id,
            onTap = {
              selectedHeart = heartItem
              touchedCount++
              onHeartTouched(heartItem)
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Row 2: next 4 hearts
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
      ) {
        sweetHeartList.drop(4).take(4).forEach { heartItem ->
          AnimatedFloatingHeartView(
            item = heartItem,
            isSelected = selectedHeart?.id == heartItem.id,
            onTap = {
              selectedHeart = heartItem
              touchedCount++
              onHeartTouched(heartItem)
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Revealed Message Card with glowing animation
      AnimatedVisibility(
        visible = selectedHeart != null,
        enter = fadeIn() + scaleIn(initialScale = 0.92f),
        exit = fadeOut() + scaleOut(targetScale = 0.92f)
      ) {
        selectedHeart?.let { heart ->
          RevealedMessageCard(
            heart = heart,
            userName = userName,
            onShareWhatsApp = {
              onShareToWhatsApp?.invoke(heart)
            }
          )
        }
      }
    }
  }
}

@Composable
fun AnimatedFloatingHeartView(
  item: SweetHeartItem,
  isSelected: Boolean,
  onTap: () -> Unit
) {
  var isTapped by remember { mutableStateOf(false) }

  // Continuous floating & pulsing infinite animation
  val infiniteTransition = rememberInfiniteTransition(label = "heart_anim_${item.id}")
  val floatOffset by infiniteTransition.animateFloat(
    initialValue = -6f,
    targetValue = 6f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = item.animDurationMs, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "float_${item.id}"
  )

  val rotationSway by infiniteTransition.animateFloat(
    initialValue = -5f,
    targetValue = 5f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = item.animDurationMs + 400, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "sway_${item.id}"
  )

  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.96f,
    targetValue = 1.05f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = (item.animDurationMs * 0.8f).toInt(), easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_${item.id}"
  )

  // Quick bounce animation when tapped
  val tapScale by animateFloatAsState(
    targetValue = if (isTapped) 1.25f else 1.0f,
    animationSpec = spring(dampingRatio = 0.45f, stiffness = 400f),
    finishedListener = { isTapped = false },
    label = "tap_scale"
  )

  val combinedScale = pulseScale * tapScale * (if (isSelected) 1.1f else 1.0f)

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .offset(y = floatOffset.dp)
      .rotate(rotationSway)
      .scale(combinedScale)
      .padding(4.dp)
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
      ) {
        isTapped = true
        onTap()
      }
      .testTag("heart_button_${item.id}")
  ) {
    Box(
      modifier = Modifier
        .size(item.heartSize)
        .shadow(
          elevation = if (isSelected) 8.dp else 4.dp,
          shape = CircleShape,
          spotColor = item.primaryColor.copy(alpha = 0.5f)
        )
        .clip(CircleShape)
        .background(
          Brush.radialGradient(
            colors = listOf(
              item.secondaryColor,
              item.primaryColor
            )
          )
        )
        .border(
          width = if (isSelected) 2.5.dp else 1.5.dp,
          color = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f),
          shape = CircleShape
        ),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Text(
          text = item.iconEmoji,
          fontSize = (item.heartSize.value * 0.35f).sp
        )
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = item.shortLabel,
          tint = Color.White.copy(alpha = 0.92f),
          modifier = Modifier.size(16.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = item.shortLabel,
      fontSize = 11.sp,
      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
      color = if (isSelected) item.primaryColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun RevealedMessageCard(
  heart: SweetHeartItem,
  userName: String,
  onShareWhatsApp: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp)),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    border = BorderStroke(2.dp, heart.primaryColor.copy(alpha = 0.5f))
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              heart.primaryColor.copy(alpha = 0.08f),
              Color.White
            )
          )
        )
        .padding(18.dp)
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = heart.iconEmoji,
            fontSize = 26.sp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = heart.message,
            fontSize = 19.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = heart.primaryColor,
            textAlign = TextAlign.Center
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "“${heart.subtext}”",
          fontSize = 14.sp,
          fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
          textAlign = TextAlign.Center,
          lineHeight = 21.sp,
          modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // WhatsApp Share button for this sweet compliment
        Button(
          onClick = onShareWhatsApp,
          shape = RoundedCornerShape(20.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF25D366) // WhatsApp green
          ),
          elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .testTag("share_compliment_whatsapp_${heart.id}")
        ) {
          Icon(
            imageVector = Icons.Default.Send,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Share this with Yamaan on WhatsApp 💬",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }
    }
  }
}
