import pandas as pd
from matplotlib import pyplot as plt
import matplotlib
import numpy as np

coreName = "timeDataBinaryWorst"
fileFormat = ".txt"
fileNumber = 10

uniFrame = pd.DataFrame(pd.read_csv(coreName + str(2) + fileFormat))
uniFrame.set_index("Algorithm", inplace=True)
uniFrame.dropna(axis=1, inplace=True)
print(uniFrame)
for i in range(3, fileNumber + 1):
    frame = pd.read_csv(coreName + str(i) + fileFormat)
    frame.set_index("Algorithm", inplace=True)
    frame.dropna(axis=1, inplace=True)
    print(frame)
    uniFrame = uniFrame + frame

uniFrame /= fileNumber
round(uniFrame)

print(uniFrame.to_string())

plt.figure()


# n^2
plt.plot(uniFrame.loc["combSort"])
# n(logn)^2
plt.plot(uniFrame.loc["bitonicSort"])

plt.plot(uniFrame.loc["shakerSort"])
plt.plot(uniFrame.loc["gnomeSort"])

# n^2.7
plt.plot(uniFrame.loc["stoogeSort"])

plt.xlabel("Input Size")
plt.ylabel("Time (microseconds)")

""" // Example code to add premade curves to the plot
x = np.linspace(1, 10, 100)
y = 100 * np.log2(x) ** 2
plt.plot(x, y)

x = np.linspace(1, 10, 100)
y = x ** 2
"""

plt.legend(("combSort", "bitonicSort", "shakerSort", "gnomeSort", "stoogeSort"))
plt.xticks(rotation="vertical")

# table
fig, ax = plt.subplots()
fig.patch.set_visible(False)
ax.axis('off')
ax.axis('tight')

colours = plt.cm.BuPu(np.linspace(0, 0.5, len(uniFrame.columns)))

table = ax.table(cellText=uniFrame.transpose().values,
                 #loc="right",
                 rowLabels=uniFrame.columns,
                 colLabels=uniFrame.index,
                 cellLoc="center",
                 rowColours=colours)

table.auto_set_font_size(False)
table.fontsize = 100

#table.scale(0.6, 2.015)

plt.show()
