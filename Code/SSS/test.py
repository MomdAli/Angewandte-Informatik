import numpy as np

import matplotlib.pyplot as plt

# Read the CSV file
data = np.genfromtxt('SSS/messungen/Messung001.csv', delimiter=';', skip_header=3,
                     converters={
                        0: lambda s: float(s.decode('utf-8').replace(',', '.')),
                        1: lambda s: float(s.decode('utf-8').replace(',', '.'))
                     })

# Extract time and voltage columns
time = data[:, 0]
voltage = data[:, 1]

# Plot the data
plt.figure()
plt.plot(time, voltage, label='Voltage vs Time')
plt.xlabel('Time')
plt.ylabel('Voltage')
plt.title('Time vs Voltage')
plt.legend()
plt.grid(True)
plt.show()