/**
 * YAMAAN LOVES AQSA 🤍
 * Frontend Interactive Logic & Animations
 * Registered Domain: YamaanloveAqsa.com
 *
 * Zero external dependencies. Production-ready Vanilla JavaScript.
 */

// ==========================================================================
// 1. CONFIGURATION
// ==========================================================================

/**
 * YAMAAN'S WHATSAPP NUMBER
 * Format: Country code, NO "+", NO spaces, NO hyphens.
 * Configured for Yamaan: 919336435690 (+91 93364 35690)
 */
const YAMAAN_WHATSAPP_NUMBER = "919336435690";

// Pre-encoded WhatsApp hug message: 🫂 ❤️
const WHATSAPP_HUG_TEXT = encodeURIComponent("🫂 ❤️");

// Default recipient name if none is explicitly entered
const DEFAULT_RECIPIENT = "Aqsa";

// State
let currentRecipient = DEFAULT_RECIPIENT;
let lastComplimentIndex = -1;

// ==========================================================================
// 2. COMPLIMENTS DATA (28 Unique Heartfelt Thoughts)
// ==========================================================================
const COMPLIMENTS = [
  "{name}, you make ordinary moments feel special. 🤍",
  "{name}, there is something genuinely beautiful about the way you are.",
  "{name}, your presence makes memories feel warmer.",
  "{name}, even your smallest little things are worth appreciating.",
  "{name}, you are more precious than you probably realize.",
  "{name}, your smile has a gentle way of brightening even the heaviest days.",
  "{name}, the kindness in your heart is something truly rare in this world.",
  "{name}, your voice brings a sense of calm that words can hardly describe.",
  "{name}, you have a warmth that makes everyone around you feel safe and heard.",
  "{name}, the little expressions you make when you're happy are completely unforgettable.",
  "{name}, you carry a quiet grace that is breathtaking in every single way.",
  "{name}, your laugh is one of the sweetest sounds I could ever listen to.",
  "{name}, the thoughtfulness you put into the people you care about never goes unnoticed.",
  "{name}, there is an honesty and purity in your spirit that I admire endlessly.",
  "{name}, you are stronger and more resilient than you ever give yourself credit for.",
  "{name}, having you in my life has taught me how meaningful a genuine bond can be.",
  "{name}, you don't have to try to be special—you just are, naturally.",
  "{name}, even when you are silent, your presence speaks volumes of comfort.",
  "{name}, you deserve every happiness, peace of mind, and gentleness this life can offer.",
  "{name}, the way your eyes light up when something genuinely delights you is magical.",
  "{name}, your authenticity is a breath of fresh air in a world full of pretenses.",
  "{name}, you make the world feel softer and more forgiving just by being in it.",
  "{name}, you are deeply valued, deeply respected, and cherished for who you are.",
  "{name}, I appreciate the patience and understanding you've shown through every conversation.",
  "{name}, you are irreplaceable—there is truly only one of you in this entire universe.",
  "{name}, your gentle soul leaves a lasting impression that time cannot fade.",
  "{name}, every small memory with you is tucked away somewhere sacred in my heart.",
  "{name}, thank you for being the wonderful, radiant, kind human being that you are."
];

// ==========================================================================
// 3. DOM ELEMENT REFERENCES
// ==========================================================================
const nameForm = document.getElementById("name-form");
const nameInput = document.getElementById("name-input");
const nameFeedback = document.getElementById("name-feedback");
const recipientNameElements = document.querySelectorAll(".recipient-name");
const nameChips = document.querySelectorAll(".name-chip");
const floatingHeartsLayer = document.getElementById("floating-hearts-layer");

// Modal Elements
const complimentModal = document.getElementById("compliment-modal");
const complimentBackdrop = document.getElementById("compliment-backdrop");
const complimentClose = document.getElementById("compliment-close");
const complimentText = document.getElementById("compliment-text");

// Navigation
const navbar = document.getElementById("navbar");
const navToggle = document.getElementById("nav-toggle");
const navMenu = document.getElementById("nav-menu");
const navLinks = document.querySelectorAll(".nav-link");

// WhatsApp Hug
const whatsappHugBtn = document.getElementById("whatsapp-hug-btn");
const whatsappNotice = document.getElementById("whatsapp-notice");
const whatsappNoticeText = document.getElementById("whatsapp-notice-text");

// Reduced Motion Preference
const prefersReducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;

// ==========================================================================
// 4. NAME PERSONALIZATION (XSS-Safe using textContent)
// ==========================================================================

/**
 * Updates all name displays safely throughout the document.
 * @param {string} rawName 
 */
function updateRecipientName(rawName) {
  const cleanName = (rawName || "").trim();
  currentRecipient = cleanName.length > 0 ? cleanName : DEFAULT_RECIPIENT;

  // Safely update all DOM recipient labels using textContent
  recipientNameElements.forEach((el) => {
    el.textContent = currentRecipient;
  });
}

/**
 * Handles the name submission form.
 * @param {Event} e 
 */
function handleNameSubmit(e) {
  if (e) e.preventDefault();

  const entered = nameInput.value.trim();

  if (!entered) {
    nameFeedback.textContent = "Please enter a name first 🤍";
    nameFeedback.style.color = "var(--rose-deep)";
    nameInput.focus();
    return;
  }

  // Update application name
  updateRecipientName(entered);
  nameFeedback.textContent = `Personalized for ${currentRecipient} 🤍`;
  nameFeedback.style.color = "var(--rose-primary)";

  // Smooth scroll to dedicated letter
  const targetSection = document.getElementById("for-you");
  if (targetSection) {
    targetSection.scrollIntoView({ behavior: prefersReducedMotion ? "auto" : "smooth" });
  }
}

// Attach Form Event Listeners
if (nameForm) {
  nameForm.addEventListener("submit", handleNameSubmit);
}

// Quick selection chips
nameChips.forEach((chip) => {
  chip.addEventListener("click", () => {
    const chipName = chip.getAttribute("data-name");
    if (chipName) {
      nameInput.value = chipName;
      updateRecipientName(chipName);
      nameFeedback.textContent = `Personalized for ${currentRecipient} 🤍`;
      nameFeedback.style.color = "var(--rose-primary)";

      const targetSection = document.getElementById("for-you");
      if (targetSection) {
        targetSection.scrollIntoView({ behavior: prefersReducedMotion ? "auto" : "smooth" });
      }
    }
  });
});

// ==========================================================================
// 5. COMPLIMENT RANDOMIZATION & DISPLAY
// ==========================================================================

/**
 * Retrieves a random compliment ensuring no two consecutive duplicates.
 * @returns {string} Personalized compliment string
 */
function getRandomCompliment() {
  let newIndex;
  do {
    newIndex = Math.floor(Math.random() * COMPLIMENTS.length);
  } while (newIndex === lastComplimentIndex && COMPLIMENTS.length > 1);

  lastComplimentIndex = newIndex;
  const template = COMPLIMENTS[newIndex];
  return template.replace(/\{name\}/g, currentRecipient);
}

/**
 * Opens the compliment popup with a personalized message.
 */
function showCompliment() {
  const message = getRandomCompliment();
  complimentText.textContent = message;

  complimentModal.classList.remove("hidden");
  complimentClose.focus();
}

/**
 * Closes the compliment popup.
 */
function closeCompliment() {
  complimentModal.classList.add("hidden");
}

if (complimentClose) {
  complimentClose.addEventListener("click", closeCompliment);
}

if (complimentBackdrop) {
  complimentBackdrop.addEventListener("click", closeCompliment);
}

// Close on Escape key press
document.addEventListener("keydown", (e) => {
  if (e.key === "Escape" && !complimentModal.classList.contains("hidden")) {
    closeCompliment();
  }
});

// ==========================================================================
// 6. CONTINUOUS FLOATING HEARTS & INTERACTION
// ==========================================================================

// Color palette for floating SVG hearts
const HEART_COLORS = [
  "rgba(224, 122, 134, 0.75)", // blush accent
  "rgba(184, 91, 107, 0.7)",   // rose primary
  "rgba(191, 160, 223, 0.65)", // lavender accent
  "rgba(242, 187, 196, 0.8)",  // soft pink
  "rgba(226, 188, 122, 0.7)"   // warm gold
];

/**
 * Creates a heart particle burst animation at specified screen coordinates.
 * @param {number} x Screen X
 * @param {number} y Screen Y
 */
function createHeartBurst(x, y) {
  if (prefersReducedMotion) return;

  const count = 7;
  const emojis = ["🤍", "✨", "💕", "🌸"];

  for (let i = 0; i < count; i++) {
    const particle = document.createElement("span");
    particle.className = "burst-particle";
    particle.textContent = emojis[Math.floor(Math.random() * emojis.length)];

    const angle = (Math.PI * 2 * i) / count + (Math.random() * 0.4 - 0.2);
    const distance = 40 + Math.random() * 55;
    const tx = Math.cos(angle) * distance;
    const ty = Math.sin(angle) * distance;
    const rot = (Math.random() * 60 - 30) + "deg";

    particle.style.left = `${x}px`;
    particle.style.top = `${y}px`;
    particle.style.setProperty("--tx", `${tx}px`);
    particle.style.setProperty("--ty", `${ty}px`);
    particle.style.setProperty("--rot", rot);

    document.body.appendChild(particle);

    setTimeout(() => {
      if (particle.parentNode) {
        particle.parentNode.removeChild(particle);
      }
    }, 900);
  }
}

/**
 * Manages an individual floating heart on the screen.
 */
class FloatingHeart {
  constructor(container, id) {
    this.container = container;
    this.id = id;
    this.element = null;
    this.isAlive = true;
    this.init();
  }

  init() {
    this.size = 18 + Math.random() * 18; // 18px to 36px
    this.x = Math.random() * (window.innerWidth - 60) + 30;
    this.y = window.innerHeight + Math.random() * 200; // Start below fold
    this.speed = 0.65 + Math.random() * 0.9;
    this.driftSpeed = 0.0015 + Math.random() * 0.002;
    this.driftAmplitude = 18 + Math.random() * 28;
    this.phase = Math.random() * Math.PI * 2;
    this.color = HEART_COLORS[Math.floor(Math.random() * HEART_COLORS.length)];
    this.opacity = 0.35 + Math.random() * 0.45;

    this.createElement();
  }

  createElement() {
    this.element = document.createElement("button");
    this.element.className = "floating-heart-btn";
    this.element.setAttribute("aria-label", "Tap to reveal a heartfelt compliment");
    this.element.style.width = `${this.size + 16}px`;
    this.element.style.height = `${this.size + 16}px`;

    // SVG Heart
    this.element.innerHTML = `
      <svg width="${this.size}" height="${this.size}" viewBox="0 0 24 24" fill="${this.color}" aria-hidden="true">
        <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
      </svg>
    `;

    // Click handler for this heart
    this.element.addEventListener("click", (e) => {
      e.stopPropagation();
      const rect = this.element.getBoundingClientRect();
      const clickX = rect.left + rect.width / 2;
      const clickY = rect.top + rect.height / 2;

      // 1. Create subtle burst
      createHeartBurst(clickX, clickY);

      // 2. Remove heart and respawn at bottom
      this.destroy();

      // 3. Display compliment modal
      showCompliment();
    });

    this.container.appendChild(this.element);
    this.updatePosition();
  }

  updatePosition() {
    if (!this.element || !this.isAlive) return;

    // Upward motion with gentle horizontal sinusoidal drift
    this.y -= this.speed;
    const currentDrift = Math.sin(Date.now() * this.driftSpeed + this.phase) * this.driftAmplitude;
    const currentX = this.x + currentDrift;

    this.element.style.left = `${currentX}px`;
    this.element.style.top = `${this.y}px`;
    this.element.style.opacity = this.opacity;

    // Reset when off the top of screen
    if (this.y < -50) {
      this.recycle();
    }
  }

  recycle() {
    this.x = Math.random() * (window.innerWidth - 60) + 30;
    this.y = window.innerHeight + 20 + Math.random() * 80;
    this.speed = 0.65 + Math.random() * 0.9;
    this.phase = Math.random() * Math.PI * 2;
  }

  destroy() {
    this.isAlive = false;
    if (this.element && this.element.parentNode) {
      this.element.parentNode.removeChild(this.element);
    }
    // Respawn after short delay
    setTimeout(() => {
      if (document.body.contains(this.container)) {
        this.isAlive = true;
        this.init();
      }
    }, 1200 + Math.random() * 1500);
  }
}

/**
 * Initializes and loops the floating hearts background system.
 */
function initFloatingHearts() {
  if (!floatingHeartsLayer) return;

  // Optimize heart count based on screen width for 60fps performance
  const isMobile = window.innerWidth <= 768;
  const heartCount = isMobile ? 12 : 18;
  const hearts = [];

  for (let i = 0; i < heartCount; i++) {
    const heart = new FloatingHeart(floatingHeartsLayer, i);
    // Stagger initial Y positions across the whole height
    heart.y = Math.random() * window.innerHeight;
    hearts.push(heart);
  }

  // Animation Loop using requestAnimationFrame
  function loop() {
    if (!prefersReducedMotion) {
      hearts.forEach((heart) => heart.updatePosition());
    }
    requestAnimationFrame(loop);
  }

  requestAnimationFrame(loop);
}

// ==========================================================================
// 7. SEND A HUG — WHATSAPP INTEGRATION
// ==========================================================================

/**
 * Validates and handles the "Send Hug" WhatsApp button click.
 */
function handleWhatsAppHug() {
  const isConfigured = 
    YAMAAN_WHATSAPP_NUMBER && 
    YAMAAN_WHATSAPP_NUMBER !== "REPLACE_WITH_NUMBER" && 
    /^\d{7,15}$/.test(YAMAAN_WHATSAPP_NUMBER);

  if (isConfigured) {
    const targetUrl = `https://wa.me/${YAMAAN_WHATSAPP_NUMBER}?text=${WHATSAPP_HUG_TEXT}`;
    window.open(targetUrl, "_blank", "noopener,noreferrer");
  } else {
    // Graceful fallback and clear instructions when number needs setup
    whatsappNotice.classList.remove("hidden");
    whatsappNoticeText.innerHTML = `
      <strong>WhatsApp Setup Required:</strong> To send hugs directly, please configure <code>YAMAAN_WHATSAPP_NUMBER</code> in <code>script.js</code> (e.g. <code>919336435690</code>).<br />
      <em>Your hug message <strong>"🫂 ❤️"</strong> has been copied to your clipboard in the meantime!</em>
    `;

    // Attempt copying hug to clipboard
    if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText("🫂 ❤️").catch(() => {});
    }
  }
}

if (whatsappHugBtn) {
  whatsappHugBtn.addEventListener("click", handleWhatsAppHug);
}

// ==========================================================================
// 8. SCROLL REVEAL (IntersectionObserver)
// ==========================================================================
function initScrollReveal() {
  const revealElements = document.querySelectorAll(".reveal-fade, .reveal-up");

  if ("IntersectionObserver" in window && !prefersReducedMotion) {
    const observer = new IntersectionObserver(
      (entries, obs) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add("is-revealed");
            obs.unobserve(entry.target);
          }
        });
      },
      {
        root: null,
        rootMargin: "0px 0px -40px 0px",
        threshold: 0.12
      }
    );

    revealElements.forEach((el) => observer.observe(el));
  } else {
    // Fallback: reveal immediately
    revealElements.forEach((el) => el.classList.add("is-revealed"));
  }
}

// ==========================================================================
// 9. RESPONSIVE NAVIGATION & SCROLL TRACKING
// ==========================================================================
function initNavigation() {
  // Mobile Nav Toggle
  if (navToggle && navMenu) {
    navToggle.addEventListener("click", () => {
      const isExpanded = navToggle.getAttribute("aria-expanded") === "true";
      navToggle.setAttribute("aria-expanded", String(!isExpanded));
      navMenu.classList.toggle("nav-open");
    });

    // Close menu when a navigation link is clicked
    navLinks.forEach((link) => {
      link.addEventListener("click", () => {
        navToggle.setAttribute("aria-expanded", "false");
        navMenu.classList.remove("nav-open");
      });
    });

    // Close menu when clicking outside
    document.addEventListener("click", (e) => {
      if (
        navMenu.classList.contains("nav-open") &&
        !navMenu.contains(e.target) &&
        !navToggle.contains(e.target)
      ) {
        navToggle.setAttribute("aria-expanded", "false");
        navMenu.classList.remove("nav-open");
      }
    });
  }

  // Header background elevation on scroll
  window.addEventListener("scroll", () => {
    if (window.scrollY > 20) {
      navbar.classList.add("scrolled");
    } else {
      navbar.classList.remove("scrolled");
    }
  }, { passive: true });
}

// ==========================================================================
// 10. INITIALIZATION
// ==========================================================================
document.addEventListener("DOMContentLoaded", () => {
  updateRecipientName(DEFAULT_RECIPIENT);
  initFloatingHearts();
  initScrollReveal();
  initNavigation();
});
