#!/bin/bash
# Run the web app and expose it to the internet via ngrok
# Install ngrok: brew install ngrok  (or download from ngrok.com)

set -e
cd "$(dirname "$0")"

echo "→ Extracting problem data..."
python3 scripts/extract_problems.py 2>/dev/null || true

echo "→ Starting web server in background..."
mvn spring-boot:run &
SERVER_PID=$!

echo "→ Waiting for server to start..."
sleep 15

echo "→ Starting ngrok tunnel..."
if command -v ngrok &>/dev/null; then
  ngrok http 8080
else
  echo "  ngrok not found. Install: brew install ngrok"
  echo "  Or use: npx localtunnel --port 8080"
  wait $SERVER_PID
fi
