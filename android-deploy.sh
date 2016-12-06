rm -rf /Users/ohadbenporat/dev/DisignStudio/disign-studio/platforms/android/build/outputs/apk/*
cd ./disign-studio
ionic build --release android
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore release.keystore /Users/ohadbenporat/dev/DisignStudio/disign-studio/platforms/android/build/outputs/apk/android-release-unsigned.apk release
cd /usr/local/Cellar/android-sdk/24.4.1_1/build-tools/23.0.2
./zipalign -v 4 /Users/ohadbenporat/dev/DisignStudio/disign-studio/platforms/android/build/outputs/apk/android-release-unsigned.apk /Users/ohadbenporat/dev/DisignStudio/disign-studio/platforms/android/build/outputs/apk/DisignStudio.apk
