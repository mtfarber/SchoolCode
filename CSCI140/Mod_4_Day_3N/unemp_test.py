import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

unemp = pd.read_csv('US_Unemployment_Oct2012.csv', index_col = 0)
unemp.head()
plt.figure(figsize = (10,10))
plt.xticks(rotation = 90)
sns.barplot(unemp.index,unemp['Unemployment'], palette='winter')