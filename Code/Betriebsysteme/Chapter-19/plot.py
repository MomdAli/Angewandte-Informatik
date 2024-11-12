import matplotlib.pyplot as plt

# Lists to store the number of pages and access times
pages = []
times = []

# Open the results file and read each line
fig, axs = plt.subplots(2, 2, figsize=(12, 10))
files = ['Chapter-19/results1.txt', 'Chapter-19/results2.txt', 'Chapter-19/results3.txt', 'Chapter-19/results4.txt']
titles = ['Results 1', 'Results 2', 'Results 3', 'Results 4']

for ax, file, title in zip(axs.flat, files, titles):
    pages = []
    times = []
    with open(file) as f:
        for line in f:
            data = line.split(',')
            pages.append(int(data[0]))
            times.append(float(data[1]))
    ax.plot(pages, times, marker='o')
    ax.set_xlabel("Number of Pages")
    ax.set_ylabel("Average Time per Access (ns)")
    ax.set_title(title)
    ax.grid(True)
    ax.set_xscale('log')
    ax.set_xticks(pages)
    ax.set_xticklabels(pages)

plt.tight_layout()
plt.show()
