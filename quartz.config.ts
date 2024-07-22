import { QuartzConfig } from "./quartz/cfg"
import * as Plugin from "./quartz/plugins"

/**
 * Quartz 4.0 Configuration
 *
 * See https://quartz.jzhao.xyz/configuration for more information.
 */
const config: QuartzConfig = {
  configuration: {
    pageTitle: "🦉 Main",
    enableSPA: true,
    enablePopovers: true,
    analytics: {
      provider: "plausible",
    },
    locale: "de-DE",
    baseUrl: "momdali.github.io/Angewandte-Informatik",
    ignorePatterns: ["private", "Templates", ".obsidian", "Excalidraw"],
    defaultDateType: "modified",
    theme: {
      fontOrigin: "googleFonts",
      cdnCaching: true,
      typography: {
        header: "Schibsted Grotesk",
        body: "DM Sans",
        code: "JetBrains Mono",
      },
      colors: {
        lightMode: {
          light: "#faf8f8",     // page background
          lightgray: "#e5e5e5", // borders
          gray: "#b8b8b8",      // graph links, heavier borders
          darkgray: "#4e4e4e",  // body text
          dark: "#2b2b2b",      // header text and icons
          secondary: "#284b63", // link colour, current graph node
          tertiary: "#84a59d",  // hover states and visited graph nodes
          highlight: "rgba(143, 159, 169, 0.15)", // internal link background
          textHighlight: "#fff23688", // markdown highlighted text background
        },
        darkMode: {
          light: "#231a1a",       // page background
          lightgray: "#393639",   // borders
          gray: "#646464",        // graph links, heavier borders
          darkgray: "#d4d4d4",    // body text
          dark: "#ebebec",        // header text and icons
          secondary: "#7b97aa",   // link colour, current graph node
          tertiary: "#84a59d",    // hover states and visited graph nodes
          highlight: "rgba(143, 159, 169, 0.15)", // internal link background
          textHighlight: "#b3aa0288", // markdown highlighted text background
        },
      },
    },
  },
  plugins: {
    transformers: [
      Plugin.FrontMatter(),
      Plugin.CreatedModifiedDate({
        priority: ["frontmatter", "filesystem"],
      }),
      Plugin.SyntaxHighlighting({
        theme: {
          light: "github-light",
          dark: "github-dark",
        },
        keepBackground: false,
      }),
      Plugin.ObsidianFlavoredMarkdown({ enableInHtmlEmbed: false}),
      Plugin.GitHubFlavoredMarkdown(),
      Plugin.TableOfContents(),
      Plugin.CrawlLinks({ markdownLinkResolution: "shortest" }),
      Plugin.Description(),
      Plugin.Latex({ renderEngine: "katex" }),
    ],
    filters: [Plugin.RemoveDrafts()],
    emitters: [
      Plugin.AliasRedirects(),
      Plugin.ComponentResources(),
      Plugin.ContentPage(),
      Plugin.FolderPage(),
      Plugin.TagPage(),
      Plugin.ContentIndex({
        enableSiteMap: true,
        enableRSS: true,
      }),
      Plugin.Assets(),
      Plugin.Static(),
      Plugin.NotFoundPage(),
    ],
  },
}

export default config
