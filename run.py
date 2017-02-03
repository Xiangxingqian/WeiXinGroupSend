import os 
os.popen('android create uitest-project -n wgs -t 7 -p .')
os.popen('ant build')
os.popen('adb push bin/wgs.jar /data/local/tmp/')
os.popen('adb shell uiautomator runtest wgs.jar -c LaunchSettings')
