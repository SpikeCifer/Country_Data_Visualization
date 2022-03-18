import pandas as pd
import os

for filename in os.listdir("data"):
    df = pd.read_excel(io="data/"+filename, sheet_name="Data", skiprows=3)
    print(df.head(10))