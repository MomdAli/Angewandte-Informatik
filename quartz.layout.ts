import { PageLayout, SharedLayout } from "./quartz/cfg"
import * as Component from "./quartz/components"

// components shared across all pages
export const sharedPageComponents: SharedLayout = {
	head: Component.Head(),
	header: [],
	afterBody: [
		Component.RecentNotes({
			showTags: false,
			limit: 3
		}),
		Component.Comments({
			provider: "giscus",
			options: {
				repo: "MomdAli/Angewandte-Informatik",
				repoId: "R_kgDOMXLlag",
				category: "Announcements",
				categoryId: "DIC_kwDOMXLlas4Cpy8_",
				reactionsEnabled: true,
				inputPosition: "top",
				themeUrl: "https://momdali.de/static/giscus",
				darkTheme: "dark",
				lightTheme: "light",
				strict: false,
			}
		})
	],
	footer: Component.Footer(),
}

// components for pages that display a single page (e.g. a single note)
export const defaultContentPageLayout: PageLayout = {
	beforeBody: [
		Component.ConditionalRender({
			component: Component.Breadcrumbs(),
			condition: (page) => page.fileData.slug !== "index",
		}),
		Component.ArticleTitle(),
		Component.ContentMeta(),
		Component.TagList(),
	],
	left: [
		Component.PageTitle(),
		Component.MobileOnly(Component.Spacer()),
		Component.Flex({
			components: [
				{
					Component: Component.Search(),
					grow: true,
				},
				{ Component: Component.Darkmode() },
				{ Component: Component.ReaderMode() },
			],
		}),
		Component.Explorer({
			title: "Explore",
			folderClickBehavior: "collapse",
			folderDefaultState: "collapsed"
		}),
	],
	right: [
		Component.Graph({
			localGraph: {
				drag: true,
				zoom: true,
				depth: 1,
				scale: 1.1,
				repelForce: 0.8,       // mehr Abstand zwischen Nodes
				centerForce: 0.4,      // weniger starkes Zentrieren
				linkDistance: 120,     // deutlich lockerer!
				fontSize: 0.6,
				opacityScale: 1,
				removeTags: [],
				showTags: true,
				enableRadial: true,
			},
			globalGraph: {
				drag: true,
				zoom: true,
				depth: 3,
				scale: 1.5,
				repelForce: 0.8,
				centerForce: 1.0,
				linkDistance: 120,
				fontSize: 0.6,
				opacityScale: 1,
				removeTags: [],
				showTags: true,
				enableRadial: true,
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
		Component.Flex({
			components: [
				{
					Component: Component.Search(),
					grow: true,
				},
				{ Component: Component.Darkmode() },
				{ Component: Component.ReaderMode() },
			],
		}),
		Component.DesktopOnly(Component.Explorer({
			title: "Explore",
			folderClickBehavior: "collapse",
			folderDefaultState: "collapsed"
		})),
	],
	right: [],
}
