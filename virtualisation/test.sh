#!/bin/bash

cat /etc/os-release
env | sort | grep XDG
cd reproduction || exit 1
./run.sh
cd .. || exit 1

