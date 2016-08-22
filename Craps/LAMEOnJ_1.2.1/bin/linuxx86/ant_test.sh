#!/bin/sh

export ANT_HOME=../../../tools/ant
export PATH=$PATH:$ANT_HOME/bin

ant -buildfile ../../build_LAMEOnJ.xml test
