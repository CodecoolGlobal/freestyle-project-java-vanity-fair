#!/bin/sh


find -name '*.java' | xargs javac -Xlint:all

java com.codecool.termlib.Main