import { pathToRoot, joinSegments } from "../util/path"
import { QuartzComponent, QuartzComponentConstructor, QuartzComponentProps } from "./types"
import { classNames } from "../util/lang"
import { i18n } from "../i18n"

const PageTitle: QuartzComponent = ({ fileData, cfg, displayClass }: QuartzComponentProps) => {
  const title = cfg?.pageTitle ?? i18n(cfg.locale).propertyDefaults.title
  const baseDir = pathToRoot(fileData.slug!)
  return (
    <h2 class={classNames(displayClass, "page-title")}>
      <a href={baseDir}
        style="display: flex; align-items: center;"
      ><img
        src={joinSegments(baseDir, "static/icon.png")}
        alt="icon"
        class="page-title-icon Logo"
        width={32}
        height={32}
        loading="lazy"
        style="margin: 0 1rem 0 0.3rem"
      ></img>{title}</a>
    </h2>
  )
}

PageTitle.css = `
.page-title {
  font-size: 1.75rem;
  margin: 0;
  font-family: var(--titleFont);
}
`

export default (() => PageTitle) satisfies QuartzComponentConstructor
