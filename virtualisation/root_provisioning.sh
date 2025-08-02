#!/bin/bash

# Executed by Vagrant's 'shell' provisioner as root user.
# The provisioner passes in the name of the package manager to use (see the Vagrantfile)

case $1 in
  openSUSE)
    zypper -n refresh
    zypper -n update
    zypper -n install kernel-devel gcc make patterns-kde-kde sddm virtualbox-guest-tools zip unzip konsole dolphin okular xdg-utils firefox

    sudo systemctl enable sddm.service
    sudo systemctl enable vboxservice.service
    sudo systemctl set-default graphical.target

    sudo sed -i'' \
      -e 's/^DISPLAYMANAGER_AUTOLOGIN=.*/DISPLAYMANAGER_AUTOLOGIN="vagrant"/' \
      /etc/sysconfig/displaymanager
    ;;
  ubuntu)
    export DEBIAN_FRONTEND=noninteractive && apt-get update && apt-get -y install zip
    ;;
esac

su -c "source /home/vagrant/vagrant_provisioning.sh $1" vagrant
