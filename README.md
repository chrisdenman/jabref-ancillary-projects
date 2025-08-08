# [JabRef Ancillary Projects]()

To toil on [JabRef](https://github.com/JabRef/jabref) reproductions/fixes/investigations...

## Structure

- One branch for each ancillary project
- Branch named prefixed with the corresponding [JabRef](https://github.com/JabRef/jabref) issue's key, for example
  `#12356-pdf-not-opening-in-standard-application--minimial-vagrant-reproduction`

## Ancillary Project Branches

| JabRef Issue                                                                                    | Branch Name                                                    | Notes                                                                             |
|-------------------------------------------------------------------------------------------------|----------------------------------------------------------------|-----------------------------------------------------------------------------------|
| [PDF not opening in standard application #12356](https://github.com/JabRef/jabref/issues/12356) | #12356-pdf-not-opening-in-standard-application                 | Reproduction & mitigation using Vagrant                                           |
| [Fix APS fetcher #12864](https://github.com/JabRef/jabref/issues/12864)                         | #12864-fix-aps-fetcher--testing-with-selenium                  | Scraping text from CloudFlare protected PDF resources using Selenium.             |
| [Fix APS fetcher #12864](https://github.com/JabRef/jabref/issues/12864)                         | #12864-fix-aps-fetcher--testing-with-webview                   | Seeing if JavaFX WebView helps.                                                   |
| [Shortcut Ctrl+R does not work #12564](https://github.com/JabRef/jabref/issues/12564)           | #12564-shortcut-ctrl+r-does-not-work--accelerator_architecture | Demonstrating that accelerators are scoped to the scene and key combination only. |

## Creating an Ancillary Project

#### 1. Create a New (Orphan) Branch

 ```shell
   git checkout --orphan "#[JabRef Issue Number]-[lowercase-original-issue-title]--[short-description-of-project]"
   ```

The new branch is not related to `main`, it stands in its own right. It is not merged back to `main`.

#### 2. Switch to Your Branch, Toil & Commit...

#### 3. Switch back to main

#### 4. Add a Line in § [Ancillary Project Branches](#ancillary-project-branches)

**Note: As this project uses branches to isolation different projects, you may need to delete local temporary
directories (in particular, but not limited to: `build` & `.gradle`), before or after switching branches.**

## [License](LICENSE)