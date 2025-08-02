# [JabRef Ancillary Projects]()

To toil on [JabRef](https://github.com/JabRef/jabref) reproductions/fixes/investigations...

## Structure

- One branch for each ancillary project
- Branch named prefixed with the corresponding [JabRef](https://github.com/JabRef/jabref) issue's key, for example
  `#12356-pdf-not-opening-in-standard-application--minimial-vagrant-reproduction`

## Ancillary Project Branches

| JabRef Issue                                                                                    | Branch Name                                    | Notes                                   |
|-------------------------------------------------------------------------------------------------|------------------------------------------------|-----------------------------------------|
| [PDF not opening in standard application #12356](https://github.com/JabRef/jabref/issues/12356) | #12356-pdf-not-opening-in-standard-application | Reproduction & mitigation using Vagrant |

## Creating an Ancillary Project

### Create a New (Orphan) Branch

 ```shell
   git checkout --orphan "#[JabRef Issue Number]-[lowercase-original-issue-title]--[short-description-of-project]"
   ```

The new branch is not related to `main`, it stands in its own right. It is not merged back to `main`.

### Switch to Your Branch, Toil & Commit...

### Add a Line in [Ancillary Project Branches](#ancillary-project-branches)

## [License](LICENSE)