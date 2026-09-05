# Yamaan Loves Aqsa 🤍

A heartfelt, romantic, and elegant apology and appreciation website from Yamaan to Aqsa.

Designed with an ivory/white aesthetic, blush pink and soft lavender accents, glassmorphism cards, continuously floating interactive hearts, and mobile-first responsiveness.

Intended Custom Domain: **`YamaanloveAqsa.com`**

---

## 🌟 Features

- **Cinematic Hero Section**: Warm, welcoming introduction with an interactive, XSS-safe name personalization form.
- **Continuous Floating Hearts**: Gentle floating hearts drifting across the screen, touch/click-enabled with sparkle bursts and 28 unique non-repeating compliments.
- **Special Message**: Exact heartfelt dedication letter with elegant typography and custom signature.
- **Appreciation Section**: Comprehensive tribute celebrating personality, kindness, smile, voice, individuality, quirks, and emotional warmth.
- **Sincere Apology Section**: An honest, accountable, and non-manipulative apology prioritizing respect and her peace of mind.
- **WhatsApp Hug Option**: Pre-fills `🫂 ❤️` for direct sending to Yamaan on WhatsApp (configured for `+91 93364 35690`).
- **Closing & Final Thought**: Poetic closing with a gentle pulsing heart.
- **Accessibility & Motion**: Fully responsive (320px to 1920px), zero dependencies, ARIA landmarks, and `@media (prefers-reduced-motion: reduce)` support.

---

## 🚀 1. How to Run Locally

Because this project is built with vanilla HTML5, CSS3, and JavaScript, **no build tools, Node.js, or npm are needed**.

1. Download or clone this repository to your computer.
2. Locate the file named `index.html`.
3. Double-click `index.html` to open it directly in Google Chrome, Safari, Firefox, or Edge.
4. *(Optional)* If using VS Code, you can right-click `index.html` and choose **"Open with Live Server"**.

---

## 📦 2. How to Upload to GitHub

1. Go to [GitHub.com](https://github.com) and log in.
2. Click **New Repository**.
3. Name your repository (for example: `yamaanloveaqsa` or `YamaanloveAqsa`).
4. Set it to **Public** (required for free GitHub Pages).
5. Do not check "Add a README" if you are pushing these files from your local computer.
6. Open your terminal in the project directory and run:

```bash
git init
git add .
git commit -m "Initial release of YamaanloveAqsa website 🤍"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
git push -u origin main
```

*(Alternatively, you can drag and drop `index.html`, `style.css`, `script.js`, `CNAME`, and `README.md` directly into GitHub’s web interface via "Upload files".)*

---

## 🌐 3. How to Enable GitHub Pages

1. In your GitHub repository, click **Settings** (gear icon near the top).
2. On the left sidebar, select **Pages** (under the "Code and automation" section).
3. Under **Build and deployment**:
   - **Source**: Select `Deploy from a branch`.
   - **Branch**: Select `main` (or `master`) and folder `/ (root)`.
4. Click **Save**.
5. After 1–2 minutes, your website will be live at:
   `https://YOUR_USERNAME.github.io/YOUR_REPOSITORY/`

---

## 💬 4. How to Configure the WhatsApp Number

In `script.js`, the WhatsApp number is already configured with Yamaan's phone number:

```javascript
const YAMAAN_WHATSAPP_NUMBER = "919336435690";
```

When the visitor clicks **"Send hugs to Yamaan 🫂"**, WhatsApp will automatically open with `🫂 ❤️` pre-filled.

---

## 🔗 5. How to Connect `YamaanloveAqsa.com` (Custom Domain)

This repository includes a `CNAME` file containing the single registered domain:
```
YamaanloveAqsa.com
```

### Steps on GitHub:
1. Go to repository **Settings** → **Pages**.
2. Under **Custom domain**, verify `YamaanloveAqsa.com` is entered.
3. Click **Save**.
4. Check **Enforce HTTPS** (GitHub will provision a free SSL certificate once DNS resolves).

> **Note:** The domain `YamaanloveAqsa.com` is registered through your domain registrar, and its DNS records point to GitHub.

---

## 🛠️ 6. DNS Configuration Basics

In your domain registrar’s DNS management dashboard (e.g., Namecheap or Cloudflare), configure these DNS records for `YamaanloveAqsa.com`:

### A. Apex Domain (Root: `@` or `YamaanloveAqsa.com`)
Add 4 **A Records** pointing to GitHub's official IP addresses:
- `185.199.108.153`
- `185.199.109.153`
- `185.199.110.153`
- `185.199.111.153`

### B. Subdomain (`www.YamaanloveAqsa.com`)
Add 1 **CNAME Record**:
- **Host / Name**: `www`
- **Value / Target**: `YOUR_USERNAME.github.io.` (replace with your GitHub username, with a trailing dot if required by your registrar).

DNS changes typically propagate within 15 minutes to a few hours.

---

## 🌸 7. How to Customize Compliments

In `script.js`, locate the `COMPLIMENTS` array. You can edit existing thoughts or add new ones:

```javascript
const COMPLIMENTS = [
  "{name}, you make ordinary moments feel special. 🤍",
  "{name}, there is something genuinely beautiful about the way you are.",
  // Add your own custom compliments here!
];
```
The `{name}` placeholder will automatically be replaced with whichever name is entered on the homepage (defaults to "Aqsa").

---

## ✍️ 8. How to Customize Website Text

All text content is cleanly organized in `index.html`:
- **Special Message**: Inside `<section id="for-you">`
- **Appreciation Cards**: Inside `<section id="appreciation">`
- **Apology Letter**: Inside `<section id="apology">`
- **Closing Words**: Inside `<section id="closing">`

Simply edit the text in `index.html` and save!
