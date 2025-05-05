import { QuartzComponent, QuartzComponentConstructor, QuartzComponentProps } from "./types"
import style from "./styles/footer.scss"
import { version } from "../../package.json"
import { i18n } from "../i18n"


const Footer: QuartzComponent = ({ displayClass, cfg }: QuartzComponentProps) => {
  const year = new Date().getFullYear()
  return (
    <footer class={`${displayClass ?? ""}`}>
      <p>
        {i18n(cfg.locale).components.footer.createdWith}{" "}
        <a href="https://quartz.jzhao.xyz/" target="_blank" rel="noopener noreferrer">
          Quartz v{version}
        </a>{" "}
        © {year}
      </p>
      <div class="footer-links">
        <a
          href="https://github.com/MomdAli/Angewandte-Informatik"
          class="footer-button"
          target="_blank"
          rel="noopener noreferrer"
        >
          <svg class="icon" viewBox="0 0 24 24" fill="currentColor">
            <path d="M12 0C5.37 0 0 5.373 0 12a12 12 0 0 0 8.207 11.387c.6.111.793-.26.793-.577v-2.234c-3.338.725-4.042-1.416-4.042-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.09-.745.082-.729.082-.729 1.205.085 1.84 1.245 1.84 1.245 1.07 1.834 2.807 1.304 3.492.997.108-.776.418-1.304.762-1.603-2.665-.304-5.467-1.336-5.467-5.944 0-1.313.47-2.387 1.243-3.228-.124-.303-.538-1.523.117-3.176 0 0 1.008-.322 3.3 1.23a11.49 11.49 0 0 1 6.002 0c2.29-1.552 3.296-1.23 3.296-1.23.657 1.653.243 2.873.12 3.176.775.84 1.242 1.915 1.242 3.228 0 4.62-2.807 5.636-5.48 5.933.43.37.823 1.096.823 2.21v3.285c0 .32.192.694.8.576A12.002 12.002 0 0 0 24 12c0-6.627-5.373-12-12-12z" />
          </svg>
          GitHub
        </a>
        <a
          href="https://discord.gg/nwtQs3UMec"
          class="footer-button"
          target="_blank"
          rel="noopener noreferrer"
        >
          <svg class="icon" viewBox="0 0 24 24" fill="currentColor">
            <path d="M20.317 4.369a19.791 19.791 0 0 0-4.885-1.515.074.074 0 0 0-.078.037c-.211.375-.444.864-.608 1.25a18.27 18.27 0 0 0-5.487 0 12.23 12.23 0 0 0-.617-1.25.077.077 0 0 0-.078-.037c-1.653.297-3.34.816-4.884 1.515a.07.07 0 0 0-.033.027C.533 9.036-.32 13.59.099 18.104a.082.082 0 0 0 .031.056 19.919 19.919 0 0 0 5.993 3.019.077.077 0 0 0 .084-.027c.462-.63.873-1.295 1.226-1.991a.076.076 0 0 0-.041-.104 13.172 13.172 0 0 1-1.888-.902.077.077 0 0 1-.008-.129c.127-.096.254-.195.375-.293a.075.075 0 0 1 .078-.01c3.962 1.812 8.27 1.812 12.193 0a.075.075 0 0 1 .079.01c.122.098.248.197.375.293a.077.077 0 0 1-.007.129 12.57 12.57 0 0 1-1.889.901.076.076 0 0 0-.04.105c.36.696.77 1.36 1.225 1.99a.076.076 0 0 0 .084.028 19.888 19.888 0 0 0 6.004-3.018.077.077 0 0 0 .03-.057c.5-5.177-.838-9.697-3.548-13.709a.06.06 0 0 0-.032-.028zM8.02 15.331c-1.183 0-2.157-1.085-2.157-2.419 0-1.333.955-2.419 2.157-2.419 1.212 0 2.176 1.096 2.157 2.419 0 1.334-.955 2.419-2.157 2.419zm7.956 0c-1.183 0-2.157-1.085-2.157-2.419 0-1.333.955-2.419 2.157-2.419 1.212 0 2.176 1.096 2.157 2.419 0 1.334-.945 2.419-2.157 2.419z" />
          </svg>
          Discord
        </a>
      </div>
    </footer>
  )
}

Footer.css = style
export default (() => Footer) satisfies QuartzComponentConstructor
