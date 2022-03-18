import pandas as pd

file_name = "API_ALB_DS2_en_excel_v2_3734132.xls"
sheet = "Data"

df = pd.read_excel(io=file_name, sheet_name=sheet, skiprows=3)
print(df.head(10))
