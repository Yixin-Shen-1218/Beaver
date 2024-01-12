import csv
import sys

import pymysql
import math

import numpy as np

from connect import cur

time_dict = {}
result = []
drpred = []
drtrue = []
calendar = []
loss = []
sta = []
scenariocalendar = []
scenariovalue = []


# conn = pymysql.connect(
#     host='sh-cdb-4sdq0l40.sql.tencentcdb.com',
#     port=60624,
#     user='root',
#     passwd='reliableman@123',
#     db='test'
# )
# cur = conn.cursor()


def scenario(stress):
    if stress == "0":
        search_sql = "select * from simMEV;"
        cur.execute(search_sql)
        rows = cur.fetchall()
        for row in rows:
            time_dict[int(row[0])] = float(row[1])
    else:
        f1 = open(stress)
        scenariofile = f1.readline()
        while scenariofile:
            scenario_line = scenariofile.split('|')
            time_dict[int(scenario_line[0])] = float(scenario_line[1])
            scenariofile = f1.readline()


def data(sql):
    if sql == "0":
        with open('Models/simDTS.csv') as f:
            reader = csv.reader(f)
            b = [-11, 4.21, -1.25, 0.0058, 1.012, 1.513, 0.502, 0.046]
            for idx, line in enumerate(reader):
                if idx == 0:
                    continue
                else:
                    if line[8] == 'FALSE':
                        line[8] = 0
                    if line[8] == 'TRUE':
                        line[8] = 1
                    calendar.append(int(line[2]) + int(line[3]))
                    x = [1, math.log(float(line[2]) + 1), math.log(float(line[2]) + 1) * math.log(float(line[2]) + 1),
                        float(line[3]), float(line[4]), float(line[5]), float(line[6]),
                        time_dict[int(line[2]) + int(line[3])]]
                    scenariocalendar.append(int(line[2]) + int(line[3]))
                    sx = 0
                    px = 0
                    for i in range(len(b)):
                        sx += b[i] * x[i]
                        px = 1 / (1 + math.exp(-sx))
                    line.append(px)
                result.append(line)
    else:
        cur.execute(sql)
        lines = cur.fetchall()
        b = [-11, 4.21, -1.25, 0.0058, 1.012, 1.513, 0.502, 0.046]
        for line in lines:
            calendar.append(int(line[2]) + int(line[3]))
            listline = list(line)
            x = [1, math.log(float(line[2]) + 1), math.log(float(line[2]) + 1) * math.log(float(line[2]) + 1),
                 float(line[3]), float(line[4]), float(line[5]), float(line[6]),
                 time_dict[int(line[2]) + int(line[3])]]
            scenariocalendar.append(int(line[2]) + int(line[3]))
            sx = 0
            px = 0
            for i in range(len(b)):
                sx += b[i] * x[i]
                px = 1 / (1 + math.exp(-sx))
            listline.append(px)
            result.append(listline)


def defaultrate():
    for k in range(list(set(calendar))[0], list(set(calendar))[-1] + 1):
        totalpred = 0
        timespred = 0
        totaltrue = 0
        timestrue = 0
        for line in result:
            if k == int(line[2]) + int(line[3]):
                totalpred = totalpred + np.double(line[9])
                timespred = timespred + 1
                totaltrue = totaltrue + np.double(line[8])
                timestrue = timestrue + 1
        drpred.append(totalpred / timespred)
        drtrue.append(totaltrue / timestrue)
    print(len(set(calendar)))
    print(drpred)
    print(drtrue)
    print(set(calendar))


def loglikelihood():
    for k in range(list(set(calendar))[0], list(set(calendar))[-1] + 1):
        pred = []
        true = []
        for line in result:
            if k == int(line[2]) + int(line[3]):
                pred.append(line[9])
                true.append(line[8])
        loss.append(logloss(true, pred))
    print(loss)
    print(set(scenariocalendar))
    for i in range(0, len(set(scenariocalendar))):
        scenariovalue.append(time_dict[list(set(scenariocalendar))[i]])
    print(scenariovalue)


def logloss(y_true, y_pred, eps=1e-15):
    import numpy as np

    # Prepare numpy array data
    y_true = np.array(y_true)
    y_pred = np.array(y_pred)
    assert (len(y_true) and len(y_true) == len(y_pred))

    # Clip y_pred between eps and 1-eps
    p = np.clip(y_pred, eps, 1 - eps)
    loss = np.sum(- y_true * np.log(p) - (1 - y_true) * np.log(1 - p))

    return loss / len(y_true)


def statistic():
    for k in scenariocalendar:
        sta.append(time_dict[k])
    stasorted = sorted(sta)
    print(str(stasorted[0]))  # min
    print(str(stasorted[len(stasorted) - 1]))  # max
    print(str(np.mean(sta)))  # mean
    print(str(np.std(sta, ddof=1)))  # sd


search_sql = sys.argv[1]
stress = sys.argv[2]
scenario(stress)
data(search_sql)
defaultrate()
loglikelihood()
statistic()
cur.close()
