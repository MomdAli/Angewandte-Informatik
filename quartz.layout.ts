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
		Component.Breadcrumbs({
			spacerSymbol: "⇒",
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
		Component.Graph(),
		Component.DesktopOnly(Component.TableOfContents()),
		Component.Backlinks(),
	],
}

// components for pages that display lists of pages  (e.g. tags or folders)
export const defaultListPageLayout: PageLayout = {
	beforeBody: [Component.Breadcrumbs({ spacerSymbol: "⇒" }), Component.ArticleTitle(), Component.ContentMeta()],
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
