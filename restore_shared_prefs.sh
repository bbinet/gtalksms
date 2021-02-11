#!/bin/bash
export BASE_DIR="$( cd "$( dirname "$0" )" && pwd )"
SHARED_PREFS=$(cat $BASE_DIR/GTalkSMS.xml)

if ! adb shell 'test -e /data/data/com.googlecode.gtalksms/shared_prefs/GTalkSMS.xml'; then
    echo "/data/data/com.googlecode.gtalksms/shared_prefs/GTalkSMS.xml does not exist yet"
    echo "Please run GTalkSMS to create the file, so that this restore script works."
fi

cat <<EOF | adb shell 'cat > /data/data/com.googlecode.gtalksms/shared_prefs/GTalkSMS.xml'
$SHARED_PREFS
EOF
echo "/data/data/com.googlecode.gtalksms/shared_prefs/GTalkSMS.xml has now been restored..."
