import numpy as np
import matplotlib.pyplot as plt
import os

# Funktion zum Einlesen einer CSV-Datei und Berechnung des Mittelwerts und der Standardabweichung
def read_csv_file(filename, skip_header=1000, num_samples=50000):
    # Einlesen der Datei
    data = np.genfromtxt(
        filename,
        delimiter=';',
        skip_header=skip_header,
        converters={0: lambda s: float(s.decode().replace(',', '.')),
                    1: lambda s: float(s.decode().replace(',', '.'))}
    )

    relevant_data = data[:num_samples, 1]
    mean = np.mean(relevant_data)
    std_dev = np.std(relevant_data)

    return mean, std_dev

# Funktion zum Einlesen und Plotten von Messungen
def process_and_plot_measurements(directory, base_filename="Messung", file_extension=".csv",
                                  num_files=20):

    # Durchlaufen aller Dateien von Messung001 bis Messung020
    for i in range(1, num_files + 1):
        filename = f"{directory}/{base_filename}{i:03d}{file_extension}"

        # Prüfen, ob die Datei existiert
        if not os.path.exists(filename):
            print(f"Datei {filename} nicht gefunden.")
            continue

        # Plotten des Mittelwerts und der Standardabweichung
        mean, std_dev = read_csv_file(filename)
        print(f"Mean: {mean}, Std. Dev.: {std_dev}")
        plt.errorbar(i, mean, yerr=std_dev, fmt='o', label=f'Measurement {i}')

    # Plot-Einstellungen
    plt.xlabel('Time (s)')
    plt.ylabel('Voltage (V)')
    plt.title('Voltage vs Time for Multiple Measurements')
    plt.legend(loc='upper right', fontsize='small', ncol=2)
    plt.grid(True)
    plt.show()

process_and_plot_measurements("SSS/messungen")