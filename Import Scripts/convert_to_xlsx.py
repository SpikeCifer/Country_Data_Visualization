import pyexcel as p

xl__files = {
    'Albania': 'Data/API_ALB_DS2_en_excel_v2_3734132.xls',
    'Australia': 'Data/API_AUS_DS2_en_excel_v2_3754265.xls',
    'Bulgaria': 'Data/API_BGR_DS2_en_excel_v2_3752922.xls',
    'Brazil': 'Data/API_BRA_DS2_en_excel_v2_3735713.xls',
    'Cuba': 'Data/API_CUB_DS2_en_excel_v2_3756971.xls',
    'Djibouti': 'Data/API_DJI_DS2_en_excel_v2_3732898.xls',
    'Ecuador': 'Data/API_ECU_DS2_en_excel_v2_3733142.xls',
    'United Kingdom': 'Data/API_GBR_DS2_en_excel_v2_3733827.xls',
    'Greece': 'Data/API_GRC_DS2_en_excel_v2_3753075.xls',
    'Isle of Man': 'Data/API_IMN_DS2_EN_excel_v2_3767244.xls',
    'Iceland': 'Data/API_ISL_DS2_en_excel_v2_3734121.xls',
    'Kazakhstan': 'Data/API_KAZ_DS2_en_excel_v2_3732282.xls',
    'Luxembourg': 'Data/API_LUX_DS2_en_excel_v2_3731527.xls',
    'Madagascar': 'Data/API_MDG_DS2_en_excel_v2_3733883.xls',
    'Pakistan': 'Data/API_PAK_DS2_en_excel_v2_3734536.xls',
    'Sweden': 'Data/API_SWE_DS2_en_excel_v2_3732523.xls',
    'Niger': 'Data/API_NER_DS2_en_excel_v2_3753438.xls',
    'Tonga': 'Data/API_TON_DS2_en_excel_v2_3752620.xls',
    'Ukraine': 'Data/API_UKR_DS2_en_excel_v2_3732078.xls',
    'Uruguay': 'Data/API_URY_DS2_en_excel_v2_3734462.xls',
    'United States': 'Data/API_USA_DS2_en_excel_v2_3732641.xls',
    'Virgin Islands (U.S.)': 'Data/API_VIR_DS2_EN_excel_v2_3767457.xls',
    'Vietnam': 'Data/API_VNM_DS2_en_excel_v2_3732194.xls',
    'Samoa': 'Data/API_WSM_DS2_en_excel_v2_3733623.xls',
    'Zimbabwe': 'Data/API_ZWE_DS2_en_excel_v2_3757997.xls'
}

for key in xl__files:
    p.save_book_as(file_name=xl__files[key],
                   dest_file_name=xl__files[key].replace(".xls", ".xlsx"))
