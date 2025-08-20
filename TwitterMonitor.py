import http
import json
import os
from datetime import time, timedelta, datetime
from time import sleep

import pytz

from tokenMonitor import send_email

import requests
from decimal import Decimal


from wechatBot.WechatBot import SendWXMessage

AVE_API_KEY = 'Dsxbrvco6JnLasEjQGQpY0mWfBfLEdckS9Ixvd9vNPNPEQivuuJGXYYsRl73y3bo'
SEEN_FILE = "seen_tweets.txt"
test_x_uid = '1496760771098734592' #CryptoLeo_sol账号
binancezh_uid = '1451036242431262720' #binance-zh 账号
binancezh_name = 'binancezh' #binance-zh 账号

# 加载历史推文 ID
def load_seen_ids():
    if not os.path.exists(SEEN_FILE):
        return set()
    with open(SEEN_FILE, "r", encoding="utf-8") as f:
        return set(line.strip() for line in f.readlines())

# 写入新推文 ID
def save_seen_id(tweet_id):
    with open(SEEN_FILE, "a", encoding="utf-8") as f:
        f.write(f"{tweet_id}\n")

def get_price_ave(token, chain='bsc'):
    url = f"https://api.ave.ai/v2/token/price/{token}-{chain}"
    headers = {
        "X-API-KEY": f"{AVE_API_KEY}"
    }

    resp = requests.get(url, headers=headers)
    if resp.status_code != 200:
        print(f"AVE API请求失败，状态码：{resp.status_code}")
        return None

    result = resp.json()
    if 'data' in result and 'price' in result['data']:
        price = Decimal(result['data']['price'])
        return price
    else:
        print("返回数据格式异常或缺少price字段")
        return None





def parse_entry(entry):
    try:
        content = entry.get("content", {})

        # 处理 item.items 数组结构
        if "items" in content:
            items = content["items"]
            tweets = []
            for idx, item in enumerate(items):
                tweet = item["item"]["itemContent"]["tweet_results"]["result"]
                full_text = tweet["legacy"]["full_text"]
                if idx == 0:
                    tweets.append(f"📌 主推文：\n{full_text}")
                else:
                    tweets.append(f"↪️ 回复 {idx}：\n{full_text}")
            return "\n\n".join(tweets)

        # 处理普通 itemContent 结构
        elif "itemContent" in content:
            tweet = content["itemContent"]["tweet_results"]["result"]
            full_text = tweet["legacy"]["full_text"]
            return f"📌 推文：\n{full_text}"
        return None

    except Exception as e:
        return None

def extract_new_tweets(response_json):
    new_contents = []
    keywords = ["alpha", "Alpha", "空投", "代币", "token", "Token", "发币", "上币"]  # 可自行添加更多关键词
    seen_tweet_ids = load_seen_ids()
    tweets = response_json.get("timeline", {})
    results = []

    for tweet in tweets:
        tweet_type = "normal"
        head = "💊币安推特监控消息"
        content = tweet["text"]
        tweet_id = tweet["tweet_id"]
        if tweet_id in seen_tweet_ids:
            continue

        matched = any(k in content for k in keywords)

        # 处理 回复推文
        if "reply_to" in tweet and tweet["reply_to"]:
            tweet_type = "reply"
            original_id = tweet["reply_to"]
            original_tweet = next((t for t in tweets if t["tweet_id"] == original_id), None)
            if original_tweet:
                original_text = original_tweet["text"]
                content = (
                    " 原文内容：" + "{ctrl}{ENTER}" + original_text + "{ctrl}{ENTER}" +
                    " 回复内容：" + "{ctrl}{ENTER}" + content
                )
                if any(k in original_text for k in keywords):
                    matched = True

        # 处理 引用推文
        if "quoted" in tweet and tweet["quoted"]:
            tweet_type = "quote"
            quoted_tweet = tweet["quoted"]
            quoted_text = quoted_tweet["text"]
            content += "{ctrl}{ENTER}🔁 引用内容：{ctrl}{ENTER}" + quoted_text
            if any(k in quoted_text for k in keywords):
                matched = True

        # 收集推文内容（只推送包含关键词的）
        if matched:
            results.append(
                head + "{ctrl}{ENTER}" +
                "时间：" + timeTranfer(tweet["created_at"]) + "{ctrl}{ENTER}" + content
            )
        save_seen_id(tweet_id)

    return results

def timeTranfer(timestamp):
    # 将 created_at 转为北京时间字符串，例如 '07.27 15:21'
    utc_time = datetime.strptime(timestamp, "%a %b %d %H:%M:%S %z %Y")
    beijing_time = utc_time.astimezone(pytz.timezone("Asia/Shanghai"))
    created_at_str = beijing_time.strftime("%m.%d %H:%M")
    return created_at_str


def x_test():
    conn = http.client.HTTPSConnection("twitter-api45.p.rapidapi.com")
    headers = {
        'x-rapidapi-key': "da53a3311fmshad99b5cb15f3429p1d0119jsndd850bf9fc86",
        'x-rapidapi-host': "twitter-api45.p.rapidapi.com"
    }
    # conn.request("GET", "/user?username=binancezh", headers=headers)
    conn.request("GET", f"/timeline.php?screenname={binancezh_name}", headers=headers)

    res = conn.getresponse()
    data = res.read()
    response_json = json.loads(data)
    # 假设 response_json 已经是字典对象
    with open("response.json", "w", encoding="utf-8") as f:
        json.dump(response_json, f, ensure_ascii=False, indent=4)
    new_tweets = extract_new_tweets(response_json) # 返回一个新推文数组
    for tweet in new_tweets:
        SendWXMessage("四人群", tweet)



# 示例调用
if __name__ == "__main__":
    # USDT = '0x55d398326f99059ff775485246999027b3197955'
    # ZKJ = '0xc71b5f631354be6853efe9c3ab6b9590f8302e81'
    #
    # price = get_price_ave(ZKJ)
    # if price:
    #     print(f"USDT -> ZKJ 当前价格（ZKJ每1 USDT能买多少）：{price}")
    # else:
    #     print("获取价格失败")
    last_minute = -1
    x_test()
    while True:
        now = datetime.utcnow() + timedelta(hours=8)  # 转为北京时间
        hour = now.hour
        minute = now.minute
        second = now.second

        if 9 <= hour <= 23:
            # 判断分钟尾数是否为1或6
            if minute % 10 == 1 or minute % 10 == 6:
                if minute != last_minute:  # 避免重复执行
                    print(f"✅ 执行任务：{now.strftime('%Y-%m-%d %H:%M:%S')}")
                    try:
                        x_test()
                    except Exception as e:
                        send_email(
                            "程序报错" + f"<UNK>{now.strftime('%Y-%m-%d %H:%M:%S')}",
                            f"<UNK>{e}",
                            True
                        )
                        print(e)
                    last_minute = minute
            else:
                sleep(1)
        else:
            # 不在监控时间段内，每分钟检查一次
            sleep(60)
