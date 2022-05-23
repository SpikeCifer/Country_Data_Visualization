#!/usr/bin/env python3
from os import walk, getcwd
import pandas as pd
import csv

HEADER_ROW = 2
STARTING_YEAR_COLUMN = 4
RES_PATH = getcwd()+"/res/"
DEST_PATH = getcwd()+"/load_res/"

SELECTED_INDICATORS = {'SP.DYN.LE00.MA.IN', 'TX.VAL.MRCH.WL.CD', 'SP.POP.2024.FE.5Y', 'SP.DYN.TO65.MA.ZS',
                    'NY.GSR.NFCY.CD', 'EN.ATM.CO2E.SF.ZS', 'EG.ELC.FOSL.ZS', 'MS.MIL.XPND.CD',
                    'NV.MNF.FBTO.ZS.UN', 'EN.URB.LCTY.UR.ZS', 'BX.GSR.GNFS.CD', 'BM.GSR.GNFS.CD'}

def init_dataframe(filename:str) -> pd.DataFrame:
    df = pd.read_excel(RES_PATH+filename, header=HEADER_ROW+1)
    return df


def doc_years(df:pd.DataFrame):
    periods = ["year", "five_years", "ten_years", "twenty_years"]
    years_frame = pd.DataFrame(columns=periods)

    start_year = int(df.columns[STARTING_YEAR_COLUMN])
    five_years = start_year
    ten_years = start_year
    twenty_years = start_year
    
    first_row = [start_year, five_years, ten_years, twenty_years]
    years_frame.loc[len(years_frame)] = first_row
    
    for column in df.columns[STARTING_YEAR_COLUMN+1:]:
        current_year = int(column)
        # For each year assign a 5, 10 and 20 period
        if ((current_year - start_year)%5 == 0):
            five_years += 5

        if ((current_year - start_year)%10 == 0):
            ten_years += 10

        if ((current_year - start_year)%20 == 0):
            twenty_years += 20

        new_row = [current_year, five_years, ten_years, twenty_years]
        years_frame.loc[len(years_frame)] = new_row
    years_frame.to_csv(DEST_PATH+"years.csv", index=False, header=False)


def doc_indicators(df:pd.DataFrame):
    labels = ["code", "name"]
    rslt_df = df[['Indicator Code', 'Indicator Name']]
    rslt_df = rslt_df[rslt_df['Indicator Code'].isin(SELECTED_INDICATORS)]
    rslt_df.to_csv(DEST_PATH+"indicators.csv", quoting=csv.QUOTE_NONNUMERIC, index=False, header=False)


def doc_countries(df:pd.DataFrame):
    result = df[['Country Code', 'Country Name']]
    result.head(1).to_csv(DEST_PATH+'countries.csv', mode='a', quoting=csv.QUOTE_NONNUMERIC, na_rep=0, index=False, header=False)


def doc_measurements(df:pd.DataFrame):
    doc_countries(df)
    labels = ['counry_code', 'indicator_code', 'year', 'value']
    df.drop(['Country Name', 'Indicator Name'], axis=1, inplace=True)
    df = df[df['Indicator Code'].isin(SELECTED_INDICATORS)]
    measurements_df = pd.DataFrame(columns=labels)
    
    metric_arrays = df.to_numpy()
    for metric in metric_arrays:
        year = 0
        for value in metric[2:]:
            row=[metric[0], metric[1], int(df.columns[STARTING_YEAR_COLUMN -2])+year, value]
            measurements_df.loc[len(measurements_df)] = row
            year += 1

    measurements_df.to_csv(DEST_PATH+'measurements.csv', mode='a', quoting=csv.QUOTE_NONNUMERIC, na_rep=0, index=False, header=False)


filenames = next(walk(RES_PATH), (None, None, []))[2]

doc_years(init_dataframe(filenames[0]))
print("Created Years file")
doc_indicators(init_dataframe(filenames[0]))
print("Created Indicators File")

for filename in filenames:
    doc_measurements(init_dataframe(filename))

print("Finished Execution")
