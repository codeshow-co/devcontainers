#!/bin/bash

apt update
apt install curl unzip zip -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 19.0.2-amzn
sdk install gradle 8.0.1
# sdk install maven 3.9.0