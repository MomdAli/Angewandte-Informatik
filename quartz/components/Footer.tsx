import { QuartzComponent, QuartzComponentConstructor, QuartzComponentProps } from "./types"
import style from "./styles/footer.scss"
import { version } from "../../package.json"
import { i18n } from "../i18n"


export default (() => {
  const Footer: QuartzComponent = ({ displayClass, cfg }: QuartzComponentProps) => {
    const year = new Date().getFullYear()
    return (
      <footer class={`${displayClass ?? ""}`}>
        <p>
          {i18n(cfg.locale).components.footer.createdWith}{" "}
          <a href="https://quartz.jzhao.xyz/">Quartz v{version}</a> © {year}
        </p>
        <ul>
          <li>
            <a href="https://github.com/MomdAli/Angewandte-Informatik" target="_blank" rel="noopener">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="currentColor">
                <path d="M12 .5C5.73.5.5 5.73.5 12.02c0 5.11 3.3 9.44 7.88 10.97.58.1.79-.25.79-.55v-2.14c-3.21.7-3.89-1.54-3.89-1.54-.53-1.35-1.3-1.71-1.3-1.71-1.06-.73.08-.72.08-.72 1.18.08 1.81 1.22 1.81 1.22 1.04 1.78 2.73 1.27 3.4.97.1-.75.41-1.27.75-1.56-2.56-.29-5.26-1.28-5.26-5.71 0-1.26.45-2.3 1.2-3.12-.12-.29-.52-1.45.11-3.03 0 0 .97-.31 3.18 1.19a11.1 11.1 0 0 1 2.9-.39c.98 0 1.97.13 2.9.39 2.2-1.5 3.17-1.19 3.17-1.19.63 1.58.23 2.74.11 3.03.75.82 1.2 1.86 1.2 3.12 0 4.44-2.71 5.42-5.29 5.7.42.36.8 1.09.8 2.2v3.27c0 .3.21.65.8.54A10.52 10.52 0 0 0 23.5 12C23.5 5.73 18.27.5 12 .5Z" />
              </svg>
              GitHub
            </a>
          </li>
          <li>
            <a href="https://discord.gg/nwtQs3UMec" target="_blank" rel="noopener">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="currentColor">
                <path d="M20.317 4.369a19.791 19.791 0 0 0-4.885-1.515.07.07 0 0 0-.075.035c-.21.375-.444.864-.608 1.249a18.207 18.207 0 0 0-5.487 0 12.66 12.66 0 0 0-.617-1.25.077.077 0 0 0-.076-.035 19.736 19.736 0 0 0-4.885 1.516.064.064 0 0 0-.03.027C2.261 9.039 1.616 13.582 2.013 18.057a.082.082 0 0 0 .031.056 19.994 19.994 0 0 0 5.993 3.038.07.07 0 0 0 .076-.027c.462-.63.873-1.295 1.226-1.994a.076.076 0 0 0-.041-.104 13.164 13.164 0 0 1-1.872-.9.07.07 0 0 1-.007-.118c.126-.095.252-.194.372-.295a.07.07 0 0 1 .074-.01c3.927 1.793 8.18 1.793 12.062 0a.07.07 0 0 1 .075.009c.12.101.246.2.373.295a.07.07 0 0 1-.006.118 12.534 12.534 0 0 1-1.873.899.076.076 0 0 0-.04.105c.36.698.772 1.363 1.225 1.993a.07.07 0 0 0 .075.028 19.941 19.941 0 0 0 6.002-3.038.077.077 0 0 0 .03-.056c.5-5.177-.838-9.676-3.548-13.66a.062.062 0 0 0-.03-.028ZM8.02 15.331c-1.183 0-2.157-1.085-2.157-2.419 0-1.333.955-2.419 2.157-2.419 1.21 0 2.175 1.095 2.157 2.42 0 1.334-.955 2.418-2.157 2.418Zm7.975 0c-1.183 0-2.157-1.085-2.157-2.419 0-1.333.955-2.419 2.157-2.419 1.21 0 2.175 1.095 2.157 2.42 0 1.334-.948 2.418-2.157 2.418Z" />
              </svg>
              Discord
            </a>
          </li>
        </ul>
      </footer>
    )
  }

  Footer.css = style
  return Footer
}) satisfies QuartzComponentConstructor