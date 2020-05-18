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

data1 = None
data2 = None
data3 = None
data4 = None

sectors = ['FINANCIALS', 'INFORMATION_TECHNOLOGY', 'COMMUNICATION_SERVICES', 'HEALTH_CARE']

def risk_return(sector):

    if (sector == "COMMUNICATION_SERVICES"):
        df_std_risk = data3['Close'].std().dropna()
        s = df_std_risk.to_dict()

        ret_list = []
        for key, value in s.items():
            obj = {
                "stock": key,
                "std": value
            }
            ret_list.append(obj)

        return ret_list

    elif (sector == "FINANCIALS"):
        df_std_risk = data1['Close'].std().dropna()
        s = df_std_risk.to_dict()

        ret_list = []
        for key, value in s.items():
            obj = {
                "stock": key,
                "std": value
            }
            ret_list.append(obj)

        return ret_list

    elif (sector == "INFORMATION_TECHNOLOGY"):
        df_std_risk = data2['Close'].std().dropna()
        s = df_std_risk.to_dict()

        ret_list = []
        for key, value in s.items():
            obj = {
                "stock": key,
                "std": value
            }
            ret_list.append(obj)

        return ret_list

    elif (sector == "HEALTH_CARE"):
        df_std_risk = data4['Close'].std().dropna()
        s = df_std_risk.to_dict()

        ret_list = []
        for key, value in s.items():
            obj = {
                "stock": key,
                "std": value
            }
            ret_list.append(obj)

        return ret_list

def refresh():
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

    global data1
    global data2
    global data3
    global data4

    data1 = web.get_data_yahoo(df_finance['Symbol'].tolist()[:50], start=start, end=today)
    data2 = web.get_data_yahoo(df_information['Symbol'].tolist()[:50], start=start, end=today)
    data3 = web.get_data_yahoo(df_communication['Symbol'].tolist()[:50], start=start, end=today)
    data4 = web.get_data_yahoo(df_health['Symbol'].tolist()[:50], start=start, end=today)

def request(): 

    global data1
    global data2
    global data3
    global data4

    output = []

    import operator
    std_list = [data1['Close'].std().mean(), data2['Close'].std().mean(), data3['Close'].std().mean(), data4['Close'].std().mean()]
    print(std_list)
    index, value = min(enumerate(std_list), key=operator.itemgetter(1))

    print(index)

    output.append(sectors[index])

    volume_list = [data1['Volume'].mean().mean(), data2['Volume'].mean().mean(), data3['Volume'].mean().mean(), data4['Volume'].mean().mean()]
    print(volume_list)
    index1, value1 = max(enumerate(volume_list), key=operator.itemgetter(1))

    print(index1)
    output.append(sectors[index1])

    close_list = [data1['Close'].mean().mean(), data2['Close'].mean().mean(), data3['Close'].mean().mean(), data4['Close'].mean().mean()]
    print(close_list)
    index2, value2 = max(enumerate(close_list), key=operator.itemgetter(1))

    print(index2)
    output.append(sectors[index2])

    return output


def get_sector():
    return request()

@app.route("/stock/sector", methods=["GET"])
def get_stock_sector():
    return jsonify(get_sector())

@app.route("/stock/risk/<sector>", methods=["GET"])
def get_risk_return(sector):
    return jsonify(risk_return(sector))

import atexit

# v2.x version - see https://stackoverflow.com/a/38501429/135978
# for the 3.x version
from apscheduler.scheduler import Scheduler

cron = Scheduler(daemon=True)
# Explicitly kick off the background thread
cron.start()

@cron.interval_schedule(hours=1)
def job_function():
    refresh()


# Shutdown your cron thread if the web process is stopped
atexit.register(lambda: cron.shutdown(wait=False))

if __name__ == '__main__':
    refresh()
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
