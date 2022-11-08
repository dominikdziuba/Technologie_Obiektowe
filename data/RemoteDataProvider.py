import requests

class RemoteDataProvider():
    def __init__(self):
        self.link = requests.get("https://www.ogimet.com/cgi-bin/getsynop?begin=202207270600&end=202207271200&state=Pol")
        self.length = 0
        self.data = []
        self.data, self.length = self.getData()

    def getData(self):
        ogimet = self.link.text.split("\n")
        for i in ogimet:
           self.data.append(i[i.find(",") + 1: i.find(" 333 ")].replace(" ", ",").split(","))
        self.length = len(self.data)
        return self.data, len(self.data)