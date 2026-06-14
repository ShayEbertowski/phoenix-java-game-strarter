#!/usr/bin/env bash
set -e

cd "$(dirname "$0")/src"

echo "Compiling TileGame..."
javac Main.java

echo "Running TileGame..."
java Main
