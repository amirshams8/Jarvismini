# Jarvis Mini - Fixed Version

## Project Info
- **WhatsApp Target Version**: 2.25.35.79
- **Single Module Architecture**
- **View Binding Enabled**
- **Android Gradle Plugin**: 8.3.1
- **Kotlin**: 1.9.0

## Features
✅ WhatsApp Auto Reply via Accessibility Service
✅ 8-second cooldown to prevent spam
✅ Focused input field handling
✅ Safe node finding with fallbacks
✅ View Binding for UI
✅ Minimal dependencies

## Build Instructions
1. Open in Android Studio
2. Sync Gradle
3. Build APK
4. Install on device
5. Enable Accessibility Service in Settings

## Usage
1. Launch app
2. Tap "Enable Accessibility Service"
3. Enable "Jarvis Mini" in Accessibility Settings
4. Open WhatsApp
5. Jarvis will auto-reply to messages

## Key Implementation Details

### AutomationEngine
- Handles accessibility events
- 8-second cooldown between replies
- Focuses input field before setting text
- Logs successful replies

### NodeFinder
- Finds WhatsApp input field (com.whatsapp:id/entry)
- Finds WhatsApp send button (com.whatsapp:id/send)
- Fallback to EditText and ImageButton by class
- Safe error handling

### SmartAutoReply
- Simple reply generation (ready for LLM integration)
- Replace `generateReply()` with your AI model

## Customization
- Edit `SmartAutoReply.generateReply()` to integrate LLM
- Adjust `COOLDOWN_MS` in AutomationEngine
- Add more input/send button IDs in NodeFinder

## Tested On
- WhatsApp 2.25.35.79
- Android 6.0+ (API 23+)
