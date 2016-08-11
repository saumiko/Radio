#!/bin/sh

java -DJNIEASY_LICENSE_DIR=.. -Djava.library.path=. -cp ../LAMEOnJTest.jar test.lameonj.decoder.TestDecoder ../testcase.mp3 ../testcase_res

