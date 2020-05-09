import pandas as pd
import datetime
import pandas_datareader.data as web
import pandas_datareader.iex.ref as ref
import pandas_datareader.tiingo as tiingo

import json

from iexfinance.stocks import Stock
from iexfinance.stocks import get_collections
from iexfinance.stocks import get_historical_data

from flask import Flask
from flask import jsonify

app = Flask(__name__)

def execute(sector):
    return {
        "messg": sector
    }

@app.route("/stock/<sector>", methods=["POST"])
def get_sector():
    table=pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
    df = table[0]

    df.columns = [column.replace(" ", "_") for column in df.columns]

    df_finance = df.query('GICS_Sector == "Financials"')
    df_information = df.query('GICS_Sector == "Information Technology"')
    df_communication = df.query('GICS_Sector == "Communication Services"')
    df_health = df.query('GICS_Sector == "Health Care"')
    df_estate = df.query('GICS_Sector == "Real Estate"')

    from datetime import date
    import yfinance as yf
    yf.pdr_override()

    today = date.today()
    start = "2020-04-01"

    data1 = web.get_data_yahoo(df_finance['Symbol'].tolist()[:5], start=start, end=today)
    data2 = web.get_data_yahoo(df_information['Symbol'].tolist()[:5], start=start, end=today)
    data3 = web.get_data_yahoo(df_communication['Symbol'].tolist()[:5], start=start, end=today)
    data4 = web.get_data_yahoo(df_health['Symbol'].tolist()[:5], start=start, end=today)

    sectors = ['FINANCIALS', 'INFORMATION_TECHNOLOGY', 'COMMUNICATION_SERVICES', 'HEALTH_CARE']

    output = []

    import operator
    std_list = [data1['Close'].std().mean(), data2['Close'].std().mean(), data3['Close'].std().mean(), data4['Close'].std().mean()]
    index, value = min(enumerate(std_list), key=operator.itemgetter(1))

    output.append(sectors[index])

    volume_list = [data1['Volume'].mean().mean(), data2['Volume'].mean().mean(), data3['Volume'].mean().mean(), data4['Volume'].mean().mean()]
    index, value = max(enumerate(volume_list), key=operator.itemgetter(1))

    output.append(sectors[index])

    close_list = [data1['Close'].mean().mean(), data2['Close'].mean().mean(), data3['Close'].mean().mean(), data4['Close'].mean().mean()]
    index, value = max(enumerate(close_list), key=operator.itemgetter(1))

    output.append(sectors[index])
    return output

@app.route("/stock/sector", methods=["GET"])
def get_stock_sector():
    return jsonify(get_sector())

if __name__ == '__main__':
    app.run(debug=True)

# start = datetime.datetime(2020, 4, 4)
# end = datetime.datetime(2020, 5, 3)

################
# table=pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
# df = table[0]
# # df.to_csv('S&P500-Info.csv')
# df.to_csv("S&P500-Symbols.csv", columns=['Symbol'])

# df.columns = [column.replace(" ", "_") for column in df.columns]

# df.query('GICS_Sector == "Financials"', inplace=True)

# def getCompanyInfo(symbols):
#     stock_batch = Stock(symbols, token="pk_9798e0bc697744ee953fc47b3e01dfa8")
#     company_info = stock_batch.get_company()
#     return company_info

# info = getCompanyInfo(df['Symbol'].tolist())

# print(info)
####################


# symDf = ref.SymbolsReader().read()
# symDf.dropna(inplace=True)
#symDf['symbol'] = symDf['symbol'].astype(str)


# symDf.symbol = symDf.symbol.astype("string")

# symbolsList = symDf.symbol.tolist()[:500]

# def divide_chunks(l, n):

#     # looping till length l
#     for i in range(0, len(l), n):
#         yield l[i:i + n]

# x = list(divide_chunks(symbolsList, 100))

# lista = []

# for chunk in x:
#     print('awdawd')
#     try:
#         stock_batch = Stock(symbols=chunk, token="pk_9798e0bc697744ee953fc47b3e01dfa8", limit=5)
#         company_info = stock_batch.get_company()
#         for v in company_info.values():
#             lista.append(v['sector'])
#     except:
#         pass

# collection = get_collections(collection_name="Industrials", output_format='pandas', collection_type='sector', token="pk_9798e0bc697744ee953fc47b3e01dfa8").head()

# print(collection)


start = datetime.datetime(2020, 4, 4)
end = datetime.datetime(2020, 5, 3)





