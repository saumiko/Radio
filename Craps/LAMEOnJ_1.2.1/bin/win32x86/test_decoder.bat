rem Add your Java path if necessary
rem set Path=C:\jdk1.5.0_02\jre\bin;%Path%


rem lame --decode ../testcase.mp3 ../testcase_res.wav

java -DJNIEASY_LICENSE_DIR=.. -cp ..\LAMEOnJTest.jar test.lameonj.decoder.TestDecoder ../testcase.mp3 ../testcase_res

pause
