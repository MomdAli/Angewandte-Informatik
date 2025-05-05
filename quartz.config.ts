import { QuartzConfig } from "./quartz/cfg"
import * as Plugin from "./quartz/plugins"

/**
 * Quartz 4 Configuration
 *
 * See https://quartz.jzhao.xyz/configuration for more information.
 */
const config: QuartzConfig = {
  configuration: {
    pageTitle: "🦉 Main",
    pageTitleSuffix: "",
    enableSPA: true,
    enablePopovers: true,
    analytics: {
      provider: "plausible",
    },
    locale: "de-DE",
    baseUrl: "momdali.de",
    ignorePatterns: [
      "Private",
      "Templates",
      ".obsidian",
      "Excalidraw"
    ],
    defaultDateType: "modified",
    theme: {
      fontOrigin: "googleFonts",
      cdnCaching: true,
      typography: {
        header: "Schibsted Grotesk",
        body: "Lora",
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
          light: "#212129",       // page background
          lightgray: "#393639",   // borders
          gray: "#646464",        // graph links, heavier borders
          darkgray: "#F5F4ED",    // body text
          dark: "#FFF6B4",        // header text and icons
          secondary: "#7b97aa",   // link colour, current graph node
          tertiary: "#84a59d",    // hover states and visited graph nodes
          highlight: "rgba(143, 159, 169, 0.15)", // internal link background
          textHighlight: "#10f49166", // markdown highlighted text background
        },
      },
    },
  },
  plugins: {
    transformers: [
      Plugin.HardLineBreaks(),
      Plugin.FrontMatter(),
      Plugin.CreatedModifiedDate({
        priority: ["frontmatter", "git", "filesystem"],
      }),
      Plugin.SyntaxHighlighting({
        theme: {
          light: "github-light",
          dark: "catppuccin-macchiato",
        },
        keepBackground: true,
      }),
      Plugin.ObsidianFlavoredMarkdown({ enableInHtmlEmbed: true }),
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
      Plugin.FolderPage({
        sort: (f1, f2) => {
          const f1Title = f1.frontmatter?.title.toLowerCase() ?? ""
          const f2Title = f2.frontmatter?.title.toLowerCase() ?? ""
          return f1Title.localeCompare(f2Title, undefined, {
            numeric: true,
            sensitivity: "base",
          })
        }
      }),
      Plugin.TagPage(),
      Plugin.ContentIndex({
        enableSiteMap: true,
        enableRSS: true,
      }),
      Plugin.Assets(),
      Plugin.Static(),
      Plugin.Favicon(),
      Plugin.NotFoundPage(),
      // Comment out CustomOgImages to speed up build time
      //Plugin.CustomOgImages(),
    ],
  },
}

export default config
