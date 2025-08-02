#!/bin/bash

echo "Installing SDKMAN!"
cd "$HOME" || exit 1
curl --silent "https://get.sdkman.io" | bash
chmod u+x "$HOME/.sdkman/bin/sdkman-init.sh"
sed --in-place'' \
  --expression "s/sdkman_auto_env=false/sdkman_auto_env=true/" \
  --expression "s/sdkman_auto_answer=false/sdkman_auto_answer=true/" \
  "$HOME/.sdkman/etc/config"
. "$HOME/.sdkman/bin/sdkman-init.sh"
cd mitigation || exit 1
sdk env install # Have to do this as SDKMAN! auto environment detection and configuration not working :(
