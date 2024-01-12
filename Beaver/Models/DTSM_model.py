from sklearn.metrics import roc_curve, auc
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import sys
import sklearn
from sklearn import metrics
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn import preprocessing
import pickle
import string
import math
from connect import cur

search_sql = sys.argv[1]

# f1 = open("./DT_train_all.txt")
# train_data = f1.readline()
# train_credit = []
# train_DTI = []
# train_UPB = []
# train_LTV = []
# train_IR = []
# train_SEQ = []
# train_Age = []
# train_Age2 = []
# train_Age_log = []
# train_Age_log2 = []
# train_vintage = []
# train_calendar = []
# train_Def = []
#
# test_credit = []
# test_DTI = []
# test_UPB = []
# test_LTV = []
# test_IR = []
# test_Def = []
#
# x = []
# y = []
# b = []
#
# while train_data:
#     line_data = train_data.split('|')
#     if line_data[0] == '9999':
#         1
#     if line_data[0] != '9999':
#         train_credit.append(int(line_data[0]))
#         train_DTI.append(int(line_data[1]))
#         train_UPB.append(int(line_data[2]))
#         train_LTV.append(int(line_data[3]))
#         train_IR.append(float(line_data[4]))
#         train_SEQ.append(str(line_data[5]))
#         train_Age.append(int(line_data[6]))
#         train_Age2.append(math.pow(int(line_data[6]), 2))
#         if int(line_data[6]) == 0:
#             train_Age_log.append(0)
#             train_Age_log2.append(0)
#         else:
#             train_Age_log.append(math.log(int(line_data[6])))
#             train_Age_log2.append(math.pow(math.log(int(line_data[6])), 2))
#
#         train_Def.append(int(line_data[8]))
#         train_vintage.append(int(line_data[9]))
#         train_calendar.append(
#             (int(int(line_data[9]) / 100) - 2004) * 12 + (int(line_data[9]) % 100 - 1) * 4 + int(line_data[6]))
#     train_data = f1.readline()
#
# f1.close()
#
# train_credit = np.array(train_credit)
# train_DTI = np.array(train_DTI)
# train_UPB = np.array(train_UPB)
# train_LTV = np.array(train_LTV)
# train_IR = np.array(train_IR)
# train_Age = np.array(train_Age)
# train_Age2 = np.array(train_Age2)
# train_Age_log = np.array(train_Age_log)
# train_Age_log2 = np.array(train_Age_log2)
# train_vintage = np.array(train_vintage)
# train_calendar = np.array(train_calendar)
# train_Def = np.array(train_Def)
#
# for i in range(len(train_credit)):
#     b.append(train_credit[i])
#     b.append(train_DTI[i])
#     b.append(train_UPB[i])
#     b.append(train_LTV[i])
#     b.append(train_IR[i])
#     b.append(train_Age[i])
#     b.append(train_vintage[i])
#     b.append(train_calendar[i])
#     b.append(train_Age[i])
#     b.append(train_Age2[i])
#     b.append(train_Age_log[i])
#     b.append(train_Age_log2[i])
#     y.append(train_Def[i])
#     x.append(b)
#     b = []
#
# x = np.array(x)
# y = np.array(y)

# print(x.shape)
# print(y.shape)
if search_sql == "0":
    f2 = open("Models/dt_test_all.txt")
else:
    cur.execute(search_sql)
    lines = cur.fetchall()
    1  # fill in the way your process the data
test_data = f2.readline()

test_credit = []
test_DTI = []
test_UPB = []
test_LTV = []
test_IR = []
test_Age = []
test_Age2 = []
test_Age_log = []
test_Age_log2 = []
test_SEQ = []
test_vintage = []
test_calendar = []
test_Def = []

x2 = []
y2 = []
b2 = []

while test_data:
    line_data = test_data.split('|')
    if line_data[0] == '9999':
        1
    if line_data[0] != '9999':
        test_credit.append(int(line_data[0]))
        test_DTI.append(int(line_data[1]))
        test_UPB.append(int(line_data[2]))
        test_LTV.append(int(line_data[3]))
        test_IR.append(float(line_data[4]))
        test_SEQ.append(str(line_data[5]))
        test_Age.append(int(line_data[6]))
        test_Age2.append(math.pow(int(line_data[6]), 2))
        if int(line_data[6]) == 0:
            test_Age_log.append(0)
            test_Age_log2.append(0)
        else:
            test_Age_log.append(math.log(int(line_data[6])))
            test_Age_log2.append(math.pow(math.log(int(line_data[6])), 2))
        test_Def.append(int(line_data[8]))
        test_vintage.append(int(line_data[9]))
        test_calendar.append(
            (int(int(line_data[9]) / 100) - 2004) * 12 + (int(line_data[9]) % 100 - 1) * 4 + int(line_data[6]));
    test_data = f2.readline()

f2.close()

test_credit = np.array(test_credit)
test_DTI = np.array(test_DTI)
test_UPB = np.array(test_UPB)
test_LTV = np.array(test_LTV)
test_IR = np.array(test_IR)
test_SEQ = np.array(test_SEQ)
test_vintage = np.array(test_vintage)
test_calendar = np.array(test_calendar)
test_Def = np.array(test_Def)

for i in range(len(test_credit)):
    b2.append(test_credit[i])
    b2.append(test_DTI[i])
    b2.append(test_UPB[i])
    b2.append(test_LTV[i])
    b2.append(test_IR[i])
    b2.append(test_Age[i])
    b2.append(test_vintage[i])
    b2.append(test_calendar[i])
    b2.append(test_Age[i])
    b2.append(test_Age2[i])
    b2.append(test_Age_log[i])
    b2.append(test_Age_log2[i])

    y2.append(test_Def[i])
    x2.append(b2)
    b2 = []

x2 = np.array(x2)
y2 = np.array(y2)

# print(x2.shape)
# print(y2.shape)

# scaler = preprocessing.StandardScaler().fit(x)
# x = scaler.transform(x)
scaler2 = preprocessing.StandardScaler().fit(x2)
x2 = scaler2.transform(x2)
# print(scaler.mean_)
# print(scaler.scale_)

ct = ColumnTransformer(
    [('one_hot_encoder', OneHotEncoder(), [5, 6, 7])],
    # The column numbers to be transformed (here is [0] but can be [0, 1, 3])
    remainder='passthrough'  # Leave the rest of the columns untouched
)

# x = ct.fit_transform(x)
x2 = ct.fit_transform(x2)

# model = LogisticRegression(solver='liblinear').fit(x, y)
# with open('model.pickle', 'wb') as fw:
#     pickle.dump(model, fw)

# print(model.coef_)

# # parametric form of loan age:
# y_para = []
# x_para = []
# s_para = 0
# for i in range(1, 190):
#     s_para = model.coef_[0][239] * ((i - scaler.mean_[7]) / scaler.scale_[7]) + model.coef_[0][240] * \
#              ((math.pow(i, 2) - scaler.mean_[8]) / scaler.scale_[8]) + model.coef_[0][241] * \
#              ((math.log(i) - scaler.mean_[9]) / scaler.scale_[9]) + model.coef_[0][242] * (
#                      (math.pow(math.log(i), 2) - scaler.mean_[10]) / scaler.scale_[10])
#     y_para.append(s_para)
#     x_para.append(i)
#
# plt.plot(x_para, y_para, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('score of loan age')
# plt.xlabel('Month')
#
# # with penalty
# dr = []
# x_axis = []
# for i in range(0, 191):
#     dr.append(model.coef_[0][i])
#     x_axis.append((i + 1))
#
# plt.plot(x_axis, dr, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('Coefficient of each month')
# plt.xlabel('Month')
#
# # no penalty
# dr = []
# x_axis = []
# for i in range(0, 191):
#     dr.append(model.coef_[0][i])
#     x_axis.append((i + 1))
#
# plt.plot(x_axis, dr, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('Coefficient of each month')
# plt.xlabel('Month')
#
# # with penalty
# dr = []
# x_axis = []
# for i in range(191, 231):
#     dr.append(model.coef_[0][i])
#     x_axis.append(((i + 1) - 190) * 4)
#
# plt.plot(x_axis, dr, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('Coefficienct of each month')
# plt.xlabel('Month')
#
# # no penalty
# dr = []
# x_axis = []
# for i in range(0, 40):
#     dr.append(model.coef_[0][i])
#     x_axis.append(((i + 1)) * 4)
#
# plt.plot(x_axis, dr, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('Coefficienct of each month')
# plt.xlabel('Month')
#
# # with penalty
# dr = []
# x_axis = []
# for i in range(229, 422):
#     dr.append(model.coef_[0][i])
#     x_axis.append((i + 1) - 231)
#
# plt.plot(x_axis, dr, 'b')
# # plt.legend(loc='lower right')
# # plt.plot([0,1],[0,1],'r--')
# plt.ylabel('Coefficienct of each month')
# plt.xlabel('Month')

with open('Models/model.pickle', 'rb') as fr:
    model_use = pickle.load(fr)
predict_proba_result = model_use.predict_proba(x2)
predict_result = model_use.predict(x2)

drpred = []
drtrue = []
each = []
all = []
loss = []

for k in range(0, len(predict_result)):
    each.append(test_calendar[k])
    each.append(predict_proba_result[k][1])
    each.append(y2[k])
    all.append(each)
    each = []

for k in range(list(set(test_calendar))[0], list(set(test_calendar))[-1] + 1):
    totalpred = 0
    timespred = 0
    totaltrue = 0
    timestrue = 0
    for line in all:
        if k == int(line[0]):
            totalpred = totalpred + np.double(line[1])
            timespred = timespred + 1
            totaltrue = totaltrue + np.double(line[2])
            timestrue = timestrue + 1
    drpred.append(totalpred / timespred)
    drtrue.append(totaltrue / timestrue)
print(len(set(test_calendar)))
print(drpred)
print(drtrue)
print(set(test_calendar))


# log_likely = 0
# for i in range(len(predict_proba_result)):
#     if (y2[i] == 0):
#         log_likely = log_likely + math.log(predict_proba_result[i][0])
#     else:
#         log_likely = log_likely + math.log(predict_proba_result[i][1])
# print('-log_likelihood: ', (-log_likely) / len(predict_proba_result))

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


scenario = []
for k in range(list(set(test_calendar))[0], list(set(test_calendar))[-1] + 1):
    pred = []
    true = []
    for line in all:
        if k == int(line[0]):
            pred.append(line[1])
            true.append(line[2])
    loss.append(logloss(true, pred))
print(loss)
print(set(test_calendar))
scenario = [0] * len(set(test_calendar))
print(scenario)
stasorted = sorted(scenario)
print(str(stasorted[0]))  # min
print(str(stasorted[len(stasorted) - 1]))  # max
print(str(np.mean(scenario)))  # mean
print(str(np.std(scenario, ddof=1)))  # sd
