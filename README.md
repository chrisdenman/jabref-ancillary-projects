# PDF not opening in standard application [#12356](https://github.com/JabRef/jabref/issues/12356)

Reproduction & mitigation of the parent-issue using Vagrant.

## Notes

- The [reproduction](./reproduction) directory contains a Gradle project that reproduces the parent issue.
- The [mitigation](./mitigation) directory contains a Gradle project that re-implements the `Linux.linuxNativeOpenFile`
  method.
- The [virtualisation](./virtualisation) directory contains a project that runs the `reproduction` and `mitigation`
  Gradle projects on arbitrary virtualised environments.

## Further Investigations

### Get the reporter to provide the output from:

- `cat /etc/os-release`
- `env | grep XDG | sort`
- `kf5-config --version`
- `plasmashell --version`
- `xdg-mime query default application/pdf`
- `zypper search --installed-only | grep qtfm`
- `ls -al ~/.config/mimeapps.list`
- `ls -al ~/.local/share/applications/mimeapps.list`

## End-User Interventions

- `kbuildsycoca5 --noincremental`
- Does the desktop honour the media type associations in any way? E.g. by opening a PDF in the desktop. Is this just the
  case that the user can't change any media type associations generally (or just PDFs)?
