# Fix APS fetcher [#12864](https://github.com/JabRef/jabref/issues/12864)

Proof of Concept for CloudFlare Workaround

## Notes

This repository contains some work in examining if it is possible to extract PDF content from online sources that are
proxied by CloudFlare.

CloudFlare is actively protecting resources from being scraped by AI agents (or other atuomated software).

This may not be a big issue for [JabRef](https://github.com/JabRef) currently, but it may become more onerous, as time
progresses.

## System Requirements

- [SDKMAN! : sdkman.io](https://sdkman.io/)

## Running the Code

```shell
./gradlew run
```

## Analysis of CloudFlare Flow

- User requests resource in browser
  e.g. [https://journals.aps.org/prl/pdf/10.1103/PhysRevLett.116.061102](https://journals.aps.org/prl/pdf/10.1103/PhysRevLett.116.061102).
- CloudFlare returns HTML page (see [cloudflare.html](cloudflare.html) one for-example).
- This HTML page loads, it requires cookies and JavaScript enabled
- Page does some hash magic (and presumably fingerprinting) and appends a child script element to the first head
  element, which replaces the current page with actual PDF source (maybe).
- Browser's internal viewer renders the PDF.

## Wins

- We can load a page into an automated (currently FireFox) browser, using WebDriver.
- If one uncomments the code in [Main.java](src/main/java/org/jabref/Main.java), when the application is run,
  you'll observe that the browser saves the PDF file directly.
- We can see the page is rendered using HTML (and therefore we can extract text in the normal way(s)).
- In FireFox's Web Developer Tools, if you enter `this` in the 'Console', you'll see the `PDFViewApplication` 'global'
  which can be controlled at will (fetching other pages, ...) e.g.
  `window.PDFViewerApplication.pdfDocument.getPage(2)`...

## Other Ideas

Perhaps we can inject our own script into the page that monkeys with the response from CloudFlare.

## Conclusions

It is at least possible to automate the fetching and scraping of PDFs that are wrapped up by CloudFlare in this manner.

Whether it is too brittle to be worthwhile, is another question.

## [UNLICENSE](UNLICENSE)
