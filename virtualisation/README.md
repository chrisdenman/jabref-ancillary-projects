# PDF not opening in standard application [#12356](https://github.com/JabRef/jabref/issues/12356) - Virtualisation

## System Requirements

- Linux
- [Vagrant](https://developer.hashicorp.com/vagrant/install)
- [VirtualBox](https://www.virtualbox.org/wiki/Downloads)

## Queries to Raise

### Why doesn't Gradle return immediately after calling `Desktop.getDesktop().open(file);` in the `mitigation` project?
### `Desktop.getDesktop().open(file);` should throw a `RuntimeException` (`UnsupportedOperationException`) on unsupported systems 
...so the exception handlers in the existing code: 
```
try {
    Desktop.getDesktop().open(Path.of(filePath).toFile());
} catch (IllegalArgumentException e) {
    try {
        String[] cmd = {"xdg-open", filePath};
        Runtime.getRuntime().exec(cmd);
    } catch (Exception e2) {
    }
} catch (IOException e) { ... }
```  
, would surely never be triggered?

### Generally, the exception handling wrapping the method we're interested in seems 'interseting'.
