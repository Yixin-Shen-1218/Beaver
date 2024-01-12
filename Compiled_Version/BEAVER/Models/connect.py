import pymysql

conn = pymysql.connect(
    host='sh-cdb-4sdq0l40.sql.tencentcdb.com',
    port=60624,
    user='root',
    passwd='reliableman@123',
    db='test'
)
cur = conn.cursor()

