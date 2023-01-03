import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import folium

def subset_data(df, column = 'retweets', cutoff = 100, above = True):
    if column in df:
        if above == True:
            return df.loc[df[column] > cutoff] 
        else:
            return df.loc[df[column] <= cutoff]

def aggregate_data(df, column, how = 'sum'):
    if column in df:
        return df.groupby(column).agg(how)

def plot_data(df, col1, col2, rotate_labels = False):
    if col1 in df and col2 in df:
        if rotate_labels == True:
            plt.xticks(rotation = 90)
        new_data = aggregate_data(df, col2 ,how = 'sum')
        return sns.barplot(new_data.index,new_data[col1])

def map_data(df, data_col, json_file, key, cmap = 'BuPu', filename = 'map.html'):
    m = folium.Map([43, -100], zoom_start=4)
    folium.Choropleth(geo_data=json_file,
    data = df,
    columns = [df.index, data_col],
    key_on = key,
    fill_color = cmap
    ).add_to(m)
    m.save(filename)
    return m
    
#Extra credit only
#Add in functionality to automatically create log scale
def plot_data_wl(df, col1, col2, rotate_labels = False):
     pass