package com.example.ui

import android.content.Intent
import android.net.Uri
import android.view.SoundEffectConstants
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.DomainBar
import com.example.ui.components.DomainTld
import com.example.ui.components.FloatingHeartsBackground
import com.example.ui.components.ForgivenessSection
import com.example.ui.components.HugeSorrySection
import com.example.ui.components.InteractiveTouchHeartsSection
import com.example.ui.components.LoveReasonsSection
import com.example.ui.components.SweetHeartItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoveApologyScreen(modifier: Modifier = Modifier) {
  var enteredName by remember { mutableStateOf("") }
  var confirmedName by remember { mutableStateOf("") }
  var isForgiven by remember { mutableStateOf(false) }
  var activeHeartMessage by remember { mutableStateOf<String?>(null) }

  val context = LocalContext.current
  val snackbarHostState = remember { SnackbarHostState() }
  val view = LocalView.current
  val coroutineScope = rememberCoroutineScope()
  val focusManager = LocalFocusManager.current
  val haptic = LocalHapticFeedback.current
  val clipboardManager = LocalClipboardManager.current
  val scrollState = rememberScrollState()

  fun playRomanticChime() {
    try {
      view.playSoundEffect(SoundEffectConstants.CLICK)
    } catch (_: Exception) {
      // Graceful fallback
    }
  }

  fun onHeartTouched(message: String) {
    activeHeartMessage = message
    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
    playRomanticChime()
    coroutineScope.launch {
      delay(4500)
      if (activeHeartMessage == message) {
        activeHeartMessage = null
      }
    }
  }

  fun sendWhatsAppCompliment(heart: SweetHeartItem) {
    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    playRomanticChime()
    val phoneNumber = "919336435690" // +91 93364 35690
    val sender = if (confirmedName.isNotBlank()) confirmedName else "Aqsa"
    val message = "Hey Yamaan! ❤️ I just touched your heart on YamaanloveAqsa.com: \"${heart.message}\" - ${heart.subtext} 🥰💕"
    try {
      val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }
      context.startActivity(intent)
      coroutineScope.launch {
        snackbarHostState.showSnackbar("Opening WhatsApp for Yamaan... 💬❤️")
      }
    } catch (_: Exception) {
      clipboardManager.setText(AnnotatedString(message))
      coroutineScope.launch {
        snackbarHostState.showSnackbar("Sweet message copied! Send to +91 93364 35690 💕")
      }
    }
  }

  fun sendWhatsAppHug() {
    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    playRomanticChime()
    val phoneNumber = "919336435690" // +91 93364 35690
    val sender = if (confirmedName.isNotBlank()) confirmedName else "Aqsa"
    val message = "Hey Yamaan! ❤️ I received your apology on YamaanloveAqsa.com. Sending you the biggest, warmest hug right now! 🫂🥰💕"
    try {
      val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }
      context.startActivity(intent)
      coroutineScope.launch {
        snackbarHostState.showSnackbar("Opening WhatsApp to send Yamaan your hug... 🫂💬❤️")
      }
    } catch (_: Exception) {
      // Fallback if WhatsApp is not installed or intent cannot resolve
      clipboardManager.setText(AnnotatedString(message))
      coroutineScope.launch {
        snackbarHostState.showSnackbar("Message copied! WhatsApp number: +91 93364 35690 🫂❤️")
      }
    }
  }

  fun submitName(name: String) {
    val clean = name.trim()
    if (clean.isNotEmpty()) {
      confirmedName = clean
      focusManager.clearFocus()
      haptic.performHapticFeedback(HapticFeedbackType.LongPress)
      playRomanticChime()
      coroutineScope.launch {
        snackbarHostState.showSnackbar("💌 Unlocked heartfelt message for $clean!")
      }
    } else {
      coroutineScope.launch {
        snackbarHostState.showSnackbar("Please enter her name to unlock the message 💕")
      }
    }
  }

  Scaffold(
    modifier = modifier.fillMaxSize(),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      // Ambient floating hearts background with tap-to-burst interaction
      FloatingHeartsBackground(
        onTapHeart = { message ->
          onHeartTouched(message)
        }
      )

      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .statusBarsPadding()
          .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // 1. Web Address Bar (Single Registered Domain: YamaanloveAqsa.com)
        DomainBar(
          onShareClick = {
            clipboardManager.setText(AnnotatedString("https://YamaanloveAqsa.com"))
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            coroutineScope.launch {
              snackbarHostState.showSnackbar("Copied YamaanloveAqsa.com to clipboard with love! 💌")
            }
          }
        )

        // 2. Romantic Hero Banner Card
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(180.dp)
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_love_banner),
              contentDescription = "Romantic Roses and Floating Hearts",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )

            // Gradient overlay for readability
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    colors = listOf(
                      Color.Transparent,
                      Color.Black.copy(alpha = 0.65f)
                    )
                  )
                )
            )

            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = MaterialTheme.colorScheme.primary
                ) {
                  Text(
                    text = "Yamaan ❤️ Aqsa",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = "Forever & Always in Love",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 3. Name Input Card (The Key to unlock the huge sorry message!)
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(24.dp)),
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
          )
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .background(
                Brush.verticalGradient(
                  colors = listOf(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                  )
                )
              )
              .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.LockOpen,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Unlock Yamaan's Love Message",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Please enter her name to reveal the special apology message waiting inside:",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
              value = enteredName,
              onValueChange = { enteredName = it },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("name_input_field"),
              placeholder = { Text("Enter her name (e.g., Aqsa)") },
              leadingIcon = {
                Icon(
                  imageVector = Icons.Default.Favorite,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary
                )
              },
              trailingIcon = {
                if (enteredName.isNotEmpty()) {
                  IconButton(onClick = { enteredName = "" }) {
                    Icon(
                      imageVector = Icons.Default.Clear,
                      contentDescription = "Clear name input",
                      tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                  }
                }
              },
              singleLine = true,
              shape = RoundedCornerShape(16.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                cursorColor = MaterialTheme.colorScheme.primary
              ),
              keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
              ),
              keyboardActions = KeyboardActions(
                onDone = { submitName(enteredName) }
              )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Quick shortcut pill: "It's me, Aqsa 💕"
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Quick shortcut:",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
              )

              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(16.dp))
                  .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                  .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(16.dp)
                  )
                  .clickable {
                    enteredName = "Aqsa"
                    submitName("Aqsa")
                  }
                  .padding(horizontal = 12.dp, vertical = 6.dp)
                  .testTag("quick_aqsa_chip")
              ) {
                Text(
                  text = "It's me, Aqsa ❤️",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Submit / Unlock Button
            Button(
              onClick = { submitName(enteredName) },
              modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("reveal_sorry_button"),
              shape = RoundedCornerShape(25.dp),
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
                text = if (confirmedName.isEmpty()) "Open Apology Message 💌" else "Update Name & Replay 💌",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4. Interactive Animated Hearts Section with different messages on touch
        InteractiveTouchHeartsSection(
          userName = if (confirmedName.isNotBlank()) confirmedName else "Aqsa",
          onHeartTouched = { heartItem ->
            onHeartTouched(heartItem.message)
          },
          onShareToWhatsApp = { heartItem ->
            sendWhatsAppCompliment(heartItem)
          }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 5. THE HUGE SORRY MESSAGE SECTION (Triggered when name is submitted)
        AnimatedVisibility(
          visible = confirmedName.isNotEmpty(),
          enter = fadeIn(tween(700)),
          exit = fadeOut(tween(300))
        ) {
          Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            HugeSorrySection(
              userName = confirmedName,
              onSendHug = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                playRomanticChime()
                coroutineScope.launch {
                  snackbarHostState.showSnackbar("Warm hug sent to Yamaan! His heart skipped a beat! 🫂❤️")
                }
              },
              onSendWhatsAppHug = {
                sendWhatsAppHug()
              }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 5. Interactive Forgiveness Experience
            ForgivenessSection(
              userName = confirmedName,
              isForgiven = isForgiven,
              onForgiveClick = {
                isForgiven = true
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                playRomanticChime()
                coroutineScope.launch {
                  snackbarHostState.showSnackbar("🎉 $confirmedName has forgiven Yamaan! Love wins!")
                }
              },
              onSendWhatsAppHug = {
                sendWhatsAppHug()
              }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 6. Reasons Why Yamaan Loves Her
            LoveReasonsSection(userName = confirmedName)
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer note
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "YamaanloveAqsa.com",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
            fontFamily = FontFamily.Monospace
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Crafted with endless love & devotion by Yamaan for Aqsa ❤️",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            textAlign = TextAlign.Center
          )
        }

        Spacer(modifier = Modifier.height(24.dp))
      }

      // Floating Top Toast Banner when any heart is touched
      AnimatedVisibility(
        visible = activeHeartMessage != null,
        enter = fadeIn(tween(300)) + slideInVertically(initialOffsetY = { -it }),
        exit = fadeOut(tween(300)) + slideOutVertically(targetOffsetY = { -it }),
        modifier = Modifier
          .align(Alignment.TopCenter)
          .statusBarsPadding()
          .padding(top = 10.dp, start = 16.dp, end = 16.dp)
      ) {
        activeHeartMessage?.let { msg ->
          Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFE91E63),
            shadowElevation = 8.dp,
            modifier = Modifier
              .clip(RoundedCornerShape(24.dp))
              .clickable { activeHeartMessage = null }
              .testTag("floating_heart_toast")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("💖", fontSize = 18.sp)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = msg,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(8.dp))
              IconButton(
                onClick = { activeHeartMessage = null },
                modifier = Modifier.size(20.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Close",
                  tint = Color.White.copy(alpha = 0.85f),
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}
