#!/bin/sh

if [ $(basename $(pwd)) != "jabref-ancillary-projects" ]; then
  >&2 echo "Expected to be in the \"jabref-ancillary-projects\" directory, exiting."
  exit 1
fi

for gradle_project_name in reproduction; do
  cd "${gradle_project_name}" || exit 1
  ./gradlew clean
  cd - || exit 1
done

cd virtualisation || exit 1
for vagrant_box_name in $(grep -v "^\s*#.*$" ./Vagrantfile | grep -oP "(?<=config\.vm\.define).*(\")" | tr -d ' "'); do
  echo "Testing environment \"${vagrant_box_name}\""
  vagrant destroy "${vagrant_box_name}" -f
  vagrant up "${vagrant_box_name}"  || exit 1
  vagrant reload "${vagrant_box_name}"  || exit 1
  echo "Please execute your tests on the Vagrant box by running the \"/home/vagrant/test.sh\" script."
  printf 'Press any key to continue... '
  # disable echo
  stty -echo
  IFS= read -r _
  stty echo
  printf '\n'
  vagrant destroy "${vagrant_box_name}" -f
  echo
done
