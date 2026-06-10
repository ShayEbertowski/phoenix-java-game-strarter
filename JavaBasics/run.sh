#!/usr/bin/env bash
set -e

APP_DIR="$(cd "$(dirname "$0")" && pwd -P)"
SRC_DIR="$APP_DIR/src"
PID_FILE="$APP_DIR/.javabasics.pid"

echo "Stopping previous JavaBasics app if running..."

if [ -f "$PID_FILE" ]; then
    OLD_PID="$(cat "$PID_FILE")"
    OLD_COMMAND="$(ps -p "$OLD_PID" -o command= 2>/dev/null || true)"
    OLD_FOLDER=""

    if command -v lsof >/dev/null 2>&1; then
        OLD_FOLDER="$(lsof -a -p "$OLD_PID" -d cwd -Fn 2>/dev/null | sed 's/^n//' || true)"
    elif [ -e "/proc/$OLD_PID/cwd" ]; then
        OLD_FOLDER="$(readlink "/proc/$OLD_PID/cwd" 2>/dev/null || true)"
    fi

    if echo "$OLD_COMMAND" | grep -q "java Main" && [ "$OLD_FOLDER" = "$SRC_DIR" ]; then
        kill "$OLD_PID"
    fi

    rm -f "$PID_FILE"
fi

cd -P "$SRC_DIR"

echo "Compiling JavaBasics..."
javac Main.java

echo "Running JavaBasics..."
java Main &
APP_PID=$!
echo "$APP_PID" > "$PID_FILE"

wait "$APP_PID"
rm -f "$PID_FILE"
