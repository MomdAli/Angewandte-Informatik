# This is a sample Python script.

import redlab as rl

# print("------- einzelne Werte -------------------------")
# print("16 Bit Value: " + str(rl.cbAIn(0, 0, 1)))
# print("Voltage Value: " + str(rl.cbVIn(0, 0, 1)))
# print("------- Messreihe -------------------------")
# print("Messreihe: " + str(rl.cbAInScan(0, 0, 0, 300, 8000, 1)))


def sample_and_save_to_ascii(frequency, samples, rate, filename):
    # Sinussignal abtasten
    values = rl.cbAInScan(0, 0, 0, samples, rate, 1)

    # Daten in ASCII-Datei schreiben
    with open(filename, "w") as file:
        file.write(f"# Frequenz: {frequency} Hz\n")
        file.write(f"# Abtastrate: {rate} Samples/s\n")
        file.write("# Abtastpunkt, Wert\n")
        for i, value in enumerate(values):
            file.write(f"{i}, {value}\n")

    print(f"Werte erfolgreich in '{filename}' gespeichert.")


# Beispielaufruf

sample_and_save_to_ascii(frequency=7000, samples=1000, rate=7000, filename="signal_7000Hz.csv")
# print("------- Ausgabe -------------------------")
# print("Voltage Value: " + str(rl.cbVOut(0, 0, 101, 5.0)))

