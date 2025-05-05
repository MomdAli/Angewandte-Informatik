---
title: 🌳 Mohammed's Second Brain🧠
date: 2024-07-16
description: Just my little corner of the internet where I drop notes, thoughts, and projects while studying computer science. It’s messy, it’s growing, and it helps me make sense of what I’m learning. Kind of like my digital brain.
---
![[Banner.png]]

<div class="button-container">
  <button class="linker" type="button" onclick="window.location.href='Studium/index'">
    <span></span><span></span><span></span><span></span>
    Studienunterlagen
  </button>
  <button class="linker" type="button" onclick="window.location.href='tags/'">
    <span></span><span></span><span></span><span></span>
    Tags
  </button>
  <button class="linker" type="button" onclick="window.location.href='mailto:mohammedali.alsaiaf@gmail.com'">
    <span></span><span></span><span></span><span></span>
    Email-Adresse
  </button>
  <button class="linker" type="button" onclick="window.open('https://discord.gg/nwtQs3UMec','_blank')">
    <span></span><span></span><span></span><span></span>
    Discord Server
  </button>
</div>

<style>
.button-container {
  display: grid;
  gap: 1em;
  width: 100%;
  margin: 2em auto;
}

button.linker {
  position: relative;
  padding: 1.3em;
  outline: none;
  border: 1px solid rgb(69, 71, 90);       /* surface1 */
  background: rgb(30, 30, 46);              /* surface0 */
  color: rgb(203, 166, 247);                /* mauve */
  text-transform: uppercase;
  letter-spacing: 2px;
  font-size: 20px;
  overflow: hidden;
  transition: 0.2s;
  border-radius: 20px;
  cursor: pointer;
  font-weight: bold;
  margin: 0 4em;
}

button.linker span {
  position: absolute;
}

/* top bar */
button.linker span:nth-child(1) {
  top: 0; left: -100%;
  width: 100%; height: 2px;
  background: linear-gradient(90deg, transparent, rgb(203, 166, 247));
}
/* right bar */
button.linker span:nth-child(2) {
  top: -100%; right: 0;
  width: 2px; height: 100%;
  background: linear-gradient(180deg, transparent, rgb(203, 166, 247));
}
/* bottom bar */
button.linker span:nth-child(3) {
  bottom: 0; right: -100%;
  width: 100%; height: 2px;
  background: linear-gradient(90deg, transparent, rgb(137, 220, 235));
}
/* left bar */
button.linker span:nth-child(4) {
  bottom: -100%; left: 0;
  width: 2px; height: 100%;
  background: linear-gradient(360deg, transparent, rgb(137, 220, 235));
}

button.linker:hover {
  box-shadow:
    0 0 10px rgb(203, 166, 247),
    0 0 25px rgb(137, 220, 235),
    0 0 50px rgb(203, 166, 247);
  transition-delay: 0.6s;
}

button.linker:hover span:nth-child(1) {
  left: 100%; transition: 0.7s;
}
button.linker:hover span:nth-child(2) {
  top: 100%; transition: 0.7s; transition-delay: 0.17s;
}
button.linker:hover span:nth-child(3) {
  right: 100%; transition: 0.7s; transition-delay: 0.35s;
}
button.linker:hover span:nth-child(4) {
  bottom: 100%; transition: 0.7s; transition-delay: 0.52s;
}

button.linker:active {
  background: linear-gradient(
    to top right,
    rgb(203, 166, 247),
    rgb(137, 220, 235)
  );
  color: rgb(186, 194, 222);
  box-shadow:
    0 0 8px rgb(203, 166, 247),
    0 0 8px rgb(137, 220, 235),
    0 0 8px rgb(203, 166, 247);
  transition: 0.1s;
}

button.linker:active span:nth-child(1),
button.linker:active span:nth-child(2),
button.linker:active span:nth-child(3),
button.linker:active span:nth-child(4) {
  transition: none;
  transition-delay: 0;
}
</style>

