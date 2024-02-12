#!/bin/bash

TP2_HOME=$(cd "$(dirname "$0")"/../ && pwd)

echo "TP2_HOME: $TP2_HOME"

APP_DIR="$TP2_HOME/src"

echo  "APP_DIR: $APP_DIR"


CP="$APP_DIR"

echo  "Classpath: $CP"

export CLASSPATH=$CLASSPATH$CP

echo  $CLASSPATH

rmiregistry 6090 &
