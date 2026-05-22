#!/usr/bin/env bash
set -e

cd "$(dirname "$0")/src"

echo "Compiling JavaBasics..."
javac Main.java

echo "Running JavaBasics..."
java Main
