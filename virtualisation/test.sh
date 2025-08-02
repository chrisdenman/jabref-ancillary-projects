#!/bin/bash

cat /etc/os-release
env | sort | grep XDG
echo "The PDF file should open in \"$(xdg-mime query default application/pdf)\"."

echo "Running the reproduction code"
cd reproduction || exit 1
./run.sh
cd .. || exit 1

echo "Running the mitigation code"
cd mitigation || exit 1
./run.sh
cd .. || exit 1

