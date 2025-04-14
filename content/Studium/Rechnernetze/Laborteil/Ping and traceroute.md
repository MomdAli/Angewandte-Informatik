---
title: Ping and traceroute
tags:
  - Rechnernetze
  - Semester-5
  - Informatik
date: 2025-04-13
cssclasses:
  - noBorder
  - noMargin
---
## Aufgabe 2.1)

Welche Pakete werden gesendet, wenn der Ping-Befehl ausgeführt wird? Kopieren Sie die entsprechenden Pakete aus WireShark. Führen Sie den Ping-Befehl gegebenenfalls für mehrere IP Adressen aus, um diese Pakete zu identifizieren.

| No.  | Time      | Source        | Destination   | Protocol | Length |
| ---- | --------- | ------------- | ------------- | -------- | ------ |
| 1523 | 27.784308 | 192.168.0.105 | 95.100.110.8  | ICMP     | 74     |
| 1524 | 27.791026 | 95.100.110.8  | 192.168.0.105 | ICMP     | 74     |

## Aufgabe 2.2)

Vor der Übertragung der eigentlichen Ping-Pakete wird (bei Verwendung eines neuen Hostnamens einer neuen URL) ein anderes Protokoll ausgeführt? Bestimmen Sie dieses Protokoll. Führen Sie den Ping-Befehl gegebenenfalls für mehrere neue Web-Seiten aus

| No. | Time     | Source        | Destination   | Protocol | Length |
| --- | -------- | ------------- | ------------- | -------- | ------ |
| 14  | 2.892798 | 192.168.0.105 | 8.8.8.8       | DNS      | 73     |
| 15  | 2.902273 | 8.8.8.8       | 192.168.0.105 | DNS      | 181    |

## Aufgabe 2.3)

Welche Ergebnisse liefert der Ping-Befehl (Ausgabe in der Command Line)? Wie können Sie diese Ergebnisse aus den in WireShark aufgezeichneten Paketen bestimmen?

```cmd title="Terminal"
PS C:\Users\mohsa> ping www.umass.edu

Pinging e28010.dscb.akamaiedge.net [95.100.110.8] with 32 bytes of data:
Reply from 95.100.110.8: bytes=32 time=6ms TTL=52
Reply from 95.100.110.8: bytes=32 time=6ms TTL=52
Reply from 95.100.110.8: bytes=32 time=6ms TTL=52
Reply from 95.100.110.8: bytes=32 time=6ms TTL=52

Ping statistics for 95.100.110.8:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 6ms, Maximum = 6ms, Average = 6ms
```


![[Wireshark-SS-01.png]]
Auf das **Echo Reply** klicken (z. B. Nr. 6), unten klappt sich die Paketstruktur auf
"Internet Control Message Protocol" -> Response Time: 6,776 ms
TTL steht oben rechts.

## Aufgabe 2.4)

Erstellen Sie einen Filter für diese beiden Protokolle (zusätzlich zu dem Filter auf ihre IP-
Adresse), um nur diese beiden Protokolle zu filtern. Testen Sie den Filter, indem Sie weitere
Adressen pingen.

```c
(ip.addr == 192.168.0.105) && (icmp || dns)
```
## Aufgabe 2.5)

Implementieren Sie eine Versuchsreihe in Python (Tipp: ping aus dem Modul icmplib).
Suchen Sie sich IP-Adressen/Hostnamen von Rechnern, die sich an unterschiedlichen Orten
befinden z.B. im Labor, an der HTWG, in Deutschland, in Australien oder in Nordamerika.
Beispiele sind Server von Universitäten, Staaten, Zeitungen, Firmen, etc. Pingen Sie die
Adressen jeweils 100mal und stellen Sie die Ping-Zeit grafisch dar.

```python
import matplotlib.pyplot as plt
from icmplib import ping
import time
 
# Liste der Ziele
targets = {
    'HTWG Konstanz': 'www.htwg-konstanz.de',
    'Deutschland (Spiegel)': 'www.spiegel.de',
    'Australien (Uni Melbourne)': 'www.unimelb.edu.au',
    'USA (MIT)': 'web.mit.edu'
}

ping_results = {}
count = 100

# Pingen
for name, host in targets.items():
    print(f"Pinge {name} ({host})...")
    times = []
    for i in range(count):
        try:
            result = ping(host, count=1, timeout=2)
            if result.packets_received > 0:
                times.append(result.avg_rtt)
            else:
                times.append(None)
        except Exception as e:
            print(f"Fehler bei {host}: {e}")
            times.append(None)
        # time.sleep(0.01) # Pause
    ping_results[name] = times

# Subplots erstellen
num_targets = len(targets)
fig, axes = plt.subplots(num_targets, 1, figsize=(12, 3 * num_targets), sharex=True)

if num_targets == 1:
    axes = [axes]  # In Liste verpacken wenn nur ein Ziel

for ax, (name, times) in zip(axes, ping_results.items()):
    clean_times = [t if t is not None else 0 for t in times]
    ax.plot(clean_times, marker='o', linestyle='-')
    ax.set_title(f'Ping-Zeiten: {name}')
    ax.set_ylabel('Zeit (ms)')
    ax.grid(True)

axes[-1].set_xlabel('Ping-Versuch')

plt.tight_layout()
plt.show()
```

![[Studium/Rechnernetze/Laborteil/Figure_1.png]]

## Aufgabe 3.1)

Führen Sie den traceroute-Befehl für den Web-Server der Hochschule und für ihre eigene IP- Adresse aus. Welche Ergebnisse erhalten Sie?

```cmd
PS C:\Users\mohsa> tracert www.htwg-konstanz.de

Tracing route to cms.htwg-konstanz.de [141.37.20.31]
over a maximum of 30 hops:

  1    <1 ms    <1 ms    <1 ms  192.168.0.1
  2    <1 ms    <1 ms    <1 ms  gw-kn-01.amh.kn.studentenwohnheim-bw.de [141.70.105.254]
  3     1 ms     1 ms     1 ms  kon-bib-1-te0-0-0-18.belwue.net [129.143.47.3]
  4     1 ms     1 ms     1 ms  kon-rz-1-hu0-0-1-0.belwue.net [129.143.58.217]
  5    <1 ms    <1 ms    <1 ms  129.143.47.227
  6    <1 ms     *       <1 ms  141.37.1.161
  7     1 ms     1 ms     1 ms  cmssrv7.htwg-konstanz.de [141.37.20.31]

Trace complete.
```
## Aufgabe 3.2)

Bestimmen Sie, in welchem Netz sich der Rechner befindet, von dem der traceroute Befehl
gestartet wird. Bestimmen Sie außerdem, durch welche Netze die Pakete geroutet werden. Sie
können bestimmen, zu welchem Netz ein Router gehört, in dem Sie die ASN (Autonomous
System Number) des Routers bestimmen, die Netze eindeutig kennzeichnet. Nutzen Sie dazu
beispielsweise das Online Tool https://www.asnlookup.com/.

| Hop | IP             | AS Handle | Organization Name             | Country |
| --- | -------------- | --------- | ----------------------------- | ------- |
| 1   | 192.168.0.1    | -         | -                             | -       |
| 2   | 141.70.105.254 | AS553     | Universität Stuttgart (BelWü) | Germany |
| 3   | 129.143.47.3   | AS553     | Universität Stuttgart (BelWü) | Germany |
| 4   | 129.143.58.217 | AS553     | Universität Stuttgart (BelWü) | Germany |
| 5   | 129.143.47.227 | AS553     | Universität Stuttgart (BelWü) | Germany |
| 6   | 141.37.1.161   | AS553     | Universität Stuttgart (BelWü) | Germany |
| 7   | 141.37.20.31   | AS553     | Universität Stuttgart (BelWü) | Germany |

## Aufgabe 3.3)

Betrachten Sie nun mehrere Online-Tools, so dass Sie den Traceroute-Befehl von mindestens
zwei unterschiedlichen Netzen aus starten können. Führen Sie den Traceroute-Befehl nun
nicht mehr nur für den Web-Server der Hochschule sondern zusätzlich für www.ntt.co.jp (Sie
können auch eine beliebige entfernte Seite wählen) und www.google.com aus. Bestimmen Sie,
welche Teile der Route für die unterschiedlichen Kombinationen aus Online-Tool und
Zielrechner identisch sind.

| Ziel           | Tool                         | Gemeinsame Hops (AS/Netz)                                                | Unterschiede / Besonderheiten               |
| -------------- | ---------------------------- | ------------------------------------------------------------------------ | ------------------------------------------- |
| **ntt.co.jp**  | traceroute-online.com        | ab **Hop 6 bis 13** über **Twelve99 / Arelion**, danach **XSERVER (JP)** | Start bei **Linode (Akamai)** in USA        |
|                | gsuite.tools                 | ab **Hop 7 bis 15** über **Twelve99 / Arelion**, danach **XSERVER (JP)** | Start in **UpCloud (UK)**, andere IPs davor |
| **google.com** | traceroute-online.com (IPv6) | ab **Hop 7** über **GOOGLE (AS15169)**                                   | Start komplett bei **Akamai / Linode** (SG) |
|                | gsuite.tools                 | ab **Hop 8** über **GOOGLE (AS15169)**                                   | Start in **UpCloud (UK)**, andere Route     |

## Aufgabe 4.1)

Betrachten Sie das Netz von Hurricane Electric und bestimmen Sie eine weltumspannende
Route (https://www.he.net/HurricaneElectricNetworkMap.pdf). Führen Sie dazu Traceroute
auf einem oder mehreren Routern zu einem Zielrouter aus, um 3 Router zu finden, zwischen
denen die Pakete die Welt umlaufen. Sie schicken also nicht ein Ping-Pakete um die Welt,
sondern teilen die Strecke in drei (oder mehr) Ping-Pakete auf.
a. Router A->Router B
b. Router B-> Router C
c. Router C->…
d. …->Router A

-> Was ist die einfache Verzögerung (One-Way-Delay), die ein Ping-Paket auf dieser Route
benötigt. Messen Sie dazu die Ping-Zeiten von Router zu Router.

| City      | IP                                                 | Router                       |
| --------- | -------------------------------------------------- | ---------------------------- |
| london    | [62.115.135.24](https://ipinfo.io/62.115.135.24)   | ldn-bb1-link.ip.twelve99.net |
| frankfurt | [62.115.135.150](https://ipinfo.io/62.115.135.150) | ffm-bb1-link.ip.twelve99.net |
| tokyo     | [62.115.135.74](https://ipinfo.io/62.115.135.74)   | tky-b2-link.ip.twelve99.net  |
| nyc       | [62.115.135.119](https://ipinfo.io/62.115.135.119) | ewr-b12-link.ip.twelve99.net |

|Strecke|Start-Router (Ort)|Ziel-Router (Ort)|Letzter erreichter Hop|RTT (ms)|
|---|---|---|---|---|
|NYC → London|`ewr-b13` (New York City)|`ldn-b11` (London)|`ldn-bb1-link.ip.twelve99.net` (62.115.139.245)|~70 ms|
|London → Frankfurt|`ldn-b11` (London)|`ffm-b11` (Frankfurt)|`ffm-bb1-link.ip.twelve99.net` (62.115.123.12)|~17 ms|
| Frankfurt → Tokyo | `ffm-b11` (Frankfurt) | `tky-b2` (Tokyo) | `tky-b2-link.ip.twelve99.net` (62.115.135.74) | ~230 ms |
| Tokyo → NYC | `tky-b2` (Tokyo) | `ewr-b13` (New York City) | `ewr-b12-link.ip.twelve99.net` (62.115.135.119) | ~171 ms |

Um die einfache Verzögerung (One-Way-Delay) zu bestimmen, wurden die Ping-Zeiten zwischen den Routern erfasst und durch zwei geteilt (da Ping die Round Trip Time misst).  

Die geschätzten Verzögerungen pro Teilstrecke sind:
- NYC → London: ~35 ms
- London → Frankfurt: ~8.5 ms
- Frankfurt → Tokyo: ~115 ms
- Tokyo → NYC: ~85.5 ms

➤ **Gesamter One-Way-Delay entlang der Weltumrundung: ca. 244 ms**

## Aufgabe 4.2)

Was ist die theoretische Minimallaufzeit eines Pakets auf dieser Route, wenn Sie eine
Ausbreitung mit Lichtgeschwindigkeit auf direktem Weg voraussetzen (z.B.
http://www.luftlinie.org/)? Um welchen Faktor ist die tatsächlich gemessene Zeit länger als
das theoretische Minimum? Führen Sie Erklärungen für die längeren Laufzeiten an.

![[Map-SS-01.png]]
Die theoretische Minimallaufzeit eines Pakets auf direktem Weg mit Lichtgeschwindigkeit beträgt bei einer Luftlinien-Gesamtdistanz von ~26.370 km:

$$
\frac{26.370\text{ km}}{300\text{ km/ms}} ≈ 87.9\text{ ms}
$$

Die gemessene Laufzeit betrug jedoch ca. **244 ms**, was ungefähr dem **2.78-fachen der theoretischen Minimalzeit** entspricht.

**Gründe für die längere tatsächliche Laufzeit:**
- Daten laufen **nicht direkt**, sondern über mehrere Umwege
- **Lichtgeschwindigkeit in Glasfaser** ist nur ~200.000 km/s (≈ 2/3 der Vakuumgeschwindigkeit)
- **Router und Switches verursachen Verzögerungen** durch Verarbeitung
- **Protokoll-Overhead** und mögliche Warteschlangen auf der Strecke
