package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.sin
import kotlin.random.Random

data class ParticleHeart(
  val id: Long,
  val initialXRatio: Float,
  val speed: Float,
  val size: Float,
  val color: Color,
  val swayFrequency: Float,
  val swayAmplitude: Float,
  val initialPhase: Float
)

data class BurstHeart(
  val id: Long,
  val startX: Float,
  val startY: Float,
  val vx: Float,
  val vy: Float,
  val size: Float,
  val color: Color,
  val creationTime: Long
)

private val quickCompliments = listOf(
  "You're gorgeous! ✨",
  "You're the most prettiest! 🌸",
  "Your smile lights up my whole world! ☀️💖",
  "You have the kindest, purest heart! 🥺💕",
  "You are the cutest human ever created! 🥰",
  "You're my forever and always! 💍❤️",
  "No one compares to you, Aqsa! 💫",
  "You're breathtakingly beautiful! 🌹"
)

@Composable
fun FloatingHeartsBackground(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  onTapHeart: ((String) -> Unit)? = null
) {
  val ambientHearts = remember {
    val colors = listOf(
      Color(0xFFE91E63),
      Color(0xFFFF4081),
      Color(0xFFFF80AB),
      Color(0xFFF48FB1),
      Color(0xFFFFCDD2),
      Color(0xFFFFD54F)
    )
    List(18) { index ->
      ParticleHeart(
        id = index.toLong(),
        initialXRatio = Random.nextFloat(),
        speed = 0.08f + Random.nextFloat() * 0.12f,
        size = 14f + Random.nextFloat() * 22f,
        color = colors[Random.nextInt(colors.size)].copy(alpha = 0.25f + Random.nextFloat() * 0.45f),
        swayFrequency = 1.5f + Random.nextFloat() * 2f,
        swayAmplitude = 15f + Random.nextFloat() * 30f,
        initialPhase = Random.nextFloat() * 6.28f
      )
    }
  }

  val burstHearts = remember { mutableStateListOf<BurstHeart>() }

  val transition = rememberInfiniteTransition(label = "hearts")
  val progress by transition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 8000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "progress"
  )

  Canvas(
    modifier = modifier
      .fillMaxSize()
      .pointerInput(Unit) {
        detectTapGestures { offset ->
          val msg = quickCompliments[Random.nextInt(quickCompliments.size)]
          onTapHeart?.invoke(msg)
          val colors = listOf(
            Color(0xFFE91E63),
            Color(0xFFFF4081),
            Color(0xFFFF80AB),
            Color(0xFFFFD54F)
          )
          val now = System.currentTimeMillis()
          for (i in 0 until 5) {
            val angle = Random.nextFloat() * 6.28f
            val speed = 60f + Random.nextFloat() * 120f
            burstHearts.add(
              BurstHeart(
                id = now + i,
                startX = offset.x,
                startY = offset.y,
                vx = kotlin.math.cos(angle.toDouble()).toFloat() * speed,
                vy = kotlin.math.sin(angle.toDouble()).toFloat() * speed - 60f,
                size = 18f + Random.nextFloat() * 16f,
                color = colors[Random.nextInt(colors.size)],
                creationTime = now
              )
            )
          }
          if (burstHearts.size > 40) {
            burstHearts.removeRange(0, 10)
          }
        }
      }
  ) {
    if (!enabled) return@Canvas

    val width = size.width
    val height = size.height

    // Draw ambient floating hearts
    for (heart in ambientHearts) {
      val yProg = (progress * heart.speed * 8f + (heart.id * 0.13f)) % 1f
      val y = height * (1f - yProg)
      val sway = sin((yProg * 6.28f * heart.swayFrequency) + heart.initialPhase) * heart.swayAmplitude
      val x = (heart.initialXRatio * width + sway).coerceIn(0f, width)

      drawHeart(
        center = Offset(x, y),
        size = heart.size,
        color = heart.color
      )
    }

    // Draw active burst hearts
    val currentTime = System.currentTimeMillis()
    val iterator = burstHearts.iterator()
    while (iterator.hasNext()) {
      val b = iterator.next()
      val ageMs = currentTime - b.creationTime
      if (ageMs > 2500) {
        iterator.remove()
        continue
      }
      val t = ageMs / 1000f
      val alpha = (1f - (ageMs / 2500f)).coerceIn(0f, 1f)
      val x = b.startX + b.vx * t
      val y = b.startY + b.vy * t + 0.5f * 80f * t * t // slight upward drift/gravity
      drawHeart(
        center = Offset(x, y),
        size = b.size,
        color = b.color.copy(alpha = alpha)
      )
    }
  }
}

fun DrawScope.drawHeart(center: Offset, size: Float, color: Color) {
  val path = Path().apply {
    val w = size
    val h = size
    val left = center.x - w / 2f
    val top = center.y - h / 2f

    moveTo(left + w / 2f, top + h * 0.35f)
    cubicTo(
      left + w * 0.1f, top,
      left, top + h * 0.45f,
      left + w / 2f, top + h
    )
    cubicTo(
      left + w, top + h * 0.45f,
      left + w * 0.9f, top,
      left + w / 2f, top + h * 0.35f
    )
    close()
  }
  drawPath(path = path, color = color)
}
