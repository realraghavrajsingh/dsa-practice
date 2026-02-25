#!/bin/bash
# Run the LeetCode web app locally
# Open http://localhost:8080 in your browser

set -e
cd "$(dirname "$0")"

echo "→ Extracting problem data..."
python3 scripts/extract_problems.py 2>/dev/null || echo "  (Skip if Python unavailable - use existing problems.json)"

echo "→ Starting web server..."
mvn spring-boot:run
