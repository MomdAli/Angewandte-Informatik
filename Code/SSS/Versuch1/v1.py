import numpy as np
import matplotlib.pyplot as plt
import math
green = '#89de56'
red = '#e44a83'
yellow = '#e4c94a'
blue = '#4a99e4'

# Funktion zum Einlesen einer CSV-Datei und Berechnung des Mittelwerts und der Standardabweichung
def read_csv_file(filename, skip_header=1000, num_samples=1000):
    # Einlesen der Datei
    data = np.genfromtxt(
        filename,
        delimiter=';',
        skip_header=skip_header,
        converters={0: lambda s: float(s.replace(',', '.').encode()),
                    1: lambda s: float(s.replace(',', '.').encode())},
    )

    relevant_data = data[:num_samples, 1]   # Auswahl der relevanten Daten
    mean = np.mean(relevant_data)           # Berechnung des Mittelwerts
    std_dev = np.std(relevant_data)         # Berechnung der Standardabweichung
    return mean, std_dev

# Funktion zur Darstellung der Messungen
def plot_measurements(x, y, std_dev, title):
    plt.errorbar(x, y, yerr=std_dev, fmt='o', linestyle='--', label='Messungung von 1 bis 20', color=green)

    # Plot-Einstellungen
    plt.xlabel('Spannung (V)')
    plt.ylabel('Distanz (cm)')
    plt.title(title)
    plt.legend(loc='upper right', fontsize='small', fancybox=True)
    plt.grid(True)
    plt.show()

# Funktion zur Darstellung der linearen Regression
def plot_function_data(x, y, x_data, y_data, title, r,
                       color=red, color_data=blue, linestyle='-'):
    plt.plot(x, y, linestyle=linestyle, color=color, label='Mean')
    plt.scatter(x_data, y_data, color=color_data, label='Datenpunkte')
    plt.xlabel('Spannung (V)')
    plt.ylabel('Distanz (cm)')
    l = plt.legend(loc='upper right', fontsize='small', fancybox=True)
    l.get_texts()[0].set_text(f'Lineare Regression mit r^2={r:.4f}')
    plt.title(title)
    plt.grid(True)
    plt.show()

# Funktion zur Berechnung der linearen Regression
def logarithmic_linear_regression(x, y):
    x_log = np.log(x)                   # Logarithmieren der x-Werte
    y_log = np.log(y)                   # Logarithmieren der y-Werte
    a, b = np.polyfit(x_log, y_log, 1)  # Berechnung der linearen Regression
    r = np.corrcoef(x_log, y_log)[0, 1] # Berechnung des Korrelationskoeffiz
    return a, b, r


# * Main
# Durchlaufen aller Dateien von Messung001 bis Messung020
x_data = []                     # Spannungswerte
y_data = list(range(10, 70, 3)) # Distanzwerte
std_devs = []
for i in range(1, 21):
    filename = f"SSS/messungen/Messung{i:03d}.csv"

    mean, std_dev = read_csv_file(filename)
    x_data.append(mean)
    std_devs.append(std_dev)

    print(f"Spannung bei {y_data[i-1]} cm: {mean:.2f} V ± {std_dev:.2f} V")


# Darstellung der Messungen mit Fehlerbalken für die Standardabweichung
# plot_measurements(x_data, y_data, std_devs, "Messungen")


# Berechnung der linearen Regression und Darstellung der Ergebnisse
print(f'x_data: {x_data} \ny_data: {y_data}')
a, b, r = logarithmic_linear_regression(x_data, y_data)
print(f"Linear regression: a={a}, b={b}, r={r}")

x = np.linspace(x_data[0], x_data[-1], 1000)
y = np.exp(b) * x ** a
# plot_function_data(x, y, x_data, y_data, "Linear regression", r**2)


s = np.std(y_data, ddof=1)
sx = s / math.sqrt(len(y_data))