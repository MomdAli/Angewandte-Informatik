import { PageLayout, SharedLayout } from "./quartz/cfg"
import * as Component from "./quartz/components"

// components shared across all pages
export const sharedPageComponents: SharedLayout = {
  head: Component.Head(),
  header: [],
  afterBody: [
    Component.RecentNotes({
      showTags: false,
      limit: 3,
    }),
  ],
  footer: Component.Footer({
    links: {
      GitHub: "https://github.com/MomdAli/Angewandte-Informatik",
      "Discord Server": "https://discord.gg/nwtQs3UMec",
    },
  }),
}

// components for pages that display a single page (e.g. a single note)
export const defaultContentPageLayout: PageLayout = {
  beforeBody: [
    Component.Breadcrumbs(),
    Component.ArticleTitle(),
    Component.ContentMeta(),
    Component.TagList(),
  ],
  left: [
    Component.PageTitle(),
    Component.MobileOnly(Component.Spacer()),
    Component.Search(),
    Component.Darkmode(),
    Component.DesktopOnly(Component.Explorer()),
  ],
  right: [
    Component.Graph({
      localGraph: {
        drag: true,       // whether to allow panning the view around
        zoom: true,       // whether to allow zooming in and out
        depth: 1,         // how many hops of notes to display
        scale: 1.1,       // default view scale
        repelForce: 0.5,  // how much nodes should repel each other
        centerForce: 0.3, // how much force to use when trying to center the nodes
        linkDistance: 30, // how long should the links be by default?
        fontSize: 0.6,    // what size should the node labels be?
        opacityScale: 1,  // how quickly do we fade out the labels when zooming out?
        removeTags: [],   // what tags to remove from the graph
        showTags: true,   // whether to show tags in the graph
      },
      globalGraph: {
        drag: true,
        zoom: true,
        depth: -1,
        scale: 0.9,
        repelForce: 8.75,
        centerForce: 0.42,
        linkDistance: 80,
        fontSize: 1,
        opacityScale: 2,
        removeTags: [], // what tags to remove from the graph
        showTags: true, // whether to show tags in the graph
      },
    }),
    Component.DesktopOnly(Component.TableOfContents()),
    Component.Backlinks(),
  ],
}

// components for pages that display lists of pages  (e.g. tags or folders)
export const defaultListPageLayout: PageLayout = {
  beforeBody: [Component.Breadcrumbs(), Component.ArticleTitle(), Component.ContentMeta()],
  left: [
    Component.PageTitle(),
    Component.MobileOnly(Component.Spacer()),
    Component.Search(),
    Component.Darkmode(),
    Component.DesktopOnly(Component.Explorer({
      title: "=> Explore",
      folderClickBehavior: "collapse",
      folderDefaultState: "collapsed",
      useSavedState: false,
      filterFn: (node) => node.name !== "Studium"
    })),
  ],
  right: [],
}
