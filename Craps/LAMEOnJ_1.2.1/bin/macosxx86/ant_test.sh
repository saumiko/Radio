#!/bin/sh

export ANT_HOME=../../../tools/ant
export PATH=$ANT_HOME/bin:$PATH

ant -buildfile ../../build_LAMEOnJ.xml test
